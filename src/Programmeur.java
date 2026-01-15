package src;
import java.util.List;

/**
 * Classe Programmeur identique à la table.  
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 */

public class Programmeur {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String pseudo;
    private String responsable;
    private String hobby;
    private int anneeNaissance;
    private double salaire;
    private double prime;
    private String nomProjet;

    public Programmeur() {
        this.id = 0;
        this.nom = "";
        this.prenom = "";
        this.adresse = "";
        this.pseudo = "";
        this.responsable = "";
        this.hobby = "";
        this.anneeNaissance = 0;
        this.salaire = 0.0;
        this.prime = 0.0;
        this.nomProjet = "";
    }

    public Programmeur(int id, String nom, String prenom, String adresse, String pseudo,
                       String responsable, String hobby, int anneeNaissance,
                       double salaire, double prime, String nomProjet) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.responsable = responsable;
        this.hobby = hobby;
        this.anneeNaissance = anneeNaissance;
        this.salaire = salaire;
        this.prime = prime;
        this.nomProjet = nomProjet;
    }

    public int getId() {
        return id;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    @Override
    public String toString() {
        return
                "Id           : " + id + "\n" +
                        "Nom          : " + nom + "\n" +
                        "Prénom       : " + prenom + "\n" +
                        "Adresse      : " + adresse + "\n" +
                        "Pseudo       : " + pseudo + "\n" +
                        "Responsable  : " + responsable + "\n" +
                        "Hobby        : " + hobby + "\n" +
                        "Naissance    : " + anneeNaissance + "\n" +
                        "Salaire      : " + salaire + "\n" +
                        "Prime        : " + prime + "\n" +
                        "----------------------------------------";
    }

    public static Programmeur chercherProgrammeurParId(List<Programmeur> listeProgrammeurs, int idProgrammeur) {
        for (Programmeur programmeur : listeProgrammeurs) {
            if (programmeur.getId() == idProgrammeur) {
                return programmeur;
            }
        }
        return null;
    }
}
