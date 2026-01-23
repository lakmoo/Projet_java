package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Permet d'établir la connexion entre les bases de données et le reste du travail.  
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 * 
 * @version Janvier 2026
 */
public class Database {
    
    private static final String URL = "jdbc:sqlite:programmeurs.db";

    /**
     * Se connecte à la base de données.
     * 
     * @return
     * @throws SQLException
     */
    public static Connection getConnexion () throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Se connecte à la base de données et les créé si elles n'existent pas déjà. 
     */
    public static void init () {
        String sqlProg = """
                CREATE TABLE IF NOT EXISTS Programmeurs (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nom TEXT NOT NULL,
                prenom TEXT NOT NULL,
                adresse TEXT NOT NULL,
                pseudo TEXT NOT NULL UNIQUE,
                responsable TEXT NOT NULL,
                hobby TEXT NOT NULL,
                anNaissance INTEGER NOT NULL,
                salaire REAL NOT NULL,
                prime REAL NOT NULL,
                id_projet INTEGER,
                FOREIGN KEY (id_projet) REFERENCES Projet(id)
                    ON DELETE SET NULL
                    ON UPDATE CASCADE
                );
                """;

        String sqlProjet = """
                CREATE TABLE IF NOT EXISTS Projet (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                intitule TEXT NOT NULL,
                dateDebut TEXT NOT NULL,
                dateFinPrevue TEXT NOT NULL,
                etat TEXT NOT NULL
                );
                """;
        
        try (Connection conn = getConnexion(); 
             Statement stmt = conn.createStatement()) {

            boolean progExiste = tableExiste(conn, "Programmeurs");
            boolean projetExiste = tableExiste(conn, "Projet");

            stmt.execute(sqlProg);
            stmt.execute(sqlProjet);

            if (!progExiste || !projetExiste) {
                insertDonneesParDefaut();
                System.out.println("Tables créées et données par défaut insérées.");
            } else {
                System.out.println("Tables déjà existantes, aucune création nécessaire.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute des projets et programmeurs par défaut.
     */
    public static void insertDonneesParDefaut() {

        String insertProjet = """
            INSERT INTO Projet (intitule, dateDebut, dateFinPrevue, etat)
            VALUES (?, ?, ?, ?)
        """;

        String insertProg = """
            INSERT INTO Programmeurs
            (nom, prenom, adresse, pseudo, responsable, hobby, anNaissance, salaire, prime, id_projet)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnexion()) {

            int idWeb, idMobile;

            // Projets
            try (PreparedStatement ps = conn.prepareStatement(insertProjet, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, "Site Web");
                ps.setString(2, "01-01-2024");
                ps.setString(3, "01-06-2024");
                ps.setString(4, "En cours");
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                idWeb = rs.getInt(1);

                ps.setString(1, "Application Mobile");
                ps.setString(2, "01-02-2024");
                ps.setString(3, "01-09-2024");
                ps.setString(4, "Prévu");
                ps.executeUpdate();

                rs = ps.getGeneratedKeys();
                rs.next();
                idMobile = rs.getInt(1);
            }

            // Programmeurs
            try (PreparedStatement ps = conn.prepareStatement(insertProg)) {

                ps.setString(1, "Dupont");
                ps.setString(2, "Alice");
                ps.setString(3, "Paris");
                ps.setString(4, "aliceD");
                ps.setString(5, "Martin");
                ps.setString(6, "Escalade");
                ps.setInt(7, 1998);
                ps.setDouble(8, 3200);
                ps.setDouble(9, 300);
                ps.setInt(10, idWeb);
                ps.executeUpdate();

                ps.setString(1, "Durand");
                ps.setString(2, "Lucas");
                ps.setString(3, "Lyon");
                ps.setString(4, "lucasDev");
                ps.setString(5, "Martin");
                ps.setString(6, "Jeux vidéo");
                ps.setInt(7, 1995);
                ps.setDouble(8, 3500);
                ps.setDouble(9, 400);
                ps.setInt(10, idMobile);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur insertion données : " + e.getMessage());
        }
    }

    /**
     * Vérifie si une table existe dans la base de données. 
     * 
     * @param conn
     * @param nomTable
     * @return boolean 
     * @throws SQLException
     */
    private static boolean tableExiste(Connection conn, String nomTable) throws SQLException {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomTable);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true si la table existe
        }
    }

}