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
                        mostrarpersonaje(conn, rs);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "1":
                    try {
                        ResultSet rs = UtilsSQLite.querySelect(conn,
                                "SELECT * FROM " + cabecera.get(Integer.parseInt(option)) + ";");
                        mostrarfaccion(conn, rs);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }

        }

    }

    void mostrarpersonaje(Connection conn, ResultSet rs) throws SQLException {
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

    }
}
