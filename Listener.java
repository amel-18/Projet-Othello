import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * La classe qui s'intéresse au traitement d'un événement de souris implémente
 * l'interface MouseListener constitué d'une partie de type partie.
 */

public class Listener implements MouseListener {

    private Partie partie ;

    /**
     * Constructeur permet de produire une nouvelle instance de la classe Listener
     * @param p la partie 
     */
    public Listener(Partie p) {
        this.partie = p;
    }
    
    /**
     * Constructeur permet de produire une nouvelle instance de la classe
     */
    public Listener() {
    }
    
/**
 * Methode permettant de modifier la partie 
 * @param p la partie
 */

public void setPartie(Partie p) {
    this.partie = p;
}
    
/**
 * Methode permettant de detecter la clique du souris du joueur 
 * @param e l'evenement de la souris
 * @exception NullPointerException declenché si une partie est nulle
 */
    @Override
	public void mouseClicked(MouseEvent e) throws NullPointerException  {
        if(partie == null){throw new NullPointerException("la partie est null");}
        if(this.partie.getState()==gameState.INPROGRESS){
            try{
                System.out.println(e.getX() + " " + e.getY());
            
            if(this.partie.getEnnemi().name()=="HUMAIN"){
                    partie.repaint();
                    if  ( (e.getX())/50-1>0 || (e.getX())/50-1<this.partie.getBoard().getLength() || (e.getY())/50-1>0  || (e.getY())/50-1 <this.partie.getBoard().getLength()){
                        
                        partie.update((e.getX())/50-1,(e.getY())/50-1);
                        partie.repaint();
                    }
                    
                    
                    System.out.println(partie.getBoard().nbcoupPossible());
            }else if(this.partie.getEnnemi().name()== "IABEGINNER"){
                System.out.println("boucle de l'iabeginner");
                if  ( (e.getX())/50-1>0 || (e.getX())/50-1<this.partie.getBoard().getLength() || (e.getY())/50-1>0  || (e.getY())/50-1 <this.partie.getBoard().getLength()){
                    
                    partie.update((e.getX())/50-1,(e.getY())/50-1);
                    partie.repaint();
                }
                System.out.println("nombre de coups possibles : "+partie.getBoard().nbcoupPossible());
                if(!(this.partie.isOver())){
                    this.partie.getAmouv();
                }                    System.out.println(partie.getBoard().nbcoupPossible());
            }else if(this.partie.getEnnemi().name()== "IABEGINNER"){
                System.out.println("boucle de l'iabeginner");
                if  ( (e.getX())/50-1>0 || (e.getX())/50-1<this.partie.getBoard().getLength() || (e.getY())/50-1>0  || (e.getY())/50-1 <this.partie.getBoard().getLength()){
                    if(this.partie.getBoard().getPlayer()== Pawn.BLACK){
                        partie.update((e.getX())/50-1,(e.getY())/50-1);
                        partie.repaint();
                    }if(this.partie.getBoard().getPlayer() == Pawn.WHITE){
                        if(!(this.partie.isOver())){
                        this.partie.getAmouv();
                        }
                    }
                    
                }
                System.out.println("nombre de coups possibles : "+partie.getBoard().nbcoupPossible());
                    
                    

            }if(this.partie.isOver() ){ 
                if(this.partie.winner().name() == "EMPTY"){
                    JOptionPane.showMessageDialog(null, "IL Y A EGALITE ");
                }else {
                    JOptionPane.showMessageDialog(null, "Le joueur "+this.partie.winner().name()+" a gagné");
                }

                this.partie.init(this.partie.getBoard().getLength(),"init", gameState.INIT);

                this.partie.repaint();
                        
            } 

            }catch ( ArrayIndexOutOfBoundsException o){
                System.out.println("Vous clicker en dehors du plateau");
            }    
        }
        
        
                    
                    
    }
        
        
		            
                    
    
    /**
     * Methode appelé lorsqu'un bouton de la souris a été pressé sur un composant.
     */
	@Override
	public void mousePressed(MouseEvent e) {}

    /**
     * Methode appelé lorsqu'un bouton de la souris a été relâché sur un composant.
     */
	@Override
	public void mouseReleased(MouseEvent e) {}

    /**
     * Methode appelé lorsque la souris entre dans un composant.
     */
	@Override
	public void mouseEntered(MouseEvent e) {}

    /**
     * Methode appelé lorsque la souris quitte un composant.
     */
	@Override
	public void mouseExited(MouseEvent e) {}

                

}
