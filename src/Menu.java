package src;

import java.util.Scanner;

/**
 * Permet de gérer la saisie de l'utilisateur et le déclenchement des actions correspondant
 * au choix saisi. 
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 */

public class Menu {

    private ActionsBDDImpl action;

    /**
     * Constructeur avec paramètres. 
     * 
     * @param listeProgrammeurs
     * @param listeProjets
     */
    public Menu() {
        this.action = new ActionsBDDImpl();
    }

    /**
     * Affiche toutes les options possible du menu
     */
    public void afficherMenu () {
        System.out.println();
        System.out.println("********* MENU *************");
        System.out.println("1. Afficher tous les programmeurs");
        System.out.println("2. Afficher un programmeur");
        System.out.println("3. Supprimer un programmeur");
        System.out.println("4. Ajouter un programmeur");
        System.out.println("5. Modifier le salaire");
        System.out.println("6. Afficher la liste des projets");
        System.out.println("7. Programmeurs du même projet");
        System.out.println("8. Quitter le programme");
        System.out.println();
    }

    public void choixMenu () {

        Scanner scanner = new Scanner(System.in);
        boolean continuerProgramme = true;

        while (continuerProgramme) {
            afficherMenu();
            int choix = action.lireEntier(scanner, "    Votre choix : ");

            switch (choix) {

                case 1 -> action.afficherDBProg();

                case 2 -> action.afficherProgParID(choix);

                case 3 -> action.supprimerProgrammeur(scanner);

                case 4 -> action.insererDBProg(scanner);

                case 5 -> action.modifierSaisie(scanner);

                //case 6 -> action.afficherListeDesProjets(listeProjets);

                //case 7 -> action.afficherProgrammeursDuMemeProjet(scanner, listeProgrammeurs, listeProjets);

                case 8 -> {
                    System.out.println("Fermeture du programme.");
                    continuerProgramme = false;
                }

                default -> System.out.println("Choix invalide.");
            }
        }

        System.out.println();
    }
}