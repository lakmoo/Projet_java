package src;

import java.util.ArrayList;

/**
 * Contient le point d'entrée. 
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 * 
 * @version Janvier 2026
 */

public class Start {
    /**
     * Point d'entrée du programme.
     * @param args             Les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        Database.init();
        new Menu().choixMenu(new ArrayList<Integer>());
    }
}