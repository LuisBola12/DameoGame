package dameo.logic;

import java.util.ArrayList;

/**
 * Hereda de pieza, Posee capacidades superiores al hombre
 */
public class King extends Piece {
    /**
     * Constructor de una Pieza King
     * 
     * @param x         Posicion en x
     * @param y         Posicion en y
     * @param is_white  Si es blanco o negro
     * @param file_path Ubicacion del archivo de la imagen
     * @param board     Tablero al que pertenece
     */
    public King(int x, int y, boolean is_white, String file_path, LogicBoard board) {
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
            int myX = this.getX();
            int myY = this.getY();
            ArrayList<Pair<Integer,Integer>> verticalList = this.verticalMoves(myX, myY);
            ArrayList<Pair<Integer,Integer>> horizontalList = this.horizontalMoves(myX, myY);
            ArrayList<Pair<Integer,Integer>> lowerDiagonalList = this.lowerDiagonalMoves(myX, myY);
            ArrayList<Pair<Integer,Integer>> upperDiagonalList = this.upperDiagonalMoves(myX, myY);
            for(final Pair p : verticalList) {
                if (p.isInPair(destination_x, destination_y)){
                   return true;
               } 
            }
            for(final Pair p : horizontalList) {
                if (p.isInPair(destination_x, destination_y)){
                   return true;
               }
            }
            for(final Pair p : lowerDiagonalList) {
                if (p.isInPair(destination_x, destination_y)){
                   return true;
               }
            }
            for(final Pair p : upperDiagonalList) {
                if (p.isInPair(destination_x, destination_y)){
                   return true;
               }
            }
            return false;
        } else {
            return false;
        }
        //(x+1, y): one step horizontal move to the right. 
        //(x-1, y): one step horizontal move to the left. 
        //(x+1, y+1): one step diagonal move up-right. 
        //(x-1, y-1): one step diagonal move down-left. 
        //(x-1, y+1): one step diagonal move left-up. 
        //(x+1, y-1): one step diagonal move right-down. 
        //(x, y+1): one step downward. 
        //(x, y-1): one step upward.
    }
    
    public ArrayList<Pair<Integer,Integer>> upperDiagonalMoves(int initial_x, int initial_y){
        ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
        int possible_y = initial_y;
        int possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la derecha
        while(true){
            possible_y++;
            possible_x++;
            if(isNotValidMove(possible_x, possible_y)){
                break;
            }
            if(board.getPiece(possible_x, possible_y) == null){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(possible_x, possible_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        possible_y = initial_y;
        possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la izquierda
        while(true){
            possible_x++;
            possible_y--;
            if(isNotValidMove(possible_x, possible_y)){
                break;
            }
            if(board.getPiece(possible_x, possible_y) == null){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(possible_x, possible_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        return pairList;
    }
    public ArrayList<Pair<Integer,Integer>> lowerDiagonalMoves(int initial_x, int initial_y){
        ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
        int possible_y = initial_y;
        int possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la derecha
        while(true){
            possible_y--;
            possible_x--;
            if(isNotValidMove(possible_x, possible_y)){
                break;
            }
            if(board.getPiece(possible_x, possible_y) == null){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(possible_x, possible_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        possible_y = initial_y;
        possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la izquierda
        while(true){
            possible_x--;
            possible_y++;
            if(isNotValidMove(possible_x, possible_y)){
                break;
            }
            if(board.getPiece(possible_x, possible_y) == null){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(possible_x, possible_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        return pairList;
    }
    
    public ArrayList<Pair<Integer,Integer>> verticalMoves(int initial_x, int initial_y) {
        ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
        int possible_y = initial_y;
        //PosiblesMovimientos horizontales hacia la derecha
        while(true){
            possible_y++;
            if(isNotValidMove(initial_x, possible_y)){
                break;
            }
            if(board.getPiece(initial_x, possible_y) == null){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(initial_x, possible_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        possible_y = initial_y;
        //PosiblesMovimientos horizontales hacia la izquierda
        while(true){
            possible_y--;
            if(isNotValidMove(initial_x, possible_y )){
                break;
            }
            if(board.getPiece(initial_x, possible_y) == null){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(initial_x, possible_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        return pairList;
    }
    public ArrayList<Pair<Integer,Integer>> horizontalMoves(int initial_x, int initial_y){
        ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
        int possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la derecha
        while(true){
            possible_x++;
            if(isNotValidMove(possible_x, initial_y)){
                break;
            }
            if(board.getPiece(possible_x, initial_y)== null){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(possible_x, initial_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la izquierda
        while(true){
            possible_x--;
            if(isNotValidMove(possible_x, initial_y)){
                break;
            }
            if(board.getPiece(possible_x, initial_y) == null){
               Pair<Integer, Integer> pair = new Pair<Integer, Integer>(possible_x, initial_y);
                pairList.add(pair);
            }else{
                break;
            }
        }
        return pairList;
    }
    
    @Override
    public ArrayList<Pair<Integer,Integer>> horizontalKillMoves(int initial_x, int initial_y){
        ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
        int possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la derecha
        while(true){
            if(isNotValidMove(possible_x, initial_y)){
                break;
            }
            if(board.getPiece(possible_x+1, initial_y) != null && (board.getPiece(possible_x+2, initial_y) == null)){
                if(this.isEnemy(board.getPiece(possible_x+1, initial_y))&& !isNotValidMove(possible_x+2,initial_y)) {
                     Pair<Integer,Integer> pair = new Pair<Integer,Integer>(possible_x+2, initial_y);
                    pairList.add(pair);
                } else {
                    break;
                }
            } else if(board.getPiece(possible_x+1, initial_y) != null) {
               break; 
            }
            possible_x++;
        }
        possible_x = initial_x;
        //PosiblesMovimientos horizontales hacia la izquierda
        while(true){
            if(isNotValidMove(possible_x, initial_y)){
                break;
            }
            if(board.getPiece(possible_x-1, initial_y) != null && (board.getPiece(possible_x-2, initial_y) == null)){
                 if(this.isEnemy(board.getPiece(possible_x-1, initial_y))&& !isNotValidMove(possible_x-2,initial_y)) {
                     Pair<Integer,Integer> pair = new Pair<Integer,Integer>(possible_x-2, initial_y);
                    pairList.add(pair);
                } else {
                    break;
                }
            }else if(board.getPiece(possible_x-1, initial_y) != null) {
                break;
            }
            possible_x--;
        }
        return pairList;
    }
    
    @Override
    public ArrayList<Pair<Integer,Integer>> verticalKillMoves(int initial_x, int initial_y) {
        ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
        int possible_y = initial_y;
        while(true){
            if(isNotValidMove(initial_x, possible_y)){
                break;
            }
            if(board.getPiece(initial_x, possible_y+1) != null && (board.getPiece(initial_x, possible_y+2) == null)){
                if(this.isEnemy(board.getPiece(initial_x, possible_y+1)) && !isNotValidMove(initial_x,possible_y+2)) {
                    Pair<Integer,Integer> pair = new Pair<Integer,Integer>(initial_x, possible_y+2);
                    pairList.add(pair);
                } else {
                    break;
                }
            } else if (board.getPiece(initial_x, possible_y+1) != null){
                break;
            }
            possible_y++;
        }
        possible_y = initial_y;
        while(true){
            if(isNotValidMove(initial_x, possible_y )){
                break;
            }
            if(board.getPiece(initial_x, possible_y-1) != null && (board.getPiece(initial_x, possible_y-2) == null)){
               if(this.isEnemy(board.getPiece(initial_x, possible_y-1)) && !isNotValidMove(initial_x,possible_y-2)) {
                     Pair<Integer,Integer> pair = new Pair<Integer,Integer>(initial_x, possible_y-2);
                    pairList.add(pair);
                } else {
                    break;
                }
            }else if (board.getPiece(initial_x, possible_y-1) != null){
                break;
            }
            possible_y--;
        }
        return pairList;
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
