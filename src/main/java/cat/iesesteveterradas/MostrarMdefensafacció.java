package cat.iesesteveterradas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MostrarMdefensafacció
 */
public class MostrarMdefensafacció {
    private ArrayList<Integer> faccionesnumber = new ArrayList<>();
    private ArrayList<String> faccionesname = new ArrayList<>();
    Connection conn;

    public MostrarMdefensafacció(Connection conn) {
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
            faccionesname.add(rs.getString("nom"));
        }

        while (!option.equals("100")) {

            System.out.println(menuvisual + "100) Atrás\n-->");
            option = sc.nextLine();
            try {
                if (!option.equals("100") && faccionesnumber.contains(Integer.parseInt(option))) {
                    ResultSet newrs = UtilsSQLite.querySelect(conn,
                            "SELECT * FROM Personatge WHERE idFaccio = " + option + " ORDER BY defensa DESC LIMIT 1;");
                    mostrarresultado(newrs, faccionesname.get(Integer.parseInt(option) - 1));
                } else {
                    System.out.println("Ha de ser un número dentro de la lista del menú.\n");
                }
            } catch (Exception e) {
                System.out.println("No es una opción valida.\n");
            }
        }
    }

    public void mostrarresultado(ResultSet rs, String nombref) throws SQLException {
        System.out.println("Personaje con más defensa de la facción " + nombref + " és:" + rs.getString("nom")
                + " con una defensa de: " + rs.getInt("atac"));
        System.out.println();
    }

}