package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Permet d'établir la connexion entre la base de données de le projet.  
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
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

    public static void init () {
        String sqlProg = """
                CREATE TABLE IF NOT EXISTS Programmeurs (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nom TEXT,
                prenom TEXT,
                adresse TEXT,
                pseudo TEXT,
                responsable TEXT,
                hobby TEXT,
                anNaissance INTEGER,
                salaire REAL,
                prime REAL,
                nomProjet TEXT
                );
                """;

        String sqlProjet = """
                CREATE TABLE IF NOT EXISTS Projet (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                intitule TEXT,
                dateDebut TEXT,
                dateFinPrevue TEXT,
                etat TEXT
                );
                """;
        
        try (Connection conn = getConnexion(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlProg);
            stmt.execute(sqlProjet);
            System.out.println("Table créées ou déjà existantes.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
