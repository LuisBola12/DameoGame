package dameo.logic;

import java.util.ArrayList;

public class Men extends Piece {
    /**
     * Constructor de una Pieza Men
     * 
     * @param x         Posicion en x
     * @param y         Posicion en y
     * @param is_white  Si es blanco o negro
     * @param file_path Ubicacion del archivo de la imagen
     * @param board     Tablero al que pertenece
     */
    public Men(int x, int y, boolean is_white, String file_path, LogicBoard board) {
        super(x, y, is_white, file_path, board);
    }

    /**
     * Implementacion del metodo abstracto
     */
    @Override
    public boolean canMove(int destination_x, int destination_y) {
        if (this.getIsAlive() == false) {
            return false;
        }
        if (isNotValidMove(destination_x, destination_y)) {
            return false;
        }
        if (destinationIsFree(destination_x, destination_y)) {
            if (this.isWhite()) {
                return this.moveWhite(destination_x, destination_y);
            } else {
                return this.moveRed(destination_x, destination_y);
            }
        } else {
            return false;
        }

    }

    boolean verticalRedMove(int destination_x,int destination_y, int myXPos, int myYPos, int moving_spaces) {
        Piece aux;
        for (int i = 1; i < moving_spaces; i++) {
            aux = board.getPiece(myXPos, myYPos+i);
            if (aux != null && aux.isWhite()){
                return false;
            }
            if (aux == null){
                return false;
            }
        }
        return true;
    }
    
    boolean leftRedDiagonalMove(int destination_x, int destination_y, int myXPos, int myYPos, int moving_spaces){
        Piece aux;
        for (int i = 1; i < moving_spaces; i++) {
            aux = board.getPiece(myXPos-i, myYPos+i);
            if (aux != null && aux.isWhite()){
                return false;
            }
            if (aux == null){
                return false;
            }
        }
        return true;
    }
    
    boolean rightRedDiagonalMove(int destination_x, int destination_y, int myXPos, int myYPos, int moving_spaces){
        Piece aux;
        for (int i = 1; i < moving_spaces; i++) {
            aux = board.getPiece(myXPos+i, myYPos+i);
            if (aux != null && aux.isWhite()){
                return false;
            }
            if (aux == null){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo que revisa si el movieminto de una pieza roja es valida, por ejemplo
     * en caso de que sea una pieza 'men' adelante y en diagonales
     * 
     * @param destination_x pos X donde me deseo mover
     * @param destination_y pos Y donde me deseo mover
     * @return true en caso de que se pueda y false en caso de que no
     */
    public boolean moveRed(int destination_x, int destination_y) {
        int myXPos = this.getX();
        int myYPos = this.getY();
        int moving_spaces = Math.abs(destination_y - myYPos);
        if ((destination_x != myXPos && destination_y == myYPos) || (destination_y < myYPos))
            return false;
        if (myXPos == destination_x) {
            return this.verticalRedMove(destination_x, destination_y, myXPos, myYPos, moving_spaces);
        }else {
            if (destination_x < myXPos) {
                return this.leftRedDiagonalMove(destination_x, destination_y, myXPos, myYPos, moving_spaces);
            } else {
                return this.rightRedDiagonalMove(destination_x, destination_y, myXPos, myYPos, moving_spaces);
            }
        }  
    }

    
    
    boolean verticalWhiteMove(int destination_x,int destination_y, int myXPos, int myYPos, int moving_spaces) {
        Piece aux;
        for (int i = 1; i < moving_spaces; i++) {
            aux = board.getPiece(myXPos, myYPos-i);
            if (aux != null && aux.isWhite() == false){
                return false;
            }
            if (aux == null){
                return false;
            }
        }
        return true;
    }
    
    boolean leftWhiteDiagonalMove(int destination_x, int destination_y, int myXPos, int myYPos, int moving_spaces){
        Piece aux;
        for (int i = 1; i < moving_spaces; i++) {
            aux = board.getPiece(myXPos-i, myYPos-i);
            if (aux != null && aux.isWhite() == false){
                return false;
            }
            if (aux == null){
                return false;
            }
        }
        return true;
    }
    
    boolean rightWhiteDiagonalMove(int destination_x, int destination_y, int myXPos, int myYPos, int moving_spaces){
        Piece aux;
        for (int i = 1; i < moving_spaces; i++) {
            aux = board.getPiece(myXPos+i, myYPos-i);
            if (aux != null && aux.isWhite() == false){
                return false;
            }
            if (aux == null){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo que revisa si el movieminto de una pieza blanca es valida, por ejemplo
     * en caso de que sea una pieza 'men' adelante y en diagonales
     * 
     * @param destination_x pos X donde me deseo mover
     * @param destination_y Y donde me deseo mover
     * @return true en caso de que se pueda y false en caso de que no
     */
    public boolean moveWhite(int destination_x, int destination_y) {
        int myXPos = this.getX();
        int myYPos = this.getY();
        int moving_spaces = Math.abs(destination_y - myYPos);
        int vertical_moving_spaces = Math.abs(destination_y - myYPos); 
        Piece aux;
        if ((destination_x != myXPos && destination_y == myYPos) || (destination_y > myYPos))
            return false;
        if (myXPos == destination_x) {
            return this.verticalWhiteMove(destination_x, destination_y, myXPos, myYPos, moving_spaces);
        }else {
            if (destination_x < myXPos) {
                return this.leftWhiteDiagonalMove(destination_x, destination_y, myXPos, myYPos, moving_spaces);
            } else {
                return this.rightWhiteDiagonalMove(destination_x, destination_y, myXPos, myYPos, moving_spaces);
            }
        }  
    }

    @Override 
    public ArrayList<Pair<Integer,Integer>> verticalKillMoves(int myXPos, int myYPos){
        ArrayList<Pair<Integer,Integer>> killList = new ArrayList<Pair<Integer,Integer>>();
        if (this.isEnemy(board.getPiece(myXPos, myYPos + 1)) && !isNotValidMove(myXPos, myYPos + 2)) {
            if (board.getPiece(myXPos, myYPos + 2) == null) {
                Pair<Integer,Integer> pair = new Pair<Integer,Integer>(myXPos, myYPos + 2);
                killList.add(pair);
            }
        }
        if (this.isEnemy(board.getPiece(myXPos, myYPos - 1)) && !isNotValidMove(myXPos, myYPos - 2)) {
            if (board.getPiece(myXPos, myYPos - 2) == null) {
                Pair<Integer,Integer> pair = new Pair<Integer,Integer>(myXPos, myYPos - 2);
                killList.add(pair);
            }
        }
        return killList;
    }
    
    @Override
    public ArrayList<Pair<Integer,Integer>> horizontalKillMoves(int myXPos, int myYPos) {
        ArrayList<Pair<Integer,Integer>> killList = new ArrayList<Pair<Integer,Integer>>();
        if (this.isEnemy(board.getPiece(myXPos + 1, myYPos)) && !isNotValidMove(myXPos + 2, myYPos)) {
            if (board.getPiece(myXPos + 2, myYPos) == null) {
                Pair<Integer,Integer> pair = new Pair<Integer,Integer>(myXPos + 2, myYPos);
                killList.add(pair);
            }
        }
        if (this.isEnemy(board.getPiece(myXPos - 1, myYPos)) && !isNotValidMove(myXPos - 2, myYPos)) {
            if (board.getPiece(myXPos - 2, myYPos) == null) {
               Pair<Integer,Integer> pair = new Pair<Integer,Integer>(myXPos - 2, myYPos);
                killList.add(pair);
            }
        }
        return killList;
    }
    
    /**
     * Implementacion del metodo abstracto
     */
    @Override
    public boolean haveToKill() {
        int myX = this.getX();
        int myY = this.getY();
        ArrayList<Pair<Integer,Integer>> verticalKillMoves = verticalKillMoves(myX,myY);
        ArrayList<Pair<Integer,Integer>> horizontalKillMoves = horizontalKillMoves(myX,myY);
        if (verticalKillMoves.isEmpty() && horizontalKillMoves.isEmpty()) {
           return false;
        }
        return true;
    }

}
