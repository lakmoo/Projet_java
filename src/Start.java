import java.util.Scanner;

public class Start {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continuerProgramme = true;

        while (continuerProgramme) {

            afficherMenu();

            int choix = lireEntier(scanner, "Votre choix : ");

            if (choix == 1) {
                System.out.println("Option 1 : afficher tous les programmeurs (à faire)");

            } else if (choix == 2) {
                System.out.println("Option 2 : afficher un programmeur (à faire)");

            } else if (choix == 3) {
                System.out.println("Option 3 : supprimer un programmeur (à faire)");

            } else if (choix == 4) {
                System.out.println("Option 4 : ajouter un programmeur (à faire)");

            } else if (choix == 5) {
                System.out.println("Option 5 : modifier le salaire (à faire)");

            } else if (choix == 6) {
                System.out.println("Option 6 : afficher la liste des projets (à faire)");

            } else if (choix == 7) {
                System.out.println("Option 7 : programmeurs du même projet (à faire)");

            } else if (choix == 8) {
                System.out.println("Fermeture du programme.");
                continuerProgramme = false;

            } else {
                System.out.println("Choix invalide.");
            }

            System.out.println();
        }
    }

    private static void afficherMenu() {
        System.out.println("********* MENU *************");
        System.out.println("1. Afficher tous les programmeurs");
        System.out.println("2. Afficher un programmeur");
        System.out.println("3. Supprimer un programmeur");
        System.out.println("4. Ajouter un programmeur");
        System.out.println("5. Modifier le salaire");
        System.out.println("6. Afficher la liste des projets");
        System.out.println("7. Programmeurs du même projet");
        System.out.println("8. Quitter le programme");
    }


    private static int lireEntier(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine();

            try {
                return Integer.parseInt(saisie);
            } catch (NumberFormatException exception) {
                System.out.println("Veuillez saisir un nombre entier.");
            }
        }
    }
}
