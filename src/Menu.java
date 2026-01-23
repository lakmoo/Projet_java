package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate; 
import java.time.LocalTime;

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
        System.out.println("3.  Enregistrer les programmeurs dans un fichier");   // +
        System.out.println("4.  Supprimer un programmeur");
        System.out.println("5.  Ajouter un programmeur");
        System.out.println("6.  Modifier le salaire");
        System.out.println("7.  Modifier la prime");                          // +
        System.out.println("8.  Afficher la liste des projets");
        System.out.println("9.  Programmeurs du même projet");
        System.out.println("10. Ajouter un projet");                          // +
        System.out.println("11. Supprimer un projet");                       // +
        System.out.println("12. Enregistrer l'historique des actions dans un fichier");      // +
        System.out.println("13. Quitter le programme");
        System.out.println("*************************************");
        System.out.println();
    }

    /**
     * Appels les fonctions selon le choix de l'utilisateur. 
     */
    public void choixMenu (ArrayList<Integer> listeActions) {

        Scanner scanner = new Scanner(System.in);
        boolean continuerProgramme = true;

        while (continuerProgramme) {

            afficherMenu();
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            saveHistorique(choix, listeActions);

            switch (choix) {

                case 1 -> action.afficherDBProg();

                case 2 -> action.afficherProgParID(scanner);

                case 3 -> action.ProgToFile();

                case 4 -> action.supprimerProgrammeur(scanner);

                case 5 -> action.ajouterProg(scanner);

                case 6 -> action.modifierSalaire(scanner);

                case 7 -> action.modifierPrime(scanner);

                case 8 -> action.afficherListeProjets();

                case 9 -> action.afficherProgMemeProjet(scanner);

                case 10 -> action.ajouterProjet(scanner);

                case 11 -> action.supprimerProjet(scanner);

                case 12 -> logToFile(listeActions);

                case 13 -> {
                    System.out.println("Fermeture du programme.");
                    continuerProgramme = false;
                }

                default -> System.out.println("Choix invalide.");
            }
        }
        System.out.println();
    }

    /**
     * Sauvegarde l'historique des actions de l'utilisateur dans une liste d'entiers.
     * 
     * @param choix             L'action choisie par l'utilisateur
     * @param listeActions      La liste des actions effectuées
     * @return                  La liste des actions mise à jour
     */
    public ArrayList<Integer> saveHistorique (int choix, ArrayList<Integer> listeActions) {
        switch (choix) {
                case 1 -> listeActions.add(choix);
                case 2 -> listeActions.add(choix);
                case 3 -> listeActions.add(choix);
                case 4 -> listeActions.add(choix);
                case 5 -> listeActions.add(choix);
                case 6 -> listeActions.add(choix);
                case 7 -> listeActions.add(choix);
                case 8 -> listeActions.add(choix);
                case 9 -> listeActions.add(choix);
                case 10 -> listeActions.add(choix);
                case 11 -> listeActions.add(choix);
                case 12 -> listeActions.add(choix);
                default -> listeActions.add(0);
            }
        return listeActions;
    }

    /**
     * Enregistre l'historique des actions de l'utilisateur dans un fichier texte appelé
     * "historique.txt".
     * 
     * @param listeActions      La liste des actions effectuées
     */
    public void logToFile (ArrayList<Integer> listeActions) {
        try {
            File histActions = new File ("historique.txt");
            PrintWriter out = new PrintWriter (new FileWriter(histActions, true));
            out.append("\n--- Historique du ").append(LocalDate.now().toString())
            .append("à ").append(LocalTime.now().toString()).append(" ---\n");
            for (Integer action : listeActions) {
                switch (action) {
                    case 0 -> out.append("  Choix invalide\n");
                    case 1 -> out.append("1.  Afficher tous les programmeurs\n");
                    case 2 -> out.append("2.  Chercher un programmeur\n");
                    case 3 -> out.append("3.  Enregistrer les programmeurs dans un fichier\n");
                    case 4 -> out.append("4.  Supprimer un programmeur\n");
                    case 5 -> out.append("5.  Ajouter un programmeur\n");
                    case 6 -> out.append("6.  Modifier le salaire\n");
                    case 7 -> out.append("7.  Modifier la prime\n");
                    case 8 -> out.append("8.  Afficher la liste des projets\n");
                    case 9 -> out.append("9.  Programmeurs du même projet\n");
                    case 10 -> out.append("10. Ajouter un projet\n");
                    case 11 -> out.append("11. Supprimer un projet\n");
                    case 12 -> out.append("12. Enregistrer l'historique des actions dans un fichier\n");
                    default -> {}
                }
            }
            out.close();
            System.out.println("Historique des actions enregistré dans le fichier 'historique.txt'");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'écriture dans le fichier historique : " + e.getMessage());
        }
    }
}