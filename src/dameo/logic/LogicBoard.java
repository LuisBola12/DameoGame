package dameo.logic;

import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class LogicBoard {
    // Atributos
    /** Arreglo de las piezas blancas */
    private ArrayList<Piece> White_Pieces;
    /** Arreglo de las piezas rojas */
    private ArrayList<Piece> Red_Pieces;
    /** Variable para manejar una pieza temporal que se desea mover */
    private Piece Active_Piece;
    /** Cantidad de filas del tablero */
    private final int rows = 8;
    /** Cantidad de columnas del tablero */
    private final int cols = 8;
    /** Tablero con enteros por posicion */
    private final Integer[][] BoardGrid;


    // Funciones

    /**
     * Constructor del tablero
     * 
     * @param whitePlayer Nombre del jugador blanco
     * @param redPlayer   Nombre del jugador rojo
     * @param boardFrame  Frame del tablero
     */
    public LogicBoard() {

        this.BoardGrid = new Integer[rows][cols];
        this.White_Pieces = new ArrayList<Piece>();
        this.Red_Pieces = new ArrayList<Piece>();
        initGrid();
    }

    /**
     * Metodo que agrega las piezas al tablero en la posicion indicada
     */
    public void initGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                BoardGrid[i][j] = 0;
            }
        }
        // Se agregan las piezas rojas en el tablero
        Red_Pieces.add(new Men(0, 0, false, "red.png", this));
        Red_Pieces.add(new Men(1, 0, false, "red.png", this));
        Red_Pieces.add(new Men(2, 0, false, "red.png", this));
        Red_Pieces.add(new Men(3, 0, false, "red.png", this));
        Red_Pieces.add(new Men(4, 0, false, "red.png", this));
        Red_Pieces.add(new Men(5, 0, false, "red.png", this));
        Red_Pieces.add(new Men(6, 0, false, "red.png", this));
        Red_Pieces.add(new Men(7, 0, false, "red.png", this));
        Red_Pieces.add(new Men(1, 1, false, "red.png", this));
        Red_Pieces.add(new Men(2, 1, false, "red.png", this));
        Red_Pieces.add(new Men(3, 1, false, "red.png", this));
        Red_Pieces.add(new Men(4, 1, false, "red.png", this));
        Red_Pieces.add(new Men(5, 1, false, "red.png", this));
        Red_Pieces.add(new Men(6, 1, false, "red.png", this));
        Red_Pieces.add(new Men(2, 2, false, "red.png", this));
        Red_Pieces.add(new Men(3, 2, false, "red.png", this));
        Red_Pieces.add(new Men(4, 2, false, "red.png", this));
        Red_Pieces.add(new Men(5, 2, false, "red.png", this));

        // Se agregan las piezas blancas en el tablero
        White_Pieces.add(new Men(0, 7, true, "white.png", this));
        White_Pieces.add(new Men(1, 7, true, "white.png", this));
        White_Pieces.add(new Men(2, 7, true, "white.png", this));
        White_Pieces.add(new Men(3, 7, true, "white.png", this));
        White_Pieces.add(new Men(4, 7, true, "white.png", this));
        White_Pieces.add(new Men(5, 7, true, "white.png", this));
        White_Pieces.add(new Men(6, 7, true, "white.png", this));
        White_Pieces.add(new Men(7, 7, true, "white.png", this));
        White_Pieces.add(new Men(1, 6, true, "white.png", this));
        White_Pieces.add(new Men(2, 6, true, "white.png", this));
        White_Pieces.add(new Men(3, 6, true, "white.png", this));
        White_Pieces.add(new Men(4, 6, true, "white.png", this));
        White_Pieces.add(new Men(5, 6, true, "white.png", this));
        White_Pieces.add(new Men(6, 6, true, "white.png", this));
        White_Pieces.add(new Men(2, 5, true, "white.png", this));
        White_Pieces.add(new Men(3, 5, true, "white.png", this));
        White_Pieces.add(new Men(4, 5, true, "white.png", this));
        White_Pieces.add(new Men(5, 5, true, "white.png", this));
    }

    
    public void addRedPiece (Piece piece) {
        Red_Pieces.add(piece);
    }
    
    public void addWhitePiece (Piece piece) {
        White_Pieces.add(piece);
    }
    
    public void removeRedPiece(Piece piece) {
        Red_Pieces.remove(piece);
    }
    
    public void removeWhitePiece(Piece piece) {
        White_Pieces.remove(piece);
    }
    
    public ArrayList<Piece> getWhite_Pieces() {
        return White_Pieces;
    }

    public ArrayList<Piece> getRed_Pieces() {
        return Red_Pieces;
    }

    public void setRed_Pieces(ArrayList<Piece> Red_Pieces) {
        this.Red_Pieces = Red_Pieces;
    }

    public Piece getActive_Piece() {
        return Active_Piece;
    }

    public void setActive_Piece(Piece Active_Piece) {
        this.Active_Piece = Active_Piece;
    }

    /**
     * Devuelve la pieza dependiendo de las coordenadas
     * 
     * @param x posicion
     * @param y posicion
     * @return devuelve la pieza
     */
    public Piece getPiece(final int x, final int y) {
        for (final Piece p : White_Pieces) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        for (final Piece p : Red_Pieces) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }
    
    public void setMatrix(String matrix){
        int x = 0;
        int y = 0;
        char at = 'a';
        Red_Pieces.clear();
        White_Pieces.clear();
        for(int index = 0;index < matrix.length();index++){
            at = matrix.charAt(index);
            if(index % 8 == 0 && index!= 0){
                y=0;
                x+=1;   
            }
            if(at !='\n'){
                if(at == 'r'){
                    Red_Pieces.add(new Men(y, x, false, "red.png", this));
                }
                if(at == 'R'){
                    Red_Pieces.add(new King(y, x, false, "redking.png", this));
                }
                if(at=='w'){
                    White_Pieces.add(new Men(y, x, true, "white.png", this));
                }
                if(at=='W'){
                    White_Pieces.add(new King(y, x, true, "whiteKing.png", this));
                }
            }
            y+=1;
        }
    }
    
    public String getMatrix(){
        String matrix = "";
        for(int index = 0;index<8;index++){
            for(int innerIndex = 0;innerIndex<8;innerIndex++){
               Piece tempPiece = this.getPiece(innerIndex,index);
               if(tempPiece != null){
                   if(tempPiece.isWhite()){
                       if(tempPiece.getClass().getSimpleName().equals("King")){
                           matrix+= "W";
                       }else{
                           matrix+="w";
                       }
                   }else{
                       if(tempPiece.getClass().getSimpleName().equals("King")){
                           matrix+= "R";
                       }else{
                           matrix+="r";
                       }
                   }
               }else{
                   matrix+= "x";
               }
            }
            matrix+="\n";
        }
        return matrix;
    }
}
