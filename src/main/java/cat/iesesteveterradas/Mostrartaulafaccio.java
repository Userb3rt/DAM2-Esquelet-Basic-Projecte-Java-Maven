package cat.iesesteveterradas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Mostrartaulafaccio
 */
public class Mostrartaulafaccio {
    private ArrayList<Integer> faccionesnumber = new ArrayList<>();
    private Connection conn;

    public Mostrartaulafaccio(Connection conn) {
        super();
        this.conn = conn;
    }

    public void mostrarfaccions() throws SQLException {
        String option = "";
        Scanner sc = new Scanner(System.in);
        ResultSet rs = UtilsSQLite.querySelect(conn, "SELECT * FROM TaulaFaccio;");
        String menuvisual = "";

        while (rs.next()) {
            menuvisual += rs.getInt("id") + ") " + rs.getString("nom") + "\n";
            faccionesnumber.add(rs.getInt("id"));
        }

        while (!option.equals("100")) {

            System.out.println(menuvisual + "100) Atrás\n-->");
            option = sc.nextLine();
            try {
                if (!option.equals("100") && faccionesnumber.contains(Integer.parseInt(option))) {
                    ResultSet newrs = UtilsSQLite.querySelect(conn,
                            "SELECT * FROM Personatge WHERE idFaccio = " + option + ";");
                    mostrartabla(newrs);
                } else {
                    System.out.println("Ha de ser un número dentro de la lista del menú.\n");
                }
            } catch (Exception e) {
                System.out.println("No es una opción valida.\n");
            }
        }
    }

    public void mostrartabla(ResultSet rs) throws SQLException {
        ResultSetMetaData cabecera = rs.getMetaData();
        int espaciado = 20;
        int numColumnas = cabecera.getColumnCount();

        // Imprimir el nombre de las columnas
        for (int i = 1; i <= numColumnas; i++) {
            String nombreColumna = cabecera.getColumnName(i);
            System.out.print(nombreColumna);
            for (int j = 0; j < espaciado - nombreColumna.length(); j++) {
                System.out.print(" ");
            }
            // Agregar cuatro tabulaciones después de cada nombre de columna
        }
        System.out.println();

        while (rs.next()) {
            ArrayList<String> datos = new ArrayList<>();

            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int atac = rs.getInt("atac");
            int defensa = rs.getInt("defensa");
            int idfaccio = rs.getInt("idFaccio");

            datos.add(id + "");
            datos.add(nom);
            datos.add(atac + "");
            datos.add(defensa + "");
            datos.add(idfaccio + "");

            for (String string : datos) {
                System.out.print(string);
                for (int i = 0; i < espaciado-string.length(); i++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}