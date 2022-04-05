package dameo.logic;

import java.util.ArrayList;

public abstract class Piece {
    /** Posicion X de la respectiva pieza en el tablero */
    private int x;
    /** Posicion Y de la respectiva pieza en el tablero */
    private int y;
    /** Cualidad que permite saber si una pieza esta vivao */
    private boolean is_alive;
    /** Cualidad que permite saber si una piezaes de colo blancoa */
    final private boolean is_white;
    /** Path del archiv donde se encuentra la imagen de la piezaoa */
    private String file_path;
    /** Rreferencia al tablero donde se encuentra la fichaa */
    public LogicBoard board;

    /**
     * Constructor de objeto pieza, este objeto es abstracto ya que cada tipo de
     * pieza tiene cualidades distintas
     * 
     * @param x         posicion x de la pieza
     * @param y         posicion y de la pieza
     * @param is_white  identificador de color para la pieza
     * @param file_path path de la imagen de la pieza
     * @param board     tablero en el cual se encuentra la pieza
     */
    public Piece(int x, int y, boolean is_white, String file_path, LogicBoard board) {
        this.is_white = is_white;
        this.x = x;
        this.y = y;
        this.file_path = file_path;
        this.board = board;
        this.is_alive = true;
    }

    /***
     * Metodo para coseguir el path del archivo de imagen de la pieza
     * 
     * @return String file_path
     */
    public String getFilePath() {
        return file_path;
    }

    /**
     * Metodo para establecer el path del archivo de imagen de la pieza
     * 
     * @param path
     */
    public void setFilePath(String path) {
        this.file_path = path;
    }

    public boolean getIsAlive() {
        return this.is_alive;
    }

    public void setIsAlive(boolean state) {
        this.is_alive = state;
    }

    /**
     * Metodo para saber si una pieza es blanca
     * 
     * @return boolean is_white
     */
    public boolean isWhite() {
        return is_white;
    }

    /**
     * Metodo para saber si una pieza es roja
     * 
     * @return boolean !is_white
     */
    public boolean isRed() {
        return !is_white;
    }

    /**
     * Metodo para establecer las coordenadas X de una pieza
     * 
     * @param x retorna la pos x del tablero
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Metodo para establecer las coordenadas Y de una pieza
     * 
     * @param y retorna la pos y del tablero
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metodo para conseguir la coordenada X donde se encuentra una pieza
     * 
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Metodo para conseguir la coordenada Y donde se encuentra una pieza
     * 
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Metodo para conocer si a donde me deseo mover se encuentra libre o ocupado
     * por una pieza
     * 
     * @param destination_x pos X donde me deseo mover
     * @param destination_y pos Y donde me deseo mover
     * @return true en caso de estar libre o false en caso de estar ocupada
     */
    public boolean destinationIsFree(int destination_x, int destination_y) {
        if (board.getPiece(destination_x, destination_y) == null) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para saber si una determinada pieza es enemiga o aliada
     * 
     * @param posibleEnemy ficha de la cual deseo saber si es un enemigo
     * @return true en caso de ser enemiga y false en caso de ser alidada
     */
    public boolean isEnemy(Piece posibleEnemy) {
        if (posibleEnemy == null) {
            return false;
        }
        if (this.isWhite() == posibleEnemy.isWhite()) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que revisa si el movimiento que deseo hacer como pieza es valido Osea
     * que si las coordenadas a las que me deseso mover se encuentran dentro del
     * tablero
     * 
     * @param destination_x pos X a la que me deseo mover
     * @param destination_y pos Y a la que me deseo mover
     * @return true en caso de ser valido false en caso de no
     */
    public boolean isNotValidMove(int destination_x, int destination_y) {
        return (destination_x > 7 || destination_y > 7 || destination_y < 0 || destination_x < 0);
    }

    /**
     * Metodo abstracto el cual es el encargado de matar o comer piezas del tablero
     * el motivo por el que es abstracto es por que cada pieza tiene distintas
     * formas de comer
     * 
     * @param destination_x pos X de la pieza que me quiero comer
     * @param destination_y pos Y de la pieza que me quiero comer
     * @return true si se puede comer false en caso de no
     */
    
    public abstract ArrayList<Pair<Integer,Integer>> verticalKillMoves(int initial_x, int initial_y);
    
    public abstract ArrayList<Pair<Integer,Integer>> horizontalKillMoves(int initial_x, int initial_y);
    /**
     * Metodo que verifica si una pieza tiene la obligacion de comer
     * 
     * @return true en caso de tener la obligacion false en caso de no
     */
    public abstract boolean haveToKill();

    /**
     * Metodo que valida si una pieza se puede mover, una pieza se puede mover si su
     * posicion es valida, si adonde se desea mover esta vacia y en caso de que se
     * encuentre vacia, validar que el click sea un movimeinto valido
     * 
     * @param destination_x pos X donde me deseo mover
     * @param destination_y pos Y donde me deseo mover
     * @return true en caso de que se pueda y false en caso de que no
     */
    public abstract boolean canMove(int destination_x, int destination_y);
}
