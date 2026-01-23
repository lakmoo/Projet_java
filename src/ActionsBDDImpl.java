package src;
/**
 * Continet les implémentations des méthodes déclarées dans ActionsBDD.
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 * 
 * @version Janvier 2026
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class ActionsBDDImpl implements ActionsBDD {
    

    public void afficherDBProg () {
        String sql = "SELECT * FROM Programmeurs";

        try (Connection conn = Database.getConnexion(); 
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

                if (rs.getString("nom") == null) {
                    System.out.println ("   La base de donnée est vide.");
                }
                while (rs.next()) {
                    System.out.println("ID : " + rs.getInt("id"));
                    System.out.println("Nom : " + rs.getString("nom"));
                    System.out.println("Prénom : " + rs.getString("prenom"));
                    System.out.println("Adresse : " + rs.getString("adresse"));
                    System.out.println("Pseudo : " + rs.getString("pseudo"));
                    System.out.println("Responsable : " + rs.getString("responsable"));
                    System.out.println("Hobby : " + rs.getString("hobby"));
                    System.out.println("Naissance : " + rs.getInt("anNaissance"));
                    System.out.println("Salaire : " + rs.getDouble("salaire"));
                    System.out.println("Prime : " + rs.getDouble("prime"));
                    System.out.println("---------------------------------");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la lecture des programmeurs : " + e.getMessage());
                e.printStackTrace();
            }
    }


    public void afficherProgParID (Scanner scanner) {
        System.out.print("Entrez l'ID du programmeur à chercher : ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        String sql = "SELECT * FROM Programmeurs WHERE id = ?";

        try (Connection conn = Database.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println();
                    System.out.println("Identifiant : " + rs.getInt("id"));
                    System.out.println("Nom : " + rs.getString("nom")); 
                    System.out.println("Prénom : " + rs.getString("prenom"));
                    System.out.println("Adresse : " + rs.getString("adresse"));
                    System.out.println("Pseudo : " + rs.getString("pseudo"));
                    System.out.println("Responsable : " + rs.getString("responsable"));
                    System.out.println("Hobby : " + rs.getString("hobby")); 
                    System.out.println("Année de naissance : " + rs.getInt("anNaissance"));
                    System.out.println("Salaire : " + rs.getDouble("salaire"));
                    System.out.println("Prime : " + rs.getDouble("prime"));
                    System.out.println("---------------------------------");
                } else {
                    System.out.println("Programmeur introuvable.");
                }
             } catch (SQLException e) {
                e.printStackTrace();
             }
    }


    public void supprimerProgrammeur (Scanner scanner) {
        System.out.print("Entrez l'ID du programmeur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        String sql = "DELETE FROM Programmeurs WHERE id = ?";

        try (Connection conn = Database.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);

                int lignesSupprimes = stmt.executeUpdate();

                if (lignesSupprimes > 0) {
                    System.out.println("Programmeur supprimé avec succès !");
                } else {
                    System.out.println("Aucun programmeur trouvé avec l'ID " + id);
                }
             } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression : "+ e.getMessage());
             }
    }


    public void ajouterProg (Scanner scanner) {
        System.out.print(" Nom : "); 
        String nom = scanner.nextLine(); 

        System.out.print(" Prénom : "); 
        String prenom = scanner.nextLine(); 

        System.out.print(" Adresse : "); 
        String adresse = scanner.nextLine(); 

        System.out.print(" Pseudo : "); 
        String pseudo = scanner.nextLine(); 

        System.out.print(" Responsable : "); 
        String responsable = scanner.nextLine(); 

        System.out.print(" Hobby : "); 
        String hobby = scanner.nextLine(); 

        System.out.print(" Année de naissance : "); 
        int anNaissance = scanner.nextInt(); 

        System.out.print(" Salaire : ");
        double salaire = scanner.nextDouble(); 

        System.out.print(" Prime : "); 
        double prime = scanner.nextDouble(); 
        scanner.nextLine();

        System.out.print(" Nom du projet (existants: Site Web, Application Mobile): "); 
        String nomProjet = scanner.nextLine();

        String sqlProjet = "SELECT id FROM Projet WHERE intitule = ?";
        String sqlRequete = "INSERT INTO Programmeurs (nom, prenom, adresse, pseudo, responsable, hobby, anNaissance, salaire, prime, id_projet) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnexion(); 
             PreparedStatement ps = conn.prepareStatement(sqlProjet)) {

            ps.setString(1, nomProjet);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Projet inconnu. Programmeur non ajouté.");
                return;
            }

            int idProjet = rs.getInt("id");

            try (PreparedStatement psProg = conn.prepareStatement(sqlRequete)) {
                psProg.setString(1, nom);
                psProg.setString(2, prenom);
                psProg.setString(3, adresse);
                psProg.setString(4, pseudo);
                psProg.setString(5, responsable);
                psProg.setString(6, hobby);
                psProg.setInt(7, anNaissance);
                psProg.setDouble(8, salaire);
                psProg.setDouble(9, prime);
                psProg.setInt(10, idProjet);

                psProg.executeUpdate();
                System.out.println("Programmeur ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }


    public void modifierSalaire (Scanner scanner) {
        int tentative = 0;
        int TENTATIVES_MAX = 3;

        while (tentative < TENTATIVES_MAX) {

            System.out.print("ID du programmeur : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            // Vérifier si le programmeur existe
            String sqlCheck = "SELECT id FROM Programmeurs WHERE id = ?";
            String sqlUpdate = "UPDATE Programmeurs SET salaire = ? WHERE id = ?";

            try (Connection conn = Database.getConnexion();
                 PreparedStatement stmt = conn.prepareStatement(sqlCheck)) {

                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();

                    if (!rs.next()) {
                        tentative++;
                        System.out.println("ID introuvable. Tentative " + tentative + "/" + TENTATIVES_MAX);
                        continue;
                    }

                    // Si le programmeur existe 
                    System.out.print("Nouveau salaire : ");
                    double nouveauSalaire = scanner.nextDouble();
                    scanner.nextLine();

                    try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                        stmtUpdate.setDouble(1, nouveauSalaire);
                        stmtUpdate.setInt(2, id);
                        stmtUpdate.executeUpdate();
                    }

                    System.out.println("Salaire modifié avec succès!");
                    return;
                 } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                    return;
                 }
        }

        System.out.println("Trop de tentatives. Retour au menu principal.");
    }


    public void afficherListeProjets () {

        String sql = "SELECT * FROM Projet";

        try (Connection conn = Database.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

                boolean vide = true;

                while (rs.next()) {
                    vide = false;

                    System.out.println();
                    System.out.println("-------------------------");
                    System.out.println("ID : " + rs.getInt("id"));
                    System.out.println("Intitulé du projet : " + rs.getString("intitule"));
                    System.out.println("Date de début : " + rs.getString("dateDebut"));
                    System.out.println("Date de fin prévue : " + rs.getString("dateFinPrevue"));
                    System.out.println("Etat : " + rs.getString("etat"));
                    System.out.println("-------------------------");
                }

                if (vide) {
                    System.out.println("    Aucun projet enregistré.");
                    System.out.println();
                }
             } catch (SQLException e) {
                System.out.println("Erreur lors de l'affichage des projets : " + e.getMessage());
             }
    }


    public void afficherProgMemeProjet(Scanner scanner) {

        final int TENTATIVES_MAX = 3;
        int tentatives = 0;

        while (tentatives < TENTATIVES_MAX) {

            System.out.print("Nom du projet : ");
            String nomProjet = scanner.nextLine();

            // Vérifier si le projet existe et récupérer son ID
            String sqlProjet = "SELECT id FROM Projet WHERE intitule = ?";

            try (Connection conn = Database.getConnexion();
                PreparedStatement stmtProjet = conn.prepareStatement(sqlProjet)) {

                stmtProjet.setString(1, nomProjet);
                ResultSet rsProjet = stmtProjet.executeQuery();

                if (!rsProjet.next()) {
                    tentatives++;
                    System.out.println("Projet inexistant. Tentative " + tentatives + "/" + TENTATIVES_MAX);
                    continue;
                }

                int idProjet = rsProjet.getInt("id");

                // Récupérer les programmeurs liés à ce projet
                String sqlProg = """
                    SELECT p.id, p.nom, p.prenom, p.pseudo, p.salaire
                    FROM Programmeurs p
                    WHERE p.id_projet = ?
                """;

                try (PreparedStatement psProg = conn.prepareStatement(sqlProg)) {

                    psProg.setInt(1, idProjet);
                    ResultSet rsProg = psProg.executeQuery();

                    System.out.println("===== Programmeurs du projet : " + nomProjet + " =====");

                    boolean aucun = true;

                    while (rsProg.next()) {
                        aucun = false;
                        System.out.println("ID : " + rsProg.getInt("id"));
                        System.out.println("Nom : " + rsProg.getString("nom"));
                        System.out.println("Prénom : " + rsProg.getString("prenom"));
                        System.out.println("Pseudo : " + rsProg.getString("pseudo"));
                        System.out.println("Salaire : " + rsProg.getDouble("salaire"));
                        System.out.println("----------------------------");
                    }

                    if (aucun) {
                        System.out.println("Aucun programmeur n'est affecté à ce projet.");
                    }
                    return;
                }

            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
                return;
            }
        }

        System.out.println("Trop de tentatives. Retour au menu principal.");
    }

    /* ====================================================================================================================== */

    public void modifierPrime (Scanner scanner) {
        int tentative = 0;
        int TENTATIVES_MAX = 3;

        while (tentative < TENTATIVES_MAX) {

            System.out.println("ID du programmeur : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            // Vérifier si le programmeur existe
            String sqlCheck = "SELECT id FROM Programmeurs WHERE id = ?";
            String sqlUpdate = "UPDATE Programmeurs SET prime = ? WHERE id = ?";

            try (Connection conn = Database.getConnexion();
                 PreparedStatement stmt = conn.prepareStatement(sqlCheck)) {

                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();

                    if (!rs.next()) {
                        tentative++;
                        System.out.println("ID introuvable. Tentative " + tentative + "/" + TENTATIVES_MAX);
                        continue;
                    }

                    // Si le programmeur existe 
                    System.out.println("Nouvelle prime : ");
                    double nvllPrime = scanner.nextDouble();
                    scanner.nextLine();

                    try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                        stmtUpdate.setDouble(1, nvllPrime);
                        stmtUpdate.setInt(2, id);
                        stmtUpdate.executeUpdate();
                    }

                    System.out.println("Prime modifiée avec succès!");
                    return;
                 } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                    return;
                 }
        }

        System.out.println("Trop de tentatives. Retour au menu principal.");
    }

    public void ajouterProjet (Scanner scanner) {
        
        System.out.print ("Intitulé du projet : ");
        String intitule = scanner.nextLine();

        System.out.print ("Date de début (DD-MM-YYYY) : ");
        String dateDebut = scanner.nextLine();

        System.out.print ("Date de fin prévue (DD-MM-YYYY) : ");
        String dateFin = scanner.nextLine();
        
        System.out.print ("Etat du projet : ");
        String etat = scanner.nextLine();

        String sql = """
                INSERT INTO Projet (intitule, dateDebut, dateFinPrevue, etat) VALUES (?, ?, ?, ?)
                """;
        
        try (Connection conn = Database.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, intitule);
                stmt.setString(2, dateDebut);
                stmt.setString(3, dateFin);
                stmt.setString(4, etat);

                stmt.executeUpdate();
                System.out.println ("Projet ajouté avec succès");
             } catch (SQLException e) {
                System.out.println ("Erreur lors de l'ajout du projet : " + e.getMessage());
             }
    }

    public void supprimerProjet (Scanner scanner) {

        System.out.print ("ID du propjet à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sqlDel = "DELETE FROM Projet WHERE id = ?";

        try(Connection conn = Database.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sqlDel)) {

                stmt.setInt(1, id);
                int lignesSupp = stmt.executeUpdate();

                if (lignesSupp > 0) {
                    System.out.println ("Projet supprimé avec succès !");
                } else {
                    System.out.println ("Aucun projet supprimé (ID inexistant).");
                }
             } catch (SQLException e) {
                System.out.println ("Erreur lors de la suppression : " + e.getMessage());
             }
    }

    public List<Programmeur> getProgrammeurs() {
        // creation d'une liste de programmeurs
        List<Programmeur> listeProgrammeurs = new ArrayList<>();

        // requête SQL pour récupérer toutes les lignes dans la table Programmeurs
        String sql = "SELECT * FROM Programmeurs";

        // connection à la base de données
        try (Connection conn = Database.getConnexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // on crée un objet programmeur pour chaque ligne dans la table Programmeurs
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String pseudo = rs.getString("pseudo");
                String responsable = rs.getString("responsable");
                String hobby = rs.getString("hobby");
                int anNaissance = rs.getInt("anNaissance");
                double salaire = rs.getDouble("salaire");
                double prime = rs.getDouble("prime");
                int idProjet = rs.getInt("id_projet");

                Programmeur newProgrammeur = new Programmeur(id, nom, prenom, adresse, pseudo, responsable, hobby,
                        anNaissance, salaire, prime, idProjet);
                listeProgrammeurs.add(newProgrammeur);
            }

            // message de confirmation
            System.out.println(listeProgrammeurs.size() + " programmeurs ont été récupéré de la database");

        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }

        return listeProgrammeurs;
    }

}
