import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continuerProgramme = true;

        //Je crée une list de pgrameur qui appelle la methode creerProgrammeursDeTest()
        List<Programmeur> listeProgrammeurs = creerProgrammeursDeTest();

        while (continuerProgramme) {

            afficherMenu();

            int choix = lireEntier(scanner, "Votre choix : ");

            if (choix == 1) {
                afficherTousLesProgrammeurs(listeProgrammeurs);

            } else if (choix == 2) {
                afficherUnProgrammeurParId(scanner, listeProgrammeurs);

            } else if (choix == 3) {
                supprimerProgrammeurParId(scanner, listeProgrammeurs);

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


    //l'excpetion pour empêcher la saisie de String
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


    // création des programmeurs (pas de bdd pour l'instant)
    private static List<Programmeur> creerProgrammeursDeTest() {
        List<Programmeur> listeProgrammeurs = new ArrayList<>();

        listeProgrammeurs.add(new Programmeur(
                1, "Torvalds", "Linus", "2 avenue Linux Git", "linuxroot",
                "Didier Achvar", "salsa", 1969, 2170.0, 50.0
        ));

        listeProgrammeurs.add(new Programmeur(
                2, "Stroustrup", "Bjarne", "294 rue C++", "c++1",
                "Karim Lahlou", "Voyages", 1950, 2466.0, 80.0
        ));

        listeProgrammeurs.add(new Programmeur(
                3, "Gosling", "James", "3 bvd JVM", "javapapa",
                "Jacques Augustin", "Peinture", 1955, 1987.0, 10.0
        ));

        return listeProgrammeurs;
    }


    //option1 : afficher otus les programmeurs
    private static void afficherTousLesProgrammeurs(List<Programmeur> listeProgrammeurs) {
        if (listeProgrammeurs.isEmpty()) {
            System.out.println("Aucun programmeur.");
            return;
        }

        for (Programmeur programmeur : listeProgrammeurs) {
            System.out.println(programmeur.toString());
        }
    }

    //opt 2 : afficher les programmeurs par id
    private static void afficherUnProgrammeurParId(Scanner scanner, List<Programmeur> listeProgrammeurs) {
        int idProgrammeur = lireEntier(scanner, "Id du programmeur : ");

        Programmeur programmeurTrouve = chercherProgrammeurParId(listeProgrammeurs, idProgrammeur);

        if (programmeurTrouve == null) {
            System.out.println("Id introuvable.");
        } else {
            System.out.println(programmeurTrouve.toString());
        }
    }

    //opt3 : supp programmeur par id (3 tentatives)
    private static void supprimerProgrammeurParId(Scanner scanner, List<Programmeur> listeProgrammeurs) {

        int tentativesMax = 3;
        int tentatives = 0;

        while (tentatives < tentativesMax) {

            int idProgrammeur = lireEntier(scanner, "Id du programmeur à supprimer : ");
            Programmeur programmeurTrouve = chercherProgrammeurParId(listeProgrammeurs, idProgrammeur);

            if (programmeurTrouve == null) {
                tentatives++;
                System.out.println("Suppression KO. Id introuvable.");
            } else {
                listeProgrammeurs.remove(programmeurTrouve);
                System.out.println("SUPPRESSION REUSSIE !!!");
                return;
            }
        }

        System.out.println("Trop de tentatives. Retour au menu.");
    }


    //parcours la liste et renvoie le programmeur trouvé
    private static Programmeur chercherProgrammeurParId(List<Programmeur> listeProgrammeurs, int idProgrammeur) {
        for (Programmeur programmeur : listeProgrammeurs) {
            if (programmeur.getId() == idProgrammeur) {
                return programmeur;
            }
        }
        return null;
    }

}
