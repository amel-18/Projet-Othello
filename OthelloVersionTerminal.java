import java.util.Scanner ;
public class OthelloVersionTerminal {
    

    /**
     * Methode qui permet d'executer le jeu dans le terminale
     * @param args tableau d'argument
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Board nouvelle = new Board(8,"partie 1");

        while(!(nouvelle.isOver())==true){
            nouvelle.showterminal();
            nouvelle.showCoupPossible();
            int coupX = input.nextInt();
            int coupy = input.nextInt();
            nouvelle.update(coupX, coupy);
            



        }
        input.close();
    }
}
