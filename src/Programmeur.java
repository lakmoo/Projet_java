import java.util.List;
import java.util.Locale;

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
        return "Id           : " + id + "\n" +
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

    /**
     * Converts the attributes of a Programmeur object into JSON
     * 
     * @return a String formated to JSON
     */
    public String toJSON() {
        return String.format(Locale.US,
                "{\"id\":%d,\"nom\":\"%s\",\"prenom\":\"%s\",\"salaire\":%.2f}",
                id, nom, prenom, salaire);
    }

    /**
     * Converts an array of Programmer objects into JSON
     * 
     * @param programmeurs the array
     * @return JSON strings
     */
    public static String programmeurToJSON(List<Programmeur> programmeurs) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < programmeurs.size(); i++) {
            json.append(programmeurs.get(i).toJSON());
            if (i < programmeurs.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

}
