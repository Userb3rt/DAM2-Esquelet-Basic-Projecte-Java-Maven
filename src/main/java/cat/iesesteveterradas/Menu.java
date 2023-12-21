package cat.iesesteveterradas;

import java.util.Scanner;

public class Menu {
    private String titol00 = "For honor data base";
    private String[] menu00 = { "Mostrat una taula", "Mostrar personatges per facció",
            "Mostrar el millor atacant per facció", "Mostrar el millor defensor per facció", "Sortir" };
    private String option;
    private Scanner sc = new Scanner(System.in);
    private boolean run = true;

    void mostrarmenuprincipal() {
        while (run) {
            System.out.println("---" + titol00 + "---");
            for (int i = 0; i < menu00.length; i++) {
                System.out.println(i + 1 + ") " + menu00[i]);
            }
            System.out.println("-->");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

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
