package src;

import java.util.Scanner;

/**
 * Permet de gérer la saisie de l'utilisateur et le déclenchement des actions correspondant
 * au choix saisi. 
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 * 
 * @version Janvier 2026
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
     * Affiche toutes les options possible du menu.
     * 
     * Un "+" indique qu'il s'agit d'une fonctionnalité additionnelle.  
     */
    public void afficherMenu () {
        System.out.println();
        System.out.println("**************** MENU ****************");
        System.out.println("1.  Afficher tous les programmeurs");
        System.out.println("2.  Chercher un programmeur");
        System.out.println("3.  Supprimer un programmeur");
        System.out.println("4.  Ajouter un programmeur");
        System.out.println("5.  Modifier le salaire");
        System.out.println("6.  Modifier la prime");                          // +
        System.out.println("7.  Afficher la liste des projets");
        System.out.println("8.  Programmeurs du même projet");
        System.out.println("9.  Ajouter un projet");                          // +
        System.out.println("10. Supprimer un projet");                       // +
        System.out.println("11. Quitter le programme");
        System.out.println("*************************************");
        System.out.println();
    }

    /**
     * Appels les fonctions selon le choix de l'utilisateur. 
     */
    public void choixMenu () {

        Scanner scanner = new Scanner(System.in);
        boolean continuerProgramme = true;

        while (continuerProgramme) {

            afficherMenu();
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1 -> action.afficherDBProg();

                case 2 -> action.afficherProgParID(scanner);

                case 3 -> action.supprimerProgrammeur(scanner);

                case 4 -> action.ajouterProg(scanner);

                case 5 -> action.modifierSalaire(scanner);

                case 6 -> action.modifierPrime(scanner);

                case 7 -> action.afficherListeProjets();

                case 8 -> action.afficherProgMemeProjet(scanner);

                case 9 -> action.ajouterProjet(scanner);

                case 10 -> action.supprimerProjet(scanner);

                case 11 -> {
                    System.out.println("Fermeture du programme.");
                    continuerProgramme = false;
                }

                default -> System.out.println("Choix invalide.");
            }
        }
        System.out.println();
    }
}