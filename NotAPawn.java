/*
 * Classe manipulant l'exception NotAPawn.
 * Hérite de Exception
 */

public class NotAPawn extends Exception {
    
    /**
     * Constructeur de la classe NotAPawn 
     * @param s message d'erreur à afficher
     */
    public NotAPawn(String s){
        super(s);
    }
}
