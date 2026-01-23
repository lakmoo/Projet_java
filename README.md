PROJET JAVA 

=== Description ===

Ce projet en Java permet de gérer une base de données de programmeurs et de projets à
l aide de SQLite.
L utilisateur interagit via un menu pour effectuer différentes opérations. 

Technologies utilisées :
- Java (JDK 17)
- SQLite
- JDBC (sqlite-jdbc)

=== GIT ===

Lien du dépôt GitHub : https://github.com/lakmoo/Projet_java.git

=== Démo vidéo ===

Lien de la démo disponible sur Youtube : 

=== Compilation et exécution du projet ===

== Version console == 

Il vous suffit de copier-coller les commandes suivantes dans votre terminal : 
    javac -cp "lib/sqlite-jdbc-3.51.1.0.jar" -d out/production/Projet src/*.java
    java --enable-native-access=ALL-UNNAMED -cp "out/production/Projet:lib/sqlite-jdbc-3.51.1.0.jar" src.Start

== Version serveur HTTP ==

Afin de lancer l interface, il faut executer les lignes suivantes. 
javac -cp "lib/sqlite-jdbc-3.51.1.0.jar" -d out/production/Projet src/*.java
java -cp "out/production/Projet" src.SimpleHttpServer

Malheureusement, il n est pas possible de lancer les deux versions en même temps sur la
console à cause de conflits. Vous pouvez executer la fonction `main` de SimpleHttpServer.java 
puis ensuite executer la version console sur votre terminal. 

=== A savoir === 

- Le code source est organisé en plusieurs classes pour la gestion de la base de données, 
  les opérations, le menu interactif et le serveur HTTP.
- Le driver JDBC pour SQLite est inclus dans le dossier `lib`.
- Les bases de données sont locale. 
- Le fichier `programmeurs.db` est créé automatiquement lors de la première exécution si
  il n existe pas déjà.
- Les tables sont également créées et initialisées avec des données par défaut si elles 
  n existent pas. Sinon, un message indique qu elles existent déjà.
- Le code est documenté avec des commentaires pour faciliter la compréhension.
- Les exceptions SQL sont gérées pour éviter l arrêt du programme en cas d erreurs.
- Le nombre de tentatives est limité dans certaines actions. 

=== Arborescence du projet ===

.
├── README
├── lib
│   └── sqlite-jdbc-3.51.1.0.jar            // Driver SQLite
├── out
│   └── production
│       └── Projet
│           ├── Main.class
│           ├── Programmeur.class
│           ├── Programmeur.mwb
│           ├── Projet.iml
│           ├── Projet_2025-26.pdf
│           ├── Projet_enonce.docx
│           └── src
│               ├── ActionsBDD.class
│               ├── ActionsBDDImpl.class
│               ├── Database.class
│               ├── Menu.class
│               ├── Programmeur.class
│               ├── Projet.class
│               ├── SimpleHttpServer$MyHandler.class
│               ├── SimpleHttpServer$ProgrammeurAPIHandler.class
│               ├── SimpleHttpServer.class
│               └── Start.class
└── src
    ├── ActionsBDD.java                     // Interface pour les opérations
    ├── ActionsBDDImpl.java                 // Implémentation des opérations
    ├── Database.java                       // Gestion de la connexion à la BDD
    ├── Menu.java                           // Menu interactif
    ├── Programmeur.java                    // Classe Programmeur
    ├── Projet.java                         // Classe Projet
    ├── SimpleHttpServer.java               // Serveur HTTP simple
    ├── Start.java                          // Point d entrée de l application
    └── public
        ├── default-pfp.jpg
        ├── index.css
        ├── index.html
        ├── main.css
        ├── projets.css
        ├── projets.html
        └── script.js
└── programmeurs.db                          // Base de données SQLite

=== Structure de la base de données ===

La base de données SQLite contient deux tables principales :
1. Programmeurs :
   - id (INTEGER PRIMARY KEY AUTOINCREMENT)
   - nom (TEXT NOT NULL)
   - prenom (TEXT NOT NULL)
   - adresse (TEXT NOT NULL UNIQUE)
   - pseudo (TEXT NOT NULL UNIQUE)
   - responsable (TEXT NOT NULL)
   - hobby (TEXT NOT NULL)
   - anNaissance (INTEGER NOT NULL)
   - salaire (REAL NOT NULL)
   - prime (REAL NOT NULL)
   - id_projet (INTEGER, FOREIGN KEY REFERENCES Projet(id) ON DELETE SET NULL)

2. Projet :
   - id (INTEGER PRIMARY KEY AUTOINCREMENT)
   - intitule (TEXT NOT NULL)
   - dateDebut (TEXT NOT NULL)
   - dateFinPrevue (TEXT NOT NULL)
   - etat (TEXT NOT NULL)

-> Un programmeur peut être associé à un projet via la clé étrangère `id_projet`.

=== Fonctionnalités ===

== Princiaples ==

    * Menu Principal *
    1.  Afficher tous les programmeurs
    2.  Chercher un programmeur
    4.  Supprimer un programmeur
    5.  Ajouter un programmeur
    6.  Modifier le salaire
    8.  Afficher la liste des projets
    9.  Programmeurs du même projet
    13. Quitter le programme

== Additionnelles ==

    3.  Enregistrer les programmeurs dans un fichier
    7.  Modifier la prime
    10.  Ajouter un projet
    11. Supprimer un projet
    12. Enregistrer l historique des actions dans un fichier

=== Pistes d amélioration ===

    * Ajouter une interface graphique (JavaFX ou Swing) pour une meilleure expérience utilisateur.
    * Implémenter des fonctionnalités plus avancées.
    * Permettre l exportation des données des programmeurs au format CSV ou JSON.
    * Ajouter des fonctionnalités de tri selon le salaire, etc.

=== Auteurs ===

    * Lakshya SELVAKUMAR
    * Huan Jie YEN
    * Kimberley NDOUGA 
