/**
 * Enum s'occupe des pions de jeu
 * les pions : White , Black , Empty
 */

public enum Pawn {
    WHITE ,EMPTY,BLACK;
 
    
    /**
     * Methode qui pertmet d'associer à chaque composant de l'enul un indice
     * @return l'indice de chaque compsant de l'enum
     */
     public  int ordinal2(){
         return this.ordinal()-1;
     }
     
 }
 