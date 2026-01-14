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

    public Programmeur(int id, String nom, String prenom, String adresse, String pseudo,
                       String responsable, String hobby, int anneeNaissance,
                       double salaire, double prime) {
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


}
