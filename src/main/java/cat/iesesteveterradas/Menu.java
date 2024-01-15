package cat.iesesteveterradas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private String titol00 = "For honor data base";
    private String[] menu00 = { "Mostrat una taula", "Mostrar personatges per facció",
            "Mostrar el millor atacant per facció", "Mostrar el millor defensor per facció", "Sortir" };
    private String option;
    private Scanner sc = new Scanner(System.in);
    private boolean run = true;
    Mostrartaula mostrartabla;
    Mostrartaulafaccio mostrartaulafaccio;
    MostrarMatacantfacció mostrarMatacantfacció;
    MostrarMdefensafacció mostrarMdefensafacció;


    void mostrarmenuprincipal(Connection conn) {
        while (run) {
            System.out.println("---" + titol00 + "---");
            for (int i = 0; i < menu00.length; i++) {
                System.out.println(i + 1 + ") " + menu00[i]);
            }
            System.out.println("-->");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    mostrartabla = new Mostrartaula(conn);
                    mostrartabla.menutablas();
                    break;
                case "2":
                    mostrartaulafaccio = new Mostrartaulafaccio(conn);
                    try {
                        mostrartaulafaccio.mostrarfaccions();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    mostrarMatacantfacció = new MostrarMatacantfacció(conn);
                    try {
                        mostrarMatacantfacció.mostrarfaccions();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    mostrarMdefensafacció = new MostrarMdefensafacció(conn);
                    try {
                        mostrarMdefensafacció.mostrarfaccions();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    run = false;
                    System.out.println("Sortint");
                    break;

                default:
                    System.out.println("Introdueix una opció valida.\n");
                    break;
            }
        }

    }
}
