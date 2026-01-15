package src;

/**
 * Contient le point d'entrée. 
 * 
 * @author Huan Jie YEN
 * @author Lakshya SELVAKUMAR
 * @author Kimberley NDOUGA
 */

public class Start {
    public static void main(String[] args) {
        Database.init();
        new Menu().choixMenu();
    }
}
