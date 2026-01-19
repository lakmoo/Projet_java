package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Classe Programmeur identique à la table.  
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 * 
 * @version Janvier 2026
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

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
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

    /**
     * Convertit une liste d'objets Programmeurs en un fichier JSON
     * 
     * @param listeProgrammeurs Liste des programmeurs
     * @return une chaîne de caractères contenant les progrommaeurs
     */
    public static String programmeursEnJSON(List<Programmeur> listeProgrammeurs) {
        // créer une chaîne de caractère modifiable
        StringBuilder json = new StringBuilder();
        json.append("[");

        // ajoute chaque Programmeur dans la chaîne de caractères(json)
        for (int i = 0; i < listeProgrammeurs.size(); i++) {
            Programmeur emp = listeProgrammeurs.get(i);

            json.append("{");
            json.append("\"id\":").append(emp.getId()).append(",");
            json.append("\"nom\":\"").append(escapeJSON(emp.getNom())).append("\",");
            json.append("\"prenom\":\"").append(escapeJSON(emp.getPrenom())).append("\",");
            json.append("\"salaire\":").append(String.format(Locale.US, "%.2f", emp.getSalaire()));
            json.append("}");

            if (i < listeProgrammeurs.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");
        return json.toString();
    }

    /**
     * Créer un fichier JSON contennat les informations sur les programmeurs
     * 
     * @param listeProgrammeurs Liste des programmeurs
     * @param filename          Nom du fichier JSON
     */
    public static void creerFichierJSON(List<Programmeur> listeProgrammeurs, String filename) {
        String json = programmeursEnJSON(listeProgrammeurs);

        try (FileWriter file = new FileWriter(filename)) {
            file.write(json);
            file.flush();
            System.out.println("Données sauvegardées dans le fichier : " + filename);
        } catch (IOException e) {
            System.err.println("Erreur JSON :( : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Renplace les caractères spéciaux pour qu'il corresponde à la syntaxe JSON
     * 
     * @param str une chaîne de caractère JSON
     * @return la chaîne de caractère modifiée
     */
    private static String escapeJSON(String str) {
        if (str == null)
            return "";
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
