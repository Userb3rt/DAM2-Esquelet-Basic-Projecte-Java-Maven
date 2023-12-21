package cat.iesesteveterradas;

import java.io.File;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        String basePath = System.getProperty("user.dir") + "\\data\\";
        String filePath = basePath + "database.db";

        File fDatabase = new File(filePath);
        if (!fDatabase.exists()) {
            initDatabase(filePath);
        }else{
            UtilsSQLite.connect(filePath);
        }

        Menu m = new Menu();
        m.mostrarmenuprincipal();

    }

    static void initDatabase(String filePath) {
        // Connectar (crea la BBDD si no existeix)
        Connection conn = UtilsSQLite.connect(filePath);

        // Esborrar la taula (per si existeix)
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS TaulaFaccio;");
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS Personatge;");

        // Crear una nova taula TaulaFaccio
        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS TaulaFaccio ("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "nom VARCHAR(15),"
                + "resum VARCHAR(500));");

        // Crear una nova taula Personatge
        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS Personatge ("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "nom VARCHAR(15),"
                + "atac DECIMAL(10,2),"
                + "defensa DECIMAL(10,2),"
                + "idFaccio INT,"
                + "FOREIGN KEY (idFaccio) REFERENCES TaulaFaccio(id));");

        // Afegir elements a una taula faccio
        UtilsSQLite.queryUpdate(conn,
                "INSERT INTO TaulaFaccio (nom, resum) VALUES ('Caballeros', 'Los caballeros de Ashfeld son paradigmas del poder. La Legión de Hierro los envió para llevar la paz a esas tierras y, desde entonces, disfrutan de la libertad y han hecho de Ashfeld su hogar. Creen que muchas (si no todas) de las antiguas ruinas que cubren sus tierras fueron construidas por el Gran Imperio, precursor de la Legión de Hierro.');");
        UtilsSQLite.queryUpdate(conn,
                "INSERT INTO TaulaFaccio (nom, resum) VALUES ('Vikings', 'Los vikingos son los maestros indiscutibles del mar y el agua. Cuando esta ruidosa nación se reúne en enormes flotas de barcos con cabeza de dragón, solo verlos inspira terror, y son casi imparables.');");
        UtilsSQLite.queryUpdate(conn,
                "INSERT INTO TaulaFaccio (nom, resum) VALUES ('Samurais', 'La historia no ha tratado bien a los samuráis. Originarios de una tienda allende los mares, cuentan la historia de un emperador y una patria que desaparecieron entre el mar y el fuego. Casi un milenio después, la nación nómada ha dejado de errar y ha construido un nuevo imperio cerca de las tierras reclamadas por los vikingos y por las que disputan los caballeros.');");

        // Desconnectar
    }

}
