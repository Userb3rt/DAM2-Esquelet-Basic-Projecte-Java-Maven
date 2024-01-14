package cat.iesesteveterradas;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UtilsSQLite {

    // Establece la conexión con la base de datos SQLite utilizando el archivo
    // especificado.
    // Imprime información sobre el controlador de la base de datos.
    public static Connection connect(String filePath) {
        Connection conn = null;

        try {
            String url = "jdbc:sqlite:" + filePath;
            conn = DriverManager.getConnection(url);

            if (conn != null) {
                // Imprime el nombre del controlador de la base de datos.
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("BBDD driver: " + meta.getDriverName());
            }

            // Imprime un mensaje indicando que la base de datos está conectada.
            System.out.println("BBDD SQLite conectada");
        } catch (SQLException e) {
            // Imprime detalles de cualquier error de conexión.
            e.printStackTrace();
        }

        return conn;
    }

    // Cierra la conexión con la base de datos si no es nula.
    // Imprime un mensaje indicando que la base de datos ha sido desconectada.
    public static void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("DDBB SQLite desconectada");
            }
        } catch (SQLException ex) {
            // Imprime detalles de cualquier error al cerrar la conexión.
            System.out.println(ex.getMessage());
        }
    }

    // Obtiene una lista de nombres de tablas en la base de datos (excluyendo
    // sqlite_schema y sqlite_sequence) y la devuelve como un ArrayList.
    public static ArrayList<String> listTables(Connection conn) {
        ArrayList<String> list = new ArrayList<>();

        try {
            // Obtiene el ResultSet con información sobre las tablas.
            ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

            // Itera sobre el ResultSet y agrega los nombres de las tablas a la lista
            // (excluyendo sqlite_schema y sqlite_sequence).
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (!tableName.equalsIgnoreCase("sqlite_schema") && !tableName.equalsIgnoreCase("sqlite_sequence")) {
                    list.add(tableName);
                }
            }
        } catch (SQLException ex) {
            // Imprime detalles de cualquier error al obtener las tablas.
            System.out.println(ex.getMessage());
        }

        return list;
    }

    // Ejecuta consultas SQL de actualización (INSERT, UPDATE, DELETE) y devuelve el
    // número de filas afectadas.
    public static int queryUpdate(Connection conn, String sql) {
        int result = 0;

        try {
            Statement stmt = conn.createStatement();

            // Ejecuta la consulta de actualización y obtiene el número de filas afectadas.
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // Imprime detalles de cualquier error durante la ejecución de la consulta.
            e.printStackTrace();
        }

        return result;
    }

    // Ejecuta consultas SQL de selección y devuelve un ResultSet con los
    // resultados.
    public static ResultSet querySelect(Connection conn, String sql) {
        ResultSet rs = null;

        try {
            Statement stmt = conn.createStatement();

            // Ejecuta la consulta de selección y obtiene el ResultSet con los resultados.
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            // Imprime detalles de cualquier error durante la ejecución de la consulta.
            e.printStackTrace();
        }

        return rs;
    }
}
