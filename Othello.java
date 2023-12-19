

public class Othello {
    
    /**
     * Fonction main Permettant d'executer le programme en ouvrant une fenetrede jeu
     * @param args tableau de string
     */
    public static void main(String[] args){

            try {
            new Window();}
            catch(NullPointerException e){
                System.out.println("la partie est null");
            }catch(Exception e){
                System.out.println("EXCEPTION");
            }
        
        

    }
    
}
