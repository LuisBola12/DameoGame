package dameo.GUI;

import dameo.controller.DameoController;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * Clase que hereda de JFrame y genera el frame
 * 
 */
public class BoardFrame extends JFrame {
    /** Instancia de la clase componente */
    Component component;
    /** Instancia de la barra de menus */
    JMenuBar menuBar;
    /** Instancia del menu de juego */
    JMenu menuGame;
    /** Intancica de la etiqueta que indica a quien le toca juagr */
    JLabel turnOfPlayer;
    /** Nombre del jugador que empieza */
    String initialPlayer;
    private DameoController controller;
    private GraphicBoard board;
    /**
     * Constructor de la ventana grafica
     * 
     * @param whitePlayer nombre del jugador que usara las fichas blancas
     * @param redPlayer   nombre del jugador que usara las fichas rojas
     */
    public BoardFrame(String whitePlayer, String redPlayer) {
        this.initialPlayer = whitePlayer;
        this.setJMenuBar(this.createMenus());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Dameo");
        this.setResizable(false);
        controller = new DameoController(whitePlayer, redPlayer);
        board = new GraphicBoard(this, controller);
        controller.setGraphicBoard(board);
        component = board;
        this.add(component, BorderLayout.CENTER);
        this.setLocation(200, 50);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public JLabel getTurnOfPlayer() {
        return turnOfPlayer;
    }

    /**
     * Metodo que se encarga de crear la barra con los diferentes menus de opciones
     * del juego.
     * 
     * @return la barra de menus 'menuBar'
     */
    public JMenuBar createMenus() {
        menuBar = new JMenuBar();
        menuGame = new JMenu("Juego");
        turnOfPlayer = new JLabel("|  Turno de: " + initialPlayer);
        JMenuItem menuHelp = new JMenuItem("Ayuda");
        JMenuItem loadGame = new JMenuItem("Cargar partida");
        JMenuItem saveGame = new JMenuItem("Guardar partida");

        // Si se preciona el munu de ayuda
        menuHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Reglas:\n" + "1. Las piezas mas claras inician primero.\n"
                        + "2. Solo me puedo mover diagonalmente y vertical hacia adelante, en caso de hacerlo, solo puedo moverme a una casilla que se encuentre vacía.\n"
                        + "3. Si una ficha \"men\" llega al extremo contrario donde comienzan mis fichas me puedo convertir en Rey. \n"
                        + "4. Un Rey se puede mover en cualquier dirección, cualquier numero de espacios siempre y cuando se encuentren desocupados.\n"
                        + "5. En caso de tener una línea continua de fichas iguales a la mía por delante, debo de tener la opcion de saltar sobre ellas siempre y cuando la casilla por delante se encuentre vacía.\n"
                        + "6. Tengo la obligacion de comer siempre que pueda hacerlo, y una vez haya comido, sigue siendo mi turno.\n"
                        + "7. Las formas de comer permitidas son en base a los puntos cardinales, osea solo puedo comer a quienes se encuentren adelante mío, atras a mi izquierda y a mi derecha; simepre y cuando la casilla siguiente se encuentre vacía.\n"
                        + "8. Las fichas comidas son retiradas del tablero.\n"
                        + "9. Se gana cuando me quedo sin fichas, o cuando no me puedo mover hacia ningúna casilla.");
            }
        });
        // Si se preciona el menu de cargar partida
        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser filechooser = new JFileChooser("./");
                filechooser.showSaveDialog(null);
                if(filechooser.getSelectedFile()!= null){
                    controller.loadGame(filechooser.getSelectedFile().getPath());
                }
            }
        });
        // Si se preciona el menu de guardar partida
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser filechooser = new JFileChooser("./");
                filechooser.showSaveDialog(null);
                if(filechooser.getSelectedFile()!= null){
                    controller.saveGame(filechooser.getSelectedFile().getPath());
                }
            }
        });
        
        menuGame.setMnemonic(KeyEvent.VK_F);
        menuHelp.setMnemonic(KeyEvent.VK_H);

        // Se agregan los submenues en el respectivo menu al que corresponden.
        menuBar.add(menuGame);
        menuGame.add(loadGame);
        menuGame.add(saveGame);
        menuGame.add(menuHelp);
        menuBar.add(turnOfPlayer);

        return menuBar;
    }
}