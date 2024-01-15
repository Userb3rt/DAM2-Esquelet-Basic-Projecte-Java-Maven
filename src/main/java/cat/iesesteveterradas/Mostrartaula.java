package cat.iesesteveterradas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mostrartaula {

    private ArrayList<String> cabecera = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private String option = "";
    Connection conn;

    public Mostrartaula(Connection conn) {
        super();
        this.conn = conn;
    }

    void menutablas() {
        while (!option.equals("5")) {
            cabecera = UtilsSQLite.listTables(conn);
            for (int index = 0; index < cabecera.size(); index++) {
                System.out.println(index + ") " + cabecera.get(index));
            }
            System.out.println("5) Sortir\n-->");
            option = sc.nextLine();
            switch (option) {
                case "0":
                    try {
                        ResultSet rs = UtilsSQLite.querySelect(conn,
                                "SELECT * FROM " + cabecera.get(Integer.parseInt(option)) + ";");
                        mostrarpersonaje(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "1":
                    try {
                        ResultSet rs = UtilsSQLite.querySelect(conn,
                                "SELECT * FROM " + cabecera.get(Integer.parseInt(option)) + ";");
                        mostrarfaccion(conn, rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }

        }

    }

    void mostrarpersonaje(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Obtener el número de columnas
        int numColumnas = metaData.getColumnCount();

        // Imprimir el nombre de las columnas
        for (int i = 1; i <= numColumnas; i++) {
            String nombreColumna = metaData.getColumnName(i);
            System.out.print(nombreColumna);

            // Agregar cuatro tabulaciones después de cada nombre de columna
            for (int j = 0; j < 4; j++) {
                System.out.print("\t");
            }
        }
        System.out.println();
        while (rs.next()) {
            // Obtener datos de cada columna por nombre o índice
            ArrayList<String> lista = new ArrayList<>();

            // Ejemplo utilizando el nombre de la columna
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int atac = rs.getInt("atac");
            int defensa = rs.getInt("defensa");
            int idfaccio = rs.getInt("idFaccio");

            lista.add(id + "");
            lista.add(nom);
            lista.add(atac + "");
            lista.add(defensa + "");
            lista.add(idfaccio + "");

            for (String string : lista) {
                System.out.print(string + "\t\t\t\t");
            }
            System.out.println("");

        }

    }

    void mostrarfaccion(Connection conn, ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Obtener el número de columnas
        int numColumnas = metaData.getColumnCount();

        // Imprimir el nombre de las columnas
        for (int i = 1; i <= numColumnas; i++) {
            String nombreColumna = metaData.getColumnName(i);
            System.out.print(nombreColumna);

            // Agregar cuatro tabulaciones después de cada nombre de columna
            for (int j = 0; j < 4; j++) {
                System.out.print("\t");
            }
        }
        System.out.println();
        while (rs.next()) {
            // Obtener datos de cada columna por nombre o índice

            // Ejemplo utilizando el nombre de la columna
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String resum = rs.getString("resum");

            // Puedes hacer algo con los datos, por ejemplo, imprimirlos
            System.out.print(id + "\t\t\t\t" + nom + "\t\t\t");
            int contador = 0;
            for (int i = 0; i < resum.length(); i++) {
                System.err.print(resum.charAt(i));
                contador++;
                if (contador == 40) {
                    System.out.print("\n\t\t\t\t\t\t\t\t");
                    contador = 0;
                }
            }
            System.out.println("\n");
        }

    }
}
