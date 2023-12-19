/**
 * Classe qui genere le deplacement qu'un joueur fait
 */
public class Move {
    private int x;
    private int y;
    private Pawn p ;


    /**
     * Constructeur de la classe Move 
     * @param x les lignes du plateau
     * @param y les colonnes du plateau
     */
    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Methode qui permet d'avoir la ligne 
     * @return la ligne d'une case
     */
    public int getX(){
        return  this.x;
    }

    /**
     * Methode qui permet d'avoir la colonne 
     * @return la colonne d'une case
     */
    public int getY(){
        return this.y;
    }

    /**
     * Methode qui permet d'avoir le pion d'un joueur
     * @return le pion 
     */
    public Pawn getPawn(){
        return this.p;
    }

    


}
