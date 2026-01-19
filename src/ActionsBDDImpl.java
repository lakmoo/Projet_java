package src;

/**
 * Continet les implémentations des méthodes déclarées dans ActionsBDD.
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class ActionsBDDImpl implements ActionsBDD {

    /**
     * Affichage de tous les programmeurs présents en base comme ceci.
     * 
     * @param listeProgrammeurs Liste des programmeurs
     */
    public void afficherTousLesProgrammeurs(List<Programmeur> listeProgrammeurs) {
        if (listeProgrammeurs.isEmpty()) {
            System.out.println("La liste des programmeurs est vide.");
            return;
        }

        for (Programmeur programmeur : listeProgrammeurs) {
            System.out.println(programmeur.toString());
        }
    }

    /**
     * Renvoie un programmeur pointé par l'ID saisie par l'utilisateur.
     * 
     * @param scanner           Récupère la saisie utilisateur
     * @param listeProgrammeurs Liste des programmeurs
     */
    public void afficherUnProgrammeurParId(Scanner scanner, List<Programmeur> listeProgrammeurs) {
        int idProgrammeur = lireEntier(scanner, "Id du programmeur : ");

        Programmeur programmeurTrouve = Programmeur.chercherProgrammeurParId(listeProgrammeurs, idProgrammeur);

        if (programmeurTrouve == null) {
            System.out.println("Programmeur introuvable.");
            return;
        } else {
            System.out.println(programmeurTrouve.toString());
        }
    }

    /**
     * Supprime le programmeur correspondant à l'ID saisie par l'utilisateur.
     * 
     * @param scanner           Récupère la saisie utilisateur
     * @param listeProgrammeurs Liste des programmeurs
     */
    public void supprimerProgrammeurParId(Scanner scanner, List<Programmeur> listeProgrammeurs) {
        int tentativesMax = 3;
        int tentatives = 0;

        while (tentatives < tentativesMax) {

            int idProgrammeur = lireEntier(scanner, "Id du programmeur à supprimer : ");
            Programmeur programmeurTrouve = Programmeur.chercherProgrammeurParId(listeProgrammeurs, idProgrammeur);

            if (programmeurTrouve == null) {
                tentatives++;
                System.out.println("Programmeur introuvable.");
                return;
            } else {
                listeProgrammeurs.remove(programmeurTrouve);
                System.out.println("SUPPRESSION REUSSIE.");
                return;
            }
        }

        System.out.println("Trop de tentatives. Retour au menu.");
    }

    /**
     * Ajouter un programmeur.
     * L'utilisateur doit saisie chaque information contenu dans la fiche.
     * 
     * @param scanner           Récupère les saisies utilisateur
     * @param listeProgrammeurs Liste des programmeurs
     * @param listeProjets      Liste des projets
     */
    public void ajouterProgrammeur(Scanner scanner, List<Programmeur> listeProgrammeurs, List<Projet> listeProjets) {
        int nouvelId = genererNouvelId(listeProgrammeurs);
        System.out.println("Id attribué : " + nouvelId);

        String nom = lireTexte(scanner, "Nom : ");
        String prenom = lireTexte(scanner, "Prénom : ");
        String adresse = lireTexte(scanner, "Adresse : ");
        String pseudo = lireTexte(scanner, "Pseudo : ");
        String responsable = lireTexte(scanner, "Responsable : ");
        String hobby = lireTexte(scanner, "Hobby : ");
        int anneeNaissance = lireEntier(scanner, "Année de naissance : ");
        double salaire = lireDouble(scanner, "Salaire : ");
        double prime = lireDouble(scanner, "Prime : ");

        afficherListeDesProjets(listeProjets);
        String nomProjet = lireTexte(scanner, "Nom du projet (exact) : ");

        if (!projetExiste(listeProjets, nomProjet)) {
            System.out.println("Projet inconnu. On le nomme 'Aucun'.");
            nomProjet = "Aucun";
        }

        Programmeur programmeurAAjouter = new Programmeur(
                nouvelId, nom, prenom, adresse, pseudo, responsable,
                hobby, anneeNaissance, salaire, prime, nomProjet);

        listeProgrammeurs.add(programmeurAAjouter);
        System.out.println("AJOUT REUSSI.");
    }

    /**
     * Modifier le salaire d'un programmeur pointé par son ID.
     * 
     * @param scanner           Récupère les saisie utilisateur
     * @param listeProgrammeurs Liste des programmeurs
     */
    public void modifierSalaire(Scanner scanner, List<Programmeur> listeProgrammeurs) {
        int tentativesMax = 3;
        int tentatives = 0;

        while (tentatives < tentativesMax) {

            int idProgrammeur = lireEntier(scanner, "Id du programmeur : ");
            Programmeur programmeurTrouve = Programmeur.chercherProgrammeurParId(listeProgrammeurs, idProgrammeur);

            if (programmeurTrouve == null) {
                tentatives++;
                System.out.println("Programmeur introuvable.");
                return;
            } else {
                double nouveauSalaire = lireDouble(scanner, "Nouveau salaire : ");
                programmeurTrouve.setSalaire(nouveauSalaire);

                System.out.println("Modification OK.");
                System.out.println(programmeurTrouve.toString());
                return;
            }
        }

        System.out.println("Trop de tentatives. Retour au menu.");
    }

    /**
     * Afficher la liste des projets.
     * 
     * @param listeProjets Liste des projets
     */
    public void afficherListeDesProjets(List<Projet> listeProjets) {
        if (listeProjets.isEmpty()) {
            System.out.println("Aucun projet.");
            return;
        }

        for (Projet projet : listeProjets) {
            System.out.println(projet.toTexteAffichage());
        }
    }

    /**
     * Récupère le nom du projet et renvoie tous les programmeurs travaillant sur
     * le-dit projet.
     * 
     * @param scanner           Récupère les saisies utilisateurs
     * @param listeProgrammeurs Liste des programmeurs
     * @param listeProjets      Liste des projets
     */
    public void afficherProgrammeursDuMemeProjet(Scanner scanner,
            List<Programmeur> listeProgrammeurs,
            List<Projet> listeProjets) {
        afficherListeDesProjets(listeProjets);

        int tentativesMax = 3;
        int tentatives = 0;

        while (tentatives < tentativesMax) {

            String nomProjet = lireTexte(scanner, "Nom du projet (exact) : ");

            if (!projetExiste(listeProjets, nomProjet)) {
                tentatives++;
                System.out.println("Projet introuvable.");
                return;
            } else {
                boolean auMoinsUn = false;

                for (Programmeur programmeur : listeProgrammeurs) {
                    if (programmeur.getNomProjet() != null
                            && programmeur.getNomProjet().equalsIgnoreCase(nomProjet)) {
                        System.out.println(programmeur.toString());
                        auMoinsUn = true;
                    }
                }

                if (!auMoinsUn) {
                    System.out.println("Programmeur introuvable.");
                    return;
                }
                return;
            }
        }

        System.out.println("Trop de tentatives. Retour au menu.");
    }

    /*---------------------------------------------------------------------------------------*/

    // l'excpetion pour empêcher la saisie de String dans le choix du menu
    public int lireEntier(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine();

            try {
                return Integer.parseInt(saisie);
            } catch (NumberFormatException exception) {
                System.out.println("Veuillez saisir un nombre entier.");
            }
        }
    }

    // L'expection pour permettre la saisie d'un double pour le salaire
    public double lireDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine();

            try {
                return Double.parseDouble(saisie);
            } catch (NumberFormatException exception) {
                System.out.println("Veuillez saisir un nombre (ex: 12.5).");
            }
        }
    }

    public String lireTexte(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    // création des programmeurs (pas de bdd pour l'instant)
    public List<Programmeur> creerProgrammeursDeTest() {
        List<Programmeur> listeProgrammeurs = new ArrayList<>();

        listeProgrammeurs.add(new Programmeur(
                1, "Torvalds", "Linus", "2 avenue Linux Git", "linuxroot",
                "Didier Achvar", "salsa", 1969, 2170.0, 50.0, "OutilInterne"));

        listeProgrammeurs.add(new Programmeur(
                2, "Stroustrup", "Bjarne", "294 rue C++", "c++1",
                "Karim Lahlou", "Voyages", 1950, 2466.0, 80.0, "SiteWeb"));

        listeProgrammeurs.add(new Programmeur(
                3, "Gosling", "James", "3 bvd JVM", "javapapa",
                "Jacques Augustin", "Peinture", 1955, 1987.0, 10.0, "SiteWeb"));

        return listeProgrammeurs;
    }

    // Création des projets
    public List<Projet> creerProjetsDeTest() {
        List<Projet> listeProjets = new ArrayList<>();

        listeProjets.add(new Projet(1, "SiteWeb", "2025-10-01", "2026-01-15", "EN_COURS"));
        listeProjets.add(new Projet(2, "AppliMobile", "2025-09-10", "2026-02-20", "EN_COURS"));
        listeProjets.add(new Projet(3, "OutilInterne", "2025-11-05", "2026-03-30", "PLANIFIE"));

        return listeProjets;
    }

    public int genererNouvelId(List<Programmeur> listeProgrammeurs) {
        int idMax = 0;

        for (Programmeur programmeur : listeProgrammeurs) {
            if (programmeur.getId() > idMax) {
                idMax = programmeur.getId();
            }
        }

        return idMax + 1;
    }

    public boolean projetExiste(List<Projet> listeProjets, String nomProjet) {
        for (Projet projet : listeProjets) {
            if (projet.getIntitule().equalsIgnoreCase(nomProjet)) {
                return true;
            }
        }
        return false;
    }

    public void afficherProgParID(int id) {
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
                System.out.println("Nom de projet : " + rs.getString("nomProjet"));
                System.out.println();
            } else {
                System.out.println("Programmeur introuvable.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerProgrammeur(Scanner scanner) {
        System.out.println("Entrez l'ID du programmeur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "DELETE FROM Programmeurs WHERE id = " + id;

        try (Connection conn = Database.getConnexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            int lignesSupprimes = stmt.executeUpdate(sql);

            if (lignesSupprimes > 0) {
                System.out.println("Programmeur supprimé avec succès !");
            } else {
                System.out.println("Aucun programmeur trouvé avec l'ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    public void insererDBProg(Scanner scanner) {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();

        System.out.print("Pseudo : ");
        String pseudo = scanner.nextLine();

        System.out.print("Responsable : ");
        String responsable = scanner.nextLine();

        System.out.print("Hobby : ");
        String hobby = scanner.nextLine();

        System.out.print("Année de naissance : ");
        int anNaissance = scanner.nextInt();

        System.out.print("Salaire : ");
        double salaire = scanner.nextDouble();

        System.out.print("Prime : ");
        double prime = scanner.nextDouble();
        scanner.nextLine(); // vider le buffer

        System.out.print("Nom du projet : ");
        String nomProjet = scanner.nextLine();

        String sqlRequete = "INSERT INTO Programmeurs (nom, prenom, adresse, pseudo, responsable, hobby, anNaissance, salaire, prime, nomProjet) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnexion(); PreparedStatement ps = conn.prepareStatement(sqlRequete)) {
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, adresse);
            ps.setString(4, pseudo);
            ps.setString(5, responsable);
            ps.setString(6, hobby);
            ps.setInt(7, anNaissance);
            ps.setDouble(8, salaire);
            ps.setDouble(9, prime);
            ps.setString(10, nomProjet);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherDBProg() {
        String sql = "SELECT * FROM Programmeurs";

        try (Connection conn = Database.getConnexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "|" +
                                rs.getString("nom") + " " +
                                rs.getString("prenom") + "|" +
                                rs.getString("adresse") + "|" +
                                rs.getString("pseudo") + "|" +
                                rs.getString("responsable") + "|" +
                                rs.getString("hobby") + "|" +
                                rs.getInt("anNaissance") + "|" +
                                rs.getDouble("salaire") + "|" +
                                rs.getDouble("prime") + "|" +
                                rs.getString("nomProjet"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la lecture des programmeurs : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modifierSaisie(Scanner scanner) {
        int tentative = 0;
        int TENTATIVES_MAX = 3;

        while (tentative < TENTATIVES_MAX) {

            System.out.println("ID du programmeur : ");
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
                System.out.println("Nouveau salaire : ");
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
                String nomProjet = rs.getString("nomProjet");

                Programmeur newProgrammeur = new Programmeur(id, nom, prenom, adresse, pseudo, responsable, hobby,
                        anNaissance, salaire, prime, nomProjet);
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
