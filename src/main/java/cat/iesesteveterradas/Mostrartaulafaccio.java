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
            menuvisual += rs.getInt("id")+") "+rs.getString("nom")+"\n";
            faccionesnumber.add(rs.getInt("id"));
        }

        while (!option.equals("100")) {

            System.out.println(menuvisual+"100) Atrás\n-->");
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

    public void mostrartabla(ResultSet rs) throws SQLException{
        ResultSetMetaData cabecera = rs.getMetaData();
    }
}