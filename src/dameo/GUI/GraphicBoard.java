package dameo.GUI;

import dameo.controller.DameoController;
import dameo.logic.Piece;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
/**
 * Clase encargada de generar el tablero y colocar las fichas
 */
public class GraphicBoard extends JComponent {

    private DameoController controller;
    private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
    /** Tamaño de la casilla */
    private final int Square_Width = 66;
    /** Variable que guarda el JFrame del tablero */
    private final BoardFrame boardFrame;
    /** Cualidad que permite saber si una pieza esta viva */
    private ArrayList<DrawingShape> Static_Shapes;
    /** Cualidad que permite saber si una pieza esta viva */
    private ArrayList<DrawingShape> Piece_Graphics;
    // TODO: revisar si se ocupa
    /** Path de la imagen del tablero */
    private final String board_file_path = "images/board.png";
    /** Path de la imagen del cajon */
    private final String active_square_file_path = "images/active_square.png";

    /**
     * Constructor del tablero
     * 
     * @param whitePlayer Nombre del jugador blanco
     * @param redPlayer   Nombre del jugador rojo
     * @param boardFrame  Frame del tablero
     */
    public GraphicBoard(final BoardFrame boardFrame,
            DameoController controller) {
        this.controller = controller;
        this.Static_Shapes = new ArrayList();
        this.Piece_Graphics = new ArrayList();
        this.boardFrame = boardFrame;
        this.setBackground(new Color(37, 13, 84));
        this.setPreferredSize(new Dimension(870, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseListener(mouseAdapter);
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }

    /**
     * Dibuja el tablero y pone las piezas en la posicion que les corresponde
     */
    public void drawBoard() {
        Piece_Graphics.clear();
        Static_Shapes.clear();
        ArrayList<Piece> White_Pieces = this.controller.getLogicBoard().getWhite_Pieces();
        ArrayList<Piece> Red_Pieces = this.controller.getLogicBoard().getRed_Pieces();
        final Image cajon = loadImage("images/Cajon.png");
        final Image board = loadImage(board_file_path);
        Piece activePiece = this.controller.getActive_Piece();

        Static_Shapes.add(
                new DrawingImage(board, new Rectangle2D.Double(0, 0, board.getWidth(null), board.getHeight(null))));
        Static_Shapes.add(new DrawingImage(cajon, new Rectangle2D.Double(520, 0, 350, 520)));
        if (activePiece != null) {
            final Image active_square = loadImage(active_square_file_path);
            Static_Shapes.add(new DrawingImage(active_square,
                    new Rectangle2D.Double((Square_Width - 1) * activePiece.getX(),
                            (Square_Width - 1) * activePiece.getY(), active_square.getWidth(null),
                            active_square.getHeight(null))));
        }
        for (int i = 0; i < White_Pieces.size(); i++) {
            final int COL = White_Pieces.get(i).getX();
            final int ROW = White_Pieces.get(i).getY();
            final Image piece = loadImage("images/" + White_Pieces.get(i).getFilePath());
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(Square_Width * COL, Square_Width * ROW,
                    piece.getWidth(null), piece.getHeight(null))));
        }
        for (int i = 0; i < Red_Pieces.size(); i++) {
            final int COL = Red_Pieces.get(i).getX();
            final int ROW = Red_Pieces.get(i).getY();
            final Image piece = loadImage("images/" + Red_Pieces.get(i).getFilePath());
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(Square_Width * COL, Square_Width * ROW,
                    piece.getWidth(null), piece.getHeight(null))));
        }
        this.repaint();
    }

    /**
     * Retorna la imagen nula
     * 
     * @return static Image
     */
    public static Image getNULL_IMAGE() {
        return NULL_IMAGE;
    }

    /**
     * Setea la imagen nula del tablero
     * 
     * @param Image
     */
    public static void setNULL_IMAGE(Image NULL_IMAGE) {
        GraphicBoard.NULL_IMAGE = NULL_IMAGE;
    }

    public DameoController getController() {
        return controller;
    }

    /**
     * Retorna el vector de piezas estaticas
     * 
     * @return ArrayList<DrawingShape>
     */
    public ArrayList<DrawingShape> getStaticShapes() {
        return Static_Shapes;
    }

    /**
     * Retorna el vector de Piezas Graficas
     * 
     * @return ArrayList<DrawingShape>
     */
    public ArrayList<DrawingShape> getPieceGraphics() {
        return Piece_Graphics;
    }

    /**
     * 
     * @return Devuelve el tablero
     */
    public GraphicBoard getThis() {
        return this;
    }

    /**
     * 
     * @return Devuelve el BoardFrame
     */
    public BoardFrame getBoardFrame() {
        return this.boardFrame;
    }

    // ---------------------------------------------------------------------------------------------------
    // Control de los clics con el mouse
    // ---------------------------------------------------------------------------------------------------

    /**
     * En este metodo se controla la accion que se ejecuta al dar clic en una pieza
     */private final MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mouseClicked(final MouseEvent e) {

        }

        @Override
        public void mousePressed(final MouseEvent e) {
            final int d_X = e.getX();
            final int d_Y = e.getY();
            final int Clicked_Row = d_Y / Square_Width;
            final int Clicked_Column = d_X / Square_Width;
            controller.interpretateTurn(Clicked_Row, Clicked_Column);
            drawBoard();
            int turn_Counter = controller.getTurnCounter();
            if (turn_Counter % 2 == 1) {
                getBoardFrame().turnOfPlayer.setText("|  Turno de: " + controller.getRedPlayer());
            } else {
                getBoardFrame().turnOfPlayer.setText("|  Turno de: " + controller.getWhitePlayer());
            }
            if (controller.getWinner() != null) {
                String mensaje;
                mensaje = "Gana el jugador " + controller.getWinner();
                JOptionPane.showMessageDialog(null, mensaje);
                System.exit(0);
            }
        }

        @Override
        public void mouseDragged(final MouseEvent e) {
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
        }

        @Override
        public void mouseWheelMoved(final MouseWheelEvent e) {
        }
    };

    private void adjustShapePositions(final double dx, final double dy) {
        Static_Shapes.get(0).adjustPosition(dx, dy);
        this.repaint();
    }

    /**
     * Carga la imagen del path ingresado como parametro
     * 
     * @param imageFile path de la imagen
     * @return la imagen
     */
    private Image loadImage(final String imageFile) {
        try {
            return ImageIO.read(new File(imageFile));
        } catch (final IOException e) {
            return NULL_IMAGE;
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        drawShapes(g2);
    }

    private void drawBackground(final Graphics2D g2) {
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawShapes(final Graphics2D g2) {
        for (final DrawingShape shape : Static_Shapes) {
            shape.draw(g2);
        }
        for (final DrawingShape shape : Piece_Graphics) {
            shape.draw(g2);
        }
    }
}
