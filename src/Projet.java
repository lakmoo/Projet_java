package src;
/**
 * Dont les attributs sont identiques aux colonnes de la table Projet.  
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 */

public class Projet {

    private int id;
    private String intitule;
    private String dateDebut;
    private String dateFinPrevue;
    private String etat;

    public Projet(int id, String intitule, String dateDebut, String dateFinPrevue, String etat) {
        this.id = id;
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFinPrevue = dateFinPrevue;
        this.etat = etat;
    }

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

    public String toTexteAffichage() {
        return "Id: " + id
                + " | Intitulé: " + intitule
                + " | Début: " + dateDebut
                + " | Fin prévue: " + dateFinPrevue
                + " | Etat: " + etat;
    }
}
