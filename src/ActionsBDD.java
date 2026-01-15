package src;
/**
 * Interface avec toutes les fonctionnalités correspondant aux différentes options du menu.
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 */

import java.util.List;
import java.util.Scanner;

public interface ActionsBDD {
    
    /**
     * Affichage de tous les programmeurs présents en base comme ceci.
     * 
     * @param listeProgrammeurs         Liste des programmeurs
     */
    void afficherTousLesProgrammeurs (List<Programmeur> listeProgrammeurs);

    /**
     * Renvoie un programmeur pointé par l'ID saisie par l'utilisateur.
     * 
     * @param scanner                   Récupère la saisie utilisateur 
     * @param listeProgrammeurs         Liste des programmeurs
     */
    void afficherUnProgrammeurParId(Scanner scanner, List<Programmeur> listeProgrammeurs);

    /**
     * Supprime le programmeur correspondant à l'ID saisie par l'utilisateur. 
     * 
     * @param scanner                   Récupère la saisie utilisateur
     * @param listeProgrammeurs         Liste des programmeurs
     */
    void supprimerProgrammeurParId(Scanner scanner, List<Programmeur> listeProgrammeurs);

    /**
     * Ajouter un programmeur. 
     * L'utilisateur doit saisie chaque information contenu dans la fiche.
     * 
     * @param scanner                   Récupère les saisies utilisateur
     * @param listeProgrammeurs         Liste des programmeurs
     * @param listeProjets              Liste des projets
     */
    void ajouterProgrammeur(Scanner scanner, List<Programmeur> listeProgrammeurs, List<Projet> listeProjets);

    /**
     * Modifier le salaire d'un programmeur pointé par son ID.
     * 
     * @param scanner                   Récupère les saisie utilisateur
     * @param listeProgrammeurs         Liste des programmeurs
     */
    void modifierSalaire(Scanner scanner, List<Programmeur> listeProgrammeurs);

    /**
     * Afficher la liste des projets. 
     * 
     * @param listeProjets              Liste des projets
     */
    void afficherListeDesProjets(List<Projet> listeProjets);

    /**
     * Récupère le nom du projet et renvoie tous les programmeurs travaillant sur le-dit projet. 
     * 
     * @param scanner                   Récupère les saisies utilisateurs
     * @param listeProgrammeurs         Liste des programmeurs
     * @param listeProjets              Liste des projets
     */
    void afficherProgrammeursDuMemeProjet(Scanner scanner,
                                                         List<Programmeur> listeProgrammeurs,
                                                         List<Projet> listeProjets);

    /*---------------------------------------------------------------------------------------*/

    int lireEntier(Scanner scanner, String message);

    double lireDouble(Scanner scanner, String message);

    String lireTexte(Scanner scanner, String message);

    List<Programmeur> creerProgrammeursDeTest();

    List<Projet> creerProjetsDeTest();

    public int genererNouvelId(List<Programmeur> listeProgrammeurs);

    boolean projetExiste(List<Projet> listeProjets, String nomProjet);
}
