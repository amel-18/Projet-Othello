
/**
 * Classe qui s'occupe de l'arbre du jeu
 * Composé de : Le plateau , une liste chainé des possibilité , un score et les deux types des noeuds
 */
import java.util.LinkedList;

public class Noeud {
     private Board plateau;
     private final LinkedList<Noeud> coupPossible; // liste des configuration apres un coup
     private final int score ; //nombre de pion noir sur le plateau
     private Type type; //type du noeud (min ou max)

    /**
     * Enum types désigne les types des noeuds de l'arbre
     * Max et Min
     */
     private enum Type{MAX,MIN};



    /**
     * Constructeur de la classe noeud permet de cree une nouvelle instance de Noeud
     * @param b le plateu 
     * @param p le type de noeud 
     */
     public Noeud(Board b,Type p){
        this.plateau = b;
        this.coupPossible =this.createList();
        this.score = this.eval();
        this.type = p;
     }


     
    /**
     * Methode qui retourne le nombre des pions noirs
     * @return le nombre des pions noirs
     */ 
     public int eval(){   
        return this.plateau.nbPawn(Pawn.BLACK);
     }


    /**
     * fonction qui initialise la liste des coup possibles à partir de la
     * configuration d'un plateau
     * @return une liste des configurations possibles à partir d'une etat de jeu 
     */
     public LinkedList<Noeud> createList(){
        LinkedList<Noeud> result = new LinkedList<Noeud> ();
        for(int i = 0 ; i<this.plateau.getLength();i++){
            for(int j =0; j<this.plateau.getLength() ; j++){
                if(this.plateau.coupPossible(i,j)){

                    Board copie = Board.copie(this.plateau); 
                    copie.update(i,j);
                    result.add(new Noeud(copie,Type.MIN));

                }
            }
        }

        return result;
     }


    
    /**
     * Methode qui permet de jouer
     * @param m Objet de la classe Move
     */
    public void play(Move m){
        this.plateau.update(m);
    }

    /**
     * Methode qui permet de ne pas jouer 
     * @param m Objet de la classe Move
     */
    public void unplay(Move m){
        this.plateau.unupdate(m);
    }
}
