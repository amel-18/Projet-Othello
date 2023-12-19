
/**
 * la classe permet de gerer chaque partie de jeu.
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.Font;
import java.util.ArrayList;

public class Partie extends JPanel {

    private gameState state;
    private Board board;
    private Ennemi ennemi;


    /**
     * Constructeur de la classe Partie permettant de créer une nouvelle instance de
     * la classe
     * 
     * @param taille taille de la grille
     * @param name   nom de la grille
     */
    public Partie(int taille, String name) {

        setPreferredSize(new Dimension((taille + 2) * 50+25, (2 + taille) * 50+25)); // le Panel prend la taille du plateau
        this.board = new Board(taille, name);
        this.state = gameState.INIT;
    }

    /**
     * Constructeur de la classe Partie permettant de créer une nouvelle instance de
     * la classe
     * 
     * @param taille taille de la grille
     * @param name   nom de la grille
     * @param state  l'état du jeu
     */
    public Partie(int taille, String name, gameState state) {
        setPreferredSize(new Dimension((taille + 2) * 50+25, (taille + 2) * 50+25));
        this.board = new Board(taille, name);
        this.state = state;
    }

    /**
     * Methode permettant de mettre à jour la grille
     * 
     * @param la grille
     */
    public void setBoard(Board b) {
        this.board = b;
    }

    /**
     * Methode permettant de mettre à jour l'ennemi
     * 
     * @param l'ennemi
     */
    public void setEnnemi(Ennemi e) {
        this.ennemi = e;
    }

    /**
     * Methode permettant de mettre à jour l'etat du jeu
     * 
     * @param l'etat du jeu
     */
    public void setState(gameState g) {
        this.state = g;
    }

    /**
     * Methode permettant d'avoir l'ennemi du jeu
     * 
     * @return l'ennemi en cours
     */
    public Ennemi getEnnemi() {
        return this.ennemi;
    }


    /**
     * Méthode permettant de mettre à jour le nom de la grille
     * 
     * @param s chaine de caractère avec lequel on va modifier le nom de la grille
     */
    public void setName(String s) {
        this.board.setName(s);
    }

    /** 
     * Methode on donne null au mouselistner du panel
     */
     public void removeMouseListener() {
        this.addMouseListener(null);
    }


    /**
     * Méthode permettant d'avoir l'etat du jeu
     * @return l'etat de du jeu
     */
    public gameState getState() {
        return this.state;
    }

    /**
     * Méthode d'avoir la grille du jeu
     * @return la grille du jeu
     */
    public Board getBoard() {
        return this.board;
    }


    /**
     * Methode qui permet d'avoir le nom du jeu
     * 
     * @return le nom de la grille du jeu
     */
    public String getName() {
        return this.board.getName();
    }


    /**
     * Methode qui permet d'avoir la taille de la grille
     * 
     * @return la taille de la grille en cours
     */
    public int getLength() {
        return this.board.getLength();
    }

    /**
     * Méthode qui permet de réinitialiser la grille
     */
    public void init() {
        this.board.init(8);
        this.state = gameState.INIT;

    }

    /**
     * réinitialisation du grille en fonction de sa taille
     * 
     * @param taille de la grille
     */
    public void init(int taille) {
        this.board.init(taille);
        this.state = gameState.INIT;

    }

    /**
     * réinitialisation de la grille
     * 
     * @param taille de la grille
     * @param name   nom de la grille
     */
    public void init(int taille, String name, gameState state) {
        this.board.init(taille);
        this.setName(name);
        this.board.setPlayer(Pawn.BLACK);
        this.setState(state);
    }

    /**
     * initialisation d'une partie deja commencé
     * @param b la grille du jeu
     */    public void init(Board b) {
        this.board = Board.copie(b);
        this.state = gameState.INPROGRESS;

    }

    /**
     * Methode qui dessine le tableau sur le Panel
     * @param g objet de Graphics
     */
    public void show(Graphics g) {
        this.board.show(g);
        
        if (this.state.name() == "INIT") {
            Font font = new Font("Arial", Font.BOLD, 16);
            
            if(this.board.getLength()<8){
                font = new Font("Arial", Font.BOLD, 10);
            }
            
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("Pour commencer une partie allez dans Fichier ^^", 25, (this.board.getLength() + 2) * 50);
        }
        if (this.state.name() == "INPROGRESS") {
            g.drawString("joueur courant: " + this.board.getPlayer().name() ,25+(this.board.getLength()/2)*50, (this.board.getLength() + 2) * 50  );
            g.drawString("nombre de pion blanc :"+ this.board.nbPawn(Pawn.WHITE),25+(this.board.getLength()/2)*50, (this.board.getLength() + 2) * 50 +15);            
            g.drawString("nombre de pion noir :" + this.board.nbPawn(Pawn.BLACK),25+(this.board.getLength()/2)*50,(this.board.getLength() + 2) * 50+30);
        }
    }

    /**
     * methode qui actualise un plateau à partir d'un coup joué
     * 
     * @param x largeur de la grille
     * @param y longeur de la grille
     */
    public void update(int x, int y) {
        this.board.update(x, y);
    }

    /**
     * methode qui fait jouer l'IA DEBUTANTE
     * on stocke les coups possibles dans une arraylist puis on en joue un au hasard
     */
    public void getAmouv() {
        ArrayList<Integer> possibletab = new ArrayList<Integer>();
        int taille = 0;
        System.out.println("coups possibles :");
        for (int i = 0; i < this.board.getLength(); i++) {
            for (int j = 0; j < this.board.getLength(); j++) {
                if (this.board.coupPossible(i, j)) {
                    System.out.println(i + "  " + j);
                    possibletab.add(i);
                    possibletab.add(j);
                    taille = taille + 2;
                }
            }
        }
        System.out.println("taille du tableau : " + taille);
        double coup = Math.random() * (taille);
        if (((int) coup )% 2 != 0) {
            coup = ((int) coup )-1;
        }
        System.out.println("coup joué  coup: " + coup + "   " + (coup + 1));
        System.out.println("coupp joué (int) coup :"+ (int) coup +"   "+(  ((int ) coup) + 1)  );
        System.out.println("coup joué array.( (in) coup ): " + possibletab.get((int) coup) + "   " + (possibletab.get(((int) coup) + 1))  );
        System.out.println(this.board.getPlayer());
        System.out.println();
        System.out.println();


        this.update(possibletab.get((int) coup), possibletab.get(((int) coup) + 1));

    }

    /**
     * dessine le tableau selon son état courant
     * 
     * @param g Objet de Graphics
     */
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.show(g);
        for (int i = 0; i < this.board.getlength(); i++) {
            for (int j = 0; j < this.board.getlength(); j++) {
                if (this.state == gameState.INPROGRESS) {
                    if (this.board.coupPossible(i, j)) {
                        g.setColor(Color.YELLOW);
                        g.drawOval(69 + 50 * i, 69 + 50 * j, 15, 15);
                        g.fillOval(69 + 50 * i, 69 + 50 * j, 15, 15);
                    }
                }

            }
        }

    }

    /**
     * Mehtode qui retourne vrai si une partie est finie
     * 
     * @return true si une partie est fini false sinon
     */
    public boolean isOver() {
        return this.board.isOver();
    }

    /**
     * Methode qui retourne le gagnant d'une partie
     * @return le pion du joueur gagnant
     */
    public Pawn winner() {
        return this.board.winner();
    }
    

    /**
     * Methode qui sauvegarde une partie dans un fichier texte
     * @exception IOException générée si la partie spécifiée du fichier est
     * verrouillée.
     */

    public void save() {

        try {
            this.setState(gameState.INPROGRESS);
            FileWriter input = new FileWriter(this.board.getName() + ".txt");
            input.write(this.board.getPlayer().ordinal() + "\n" + this.board.getName() + "\n" + this.board.getLength()
                    + "\n" + this.ennemi.ordinal() + "\n"); // joueur en cours et nom de la partie en cour
            for (int i = 0; i < this.board.getLength(); i++) {
                for (int j = 0; j < this.board.getLength(); j++) {
                    input.write(this.board.getBoard()[i][j].ordinal() + "\n"); // ordinal de chaque pion du tableau
                                                                               // [i][j] du plateau
                }
            }

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * methode qui charge une partie sauvegardée
     * 
     * @param Chaine de caractere qui va permettre de recharger une partie d'apres
     *               son nom
     * @exception FileNotFoundException declenché si partie n'existe pas
     * @exception IOException           declenché impossible de lire le fichier
     * @exception NotEnnemiException    declenché si un objet n'est pas un ennemi
     * @exception NotAPawn declenché si un objet n'est pas un pion
     */
    public void restore(String nom) {
        this.setState(gameState.INPROGRESS);
        try {
            String namee = nom + ".txt";
            FileReader fread = new FileReader(namee);
            BufferedReader bread = new BufferedReader(fread);

            int joueur = Integer.valueOf(bread.readLine()); // joueur en cours (pion)
            System.out.println("joueur courant  :" + joueur);

            String name = bread.readLine(); // nom de la partie
            System.out.println("nom de la partie " + name);

            int length = Integer.valueOf(bread.readLine()); // taille du plateau
            System.out.println("taille du plateau  :" + length);

            int ennemi = Integer.valueOf(bread.readLine()); // ennemi de la partie a charger
            System.out.println(" ennemi de la partie :" + ennemi + "|");

            this.setEnnemi(Ennemi_of_int(ennemi)); // on set l'ennemi de la partie
            Board b = new Board(length, name);
            b.setPlayer(Pawn_of_int(joueur)); // on set le joueur courant du plateau
            System.out.println("taille du board : " + b.getLength());

            System.out.println("joueur courant du  board " + b.getPlayer());

            for (int i = 0; i < b.getLength(); i++) {
                for (int j = 0; j < b.getLength(); j++) {
                    b.setPawn(i, j, Pawn_of_int(Integer.valueOf(bread.readLine()))); // on remplie le tableau
                }
            }
            b.showterminal();
            this.setBoard(b);
            repaint();
            bread.close();

        } catch (FileNotFoundException e) {
            System.err.println("cette partie n'existe pas");
        } catch (IOException e) {
            System.err.println("impossible de lire le fichier");
        } catch (NotEnnemiException n) {
            System.err.println("ceci n'est pas un ennemi");
        } catch (NotAPawn n) {
            System.err.println("ceci n'est pas un pion");
        }

    }

    /**
     * Methode qui permet de convertir un entier en un objet Ennemi
     * @param t entier à convertir
     * @return l'ennemi
     * @throws NotEnnemiException déclenché si un entier rentré n'est pas un ennemi
     */
    public static Ennemi Ennemi_of_int(int t) throws NotEnnemiException {
        if (t == Ennemi.HUMAIN.ordinal()) {
            System.out.println("cas humain");
            return Ennemi.HUMAIN;
        } else if (t == Ennemi.IABEGINNER.ordinal()) {
            System.out.println("cas debutant");

            return Ennemi.IABEGINNER;
        } else if (t == Ennemi.IAEXPERT.ordinal()) {
            System.out.println("cas expert");

            return Ennemi.IAEXPERT;
        } else {
            System.out.println("|" + t + "|");

            throw new NotEnnemiException("ce n'est pas un ennemi");

        }
    }


    /**
     * Methode qui permet de convertir un entier en un pion
     * @param t l'entier à convertir
     * @return un pion
     * @throws NotAPawn declenché si l'entier rentrer n'est pas un pion
     */
    public static Pawn Pawn_of_int(int t) throws NotAPawn {
        if (t == Pawn.BLACK.ordinal()) {
            System.out.println("cas pion noir");
            return Pawn.BLACK;
        } else if (t == Pawn.EMPTY.ordinal()) {
            System.out.println("cas vide");

            return Pawn.EMPTY;
        } else if (t == Pawn.WHITE.ordinal()) {
            System.out.println("cas eblacn");

            return Pawn.WHITE;
        } else {
            System.out.println("|" + t + "|");

            throw new NotAPawn("ce n'est pas un ennemi");

        }
    }


    /**
     * Methode qui emt à jour une nouvelle partie du jeu
     * 
     * @exception NumberFormatException declenché si on entre pas un nombre pour la taille du plateau
     */
    public void setNewGame() {

        String result = (String) JOptionPane.showInputDialog(
                null, // Pas de composant parent
                "Contre qui voulez-vous jouer :", // Message à afficher
                "Sélection du joueur 2", // Titre de la fenêtre
                JOptionPane.QUESTION_MESSAGE, // Type de message
                null, // Icône (null pour aucune icône)
                new String[] { "HUMAIN", "IADEBUTANT", "IAEXPERT" }, // Valeurs possibles
                "HUMAIN" // Valeur par défaut
        );

        try{
            // Traitement du résultat
        if (result != null) {
            // L'utilisateur a sélectionné une couleur
            switch (result) {
                case "HUMAIN":
                    this.setEnnemi(Ennemi.HUMAIN);
                    break;
                case "IADEBUTANT":
                    this.setEnnemi(Ennemi.IABEGINNER);
                    break;
                case "IAEXPERT":
                    this.setEnnemi(Ennemi.IAEXPERT);
                    break;
            }
            String name = (String) JOptionPane.showInputDialog("saisissez un nom pour la partie");
            while(name == ""){
                name = (String) JOptionPane.showInputDialog("saisissez un nom avec au moins un caractère ");
            }
            int taille = Integer.parseInt(JOptionPane.showInputDialog("saisissez une taille pour le plateau"));
            
            while ( taille <2 || taille > 12 ) {
                taille = Integer.parseInt(JOptionPane.showInputDialog("saisissez une taille possible pour le plateau"));
            }

            this.init(taille, name, gameState.INPROGRESS); // on initialise le plateau
            this.repaint();
            Listener click = new Listener(this);
            this.addMouseListener(click);
        } else {
            System.out.println("Vous venez d'annuler la selection ");
        }
        }catch( NumberFormatException n ){
            System.out.println("ce n'est pas un nombre");
        }
        
    }

}
