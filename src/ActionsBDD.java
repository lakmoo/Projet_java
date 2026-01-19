package src;
/**
 * Interface avec toutes les fonctionnalités correspondant aux différentes options du menu.
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 * 
 * @version Janvier 2026
 */

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public interface ActionsBDD {
    
    /**
     * Affichage de tous les programmeurs présents dans la base de données.
     * 
     * @throws SQLException         S'il n'arrive pas à lire dans la table des programmeurs
     */
    public void afficherDBProg ();

    /**
     * Renvoie un programmeur pointé par l'ID saisie par l'utilisateur.
     * 
     * @param id                        L'identifiant du programmeur
     * @throws SQLException             Si le programmeur est introuvable
     */
    public void afficherProgParID (Scanner scanner);

    /**
     * Supprime le programmeur correspondant à l'ID saisie par l'utilisateur. 
     * 
     * @param scanner                   Récupère la saisie utilisateur
     * @throws SQLException             S'il y a une erreur lors de la suppression
     */
    public void supprimerProgrammeur (Scanner scanner);

    /**
     * Ajoute un programmeur. 
     * L'utilisateur doit saisir chaque information demandé dans la fiche.
     * 
     * @param scanner                   Récupère les saisies utilisateur
     * @throws SQLException             S'il n'arrive pas à insérer
     */
    public void ajouterProg (Scanner scanner);

    /**
     * Modifier le salaire d'un programmeur pointé par son ID.
     * 
     * @param scanner                   Récupère les saisies utilisateur
     * @throws SQLException             S'il y a une erreur à la modification
     */
    public void modifierSalaire (Scanner scanner);

    /**
     * Afficher la liste des projets de la base de données. 
     * 
     * @throws SQLException             S'il y a une erreur à la lecture
     */
    public void afficherListeProjets ();

    /**
     * Récupère le nom du projet et renvoie tous les programmeurs travaillant sur le-dit projet. 
     * 
     * @param scanner                   Récupère les saisies utilisateurs
     * @throws SQLException             S'il n'arrive pas à lire les tables
     */
    public void afficherProgMemeProjet(Scanner scanner);

    /* ====================================================================================================================== */

    /**
     * Modifie la prime d'un programmeur pointé par son ID.
     * 
     * @param scanner                   Récupère les saisies utilisateur
     * @throws SQLException             S'il y a une erreur à la modification
     */
    public void modifierPrime (Scanner scanner);

    /**
     * Permet d'ajouter un nouveau projet à la table. 
     * 
     * @param scanner                   Récupère les saisies utilisateur
     * @throws SQLException             S'il n'arrive pas à modifier
     */
    public void ajouterProjet (Scanner scanner);

    /**
     * Permet de supprimer un projet de la table. 
     * 
     * @param scanner                   Récupère les saisies utilisateur
     * @throws SQLException             S'il n'arrive pas à supprimer
     */
    public void supprimerProjet (Scanner scanner);

    /**
     * Retourne une liste des programmeurs contenu dans la base de données "Programmeurs".
     * 
     * @return ArrayList                Une liste de programmeurs
     * @throws SQLException             Si la liste n'a pas pu être récupéré
     */
    public List<Programmeur> getProgrammeurs();
}
