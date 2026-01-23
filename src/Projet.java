package src;
/**
 * Dont les attributs sont identiques aux colonnes de la table Projet.  
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 * 
 * @version Janvier 2026
 */

public class Projet {

    private int id;
    private String intitule;
    private String dateDebut;
    private String dateFinPrevue;
    private String etat;

    /**
     * Constructeur avec paramètres.
     * 
     * @param id                    L'identifiant du projet
     * @param intitule              L'intitulé du projet
     * @param dateDebut             La date de début du projet
     * @param dateFinPrevue         La date de fin prévue du projet
     * @param etat                  L'état du projet
     */
    public Projet(int id, String intitule, String dateDebut, String dateFinPrevue, String etat) {
        this.id = id;
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFinPrevue = dateFinPrevue;
        this.etat = etat;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFinPrevue() {
        return dateFinPrevue;
    }

    public String getEtat() {
        return etat;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations du projet.
     */
    public String toTexteAffichage() {
        return "Id: " + id
                + " | Intitulé: " + intitule
                + " | Début: " + dateDebut
                + " | Fin prévue: " + dateFinPrevue
                + " | Etat: " + etat;
    }
}
