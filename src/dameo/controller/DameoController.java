package dameo.controller;

import dameo.GUI.GraphicBoard;
import dameo.logic.King;
import dameo.logic.LogicBoard;
import dameo.logic.Men;
import dameo.logic.Pair;
import dameo.logic.Piece;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class DameoController {

    private String winner;
    private LogicBoard logicBoard;
    /**
     * Contador para controlar el turno del jugador
     */
    private int turn_Counter;
    /**
     * Nombre del jugador rojo
     */
    private String redPlayer;
    /**
     * Nombre del jugador blanco
     */
    private String whitePlayer;
    /**
     * Contador de las piezas rojas comidas
     */
    private int red_pieces_counter;
    /**
     * Contador de las piezas rojas comidas
     */
    private int white_pieces_counter;
    /**
     * Coordenada en X para colocar las piezas rojas comidas al lado del tablero
     */
    private int eatenRedX;
    /**
     * Coordenada en y para colocar las piezas rojas comidas al lado del tablero
     */
    private int eatenRedY;
    /**
     * Contador que permite acomodar las piezas comidas en el tablero
     */
    private int eatenWhiteX;
    /**
     * Contador que permite acomodar las piezas comidas en el tablero
     */
    private int eatenWhiteY;
    private Piece Active_Piece;
    private GraphicBoard graphicBoard;

    /**
     * Variable para manejar una pieza temporal que se desea mover
     */
    public DameoController(final String whitePlayer, final String redPlayer) {
        this.logicBoard = new LogicBoard();
        this.redPlayer = redPlayer;
        this.turn_Counter = 0;
        this.whitePlayer = whitePlayer;
        this.red_pieces_counter = 18;
        this.white_pieces_counter = 18;
        this.eatenRedX = 8;
        this.eatenRedY = 7;
        this.eatenWhiteX = 8;
        this.eatenWhiteY = 0;
        this.winner = null;
        this.Active_Piece = null;
    }

    // SETTERS AND GETTERS
    public LogicBoard getLogicBoard() {
        return logicBoard;
    }

    public void setLogicBoard(LogicBoard logicBoard) {
        this.logicBoard = logicBoard;
    }

    public String getWinner() {
        return winner;
    }

    public GraphicBoard getGraphicBoard() {
        return graphicBoard;
    }

    public void setGraphicBoard(GraphicBoard graphicBoard) {
        this.graphicBoard = graphicBoard;
    }

    /**
     * Decrementa el contador de las piezas blancas en juego
     */
    public void decrementWhitePiecesCounter() {
        this.white_pieces_counter--;
    }

    /**
     * Decrementa el contador de las piezas rojas en juego
     */
    public void decrementRedPiecesCounter() {
        this.red_pieces_counter--;
    }

    /**
     * Metodo que controla la posicion de las fichas rojas comidas
     */
    public void incrementEatenRed() {
        if (this.eatenRedX == 12) {
            this.eatenRedX = 8;
            this.eatenRedY--;
        } else {
            this.eatenRedX++;
        }
    }

    /**
     * Metodo que controla la posicion de las fichas blancas comidas
     */
    public void incrementEatenWhite() {
        if (this.eatenWhiteX == 12) {
            this.eatenWhiteX = 8;
            this.eatenWhiteY++;
        } else {
            this.eatenWhiteX++;
        }
    }

    /**
     *
     * @return Devuelve el nombre del jugador rojo
     */
    public String getRedPlayer() {
        return this.redPlayer;
    }

    /**
     *
     * @return Devuelve el nombre del jugador blanco
     */
    public String getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * Retorna el contador de las piezas rojas
     *
     * @return red_pieces_counter
     */
    public int getRed_pieces_counter() {
        return red_pieces_counter;
    }

    public void setRed_pieces_counter(int red_pieces_counter) {
        this.red_pieces_counter = red_pieces_counter;
    }

    /**
     * Retorna el contador de las piezas blancas
     *
     * @return white_pieces_counter
     */
    public int getWhite_pieces_counter() {
        return white_pieces_counter;
    }

    public int getTurnCounter() {
        return turn_Counter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turn_Counter = turnCounter;
    }

    public Piece getActive_Piece() {
        return Active_Piece;
    }

    public Piece isInKillLists(ArrayList<Pair<Integer, Integer>> horizontalKills, ArrayList<Pair<Integer, Integer>> verticalKills, int destination_x, int destination_y, int myXPos, int myYPos) {
        Piece toKill = null;
        for (Pair p : horizontalKills) {
            if (p.isInPair(destination_x, destination_y)) {
                if (myXPos < destination_x) {
                    toKill = logicBoard.getPiece(destination_x - 1, destination_y);
                } else {
                    toKill = logicBoard.getPiece(destination_x + 1, destination_y);
                }
            }
        }
        for (Pair p : verticalKills) {
            if (p.isInPair(destination_x, destination_y)) {
                if (myYPos < destination_y) {
                    toKill = logicBoard.getPiece(destination_x, destination_y - 1);
                } else {
                    toKill = logicBoard.getPiece(destination_x, destination_y + 1);
                }
            }
        }
        return toKill;
    }

    public boolean kill(int destination_x, int destination_y, int myXPos, int myYPos) {
        ArrayList<Pair<Integer, Integer>> horizontalKills = Active_Piece.horizontalKillMoves(myXPos, myYPos);
        ArrayList<Pair<Integer, Integer>> verticalKills = Active_Piece.verticalKillMoves(myXPos, myYPos);
        Piece toKill = isInKillLists(horizontalKills, verticalKills, destination_x, destination_y, myXPos, myYPos);
        if (toKill != null && toKill.getIsAlive() == true) {
            if (toKill.isWhite()) {
                toKill.setX(eatenWhiteX);
                toKill.setY(eatenWhiteY);
                toKill.setIsAlive(false);
                this.incrementEatenWhite();
                this.decrementWhitePiecesCounter();
            } else {
                toKill.setX(eatenRedX);
                toKill.setY(eatenRedY);
                toKill.setIsAlive(false);
                this.incrementEatenRed();
                this.decrementRedPiecesCounter();
            }
            return true;
        } else {
            return false;
        }
    }

    public void attemptToPromotePiece(int Clicked_Row, int Clicked_Column) {
        if (turn_Counter % 2 == 1 && Clicked_Row == 7) {
            logicBoard.removeRedPiece(Active_Piece);
            Active_Piece = new King(Clicked_Column, Clicked_Row, false, "redKing.png", logicBoard);
            logicBoard.addRedPiece(Active_Piece);
        } else if (turn_Counter % 2 == 0 && Clicked_Row == 0) {
            logicBoard.removeWhitePiece(Active_Piece);
            Active_Piece = new King(Clicked_Column, Clicked_Row, true, "WhiteKing.png", logicBoard);
            logicBoard.addWhitePiece(Active_Piece);
        }
    }
    
    public boolean theresKillingPieces() {
        ArrayList<Piece> list = null;
        if (turn_Counter % 2 == 1) {
            list = logicBoard.getRed_Pieces();
        } else if (turn_Counter % 2 == 0) {
            list = logicBoard.getWhite_Pieces();
        }
        for (Piece p : list){
            if (p.haveToKill() && (p.getIsAlive() == true)) {
                return true;
            }
        }
        return false;
    }
    
    public void interpretateTurn(final int Clicked_Row, final int Clicked_Column) {
        boolean is_whites_turn = true;
        if (turn_Counter % 2 == 1) {
            is_whites_turn = false;
        }

        final Piece clicked_piece = logicBoard.getPiece(Clicked_Column, Clicked_Row);
        if (Active_Piece == null && clicked_piece != null
                && ((is_whites_turn && clicked_piece.isWhite()) || (!is_whites_turn && clicked_piece.isRed()))) {
            if (theresKillingPieces()){
                if (clicked_piece.haveToKill()) {
                    Active_Piece = clicked_piece;
                } else {
                    Active_Piece = null;
                } 
            } else {
                Active_Piece = clicked_piece;
            }
        } else if (Active_Piece != null && Active_Piece.getX() == Clicked_Column
                && Active_Piece.getY() == Clicked_Row && !Active_Piece.haveToKill()) {
            Active_Piece = null;
        } else if (Active_Piece != null && Active_Piece.haveToKill()
                && ((is_whites_turn && Active_Piece.isWhite()) || (!is_whites_turn && Active_Piece.isRed()))) {
            // do move
            int myPosX = Active_Piece.getX();
            int myPosY = Active_Piece.getY();
            if (this.kill(Clicked_Column, Clicked_Row, myPosX, myPosY)) {
                attemptToPromotePiece(Clicked_Row, Clicked_Column);
                Active_Piece.setX(Clicked_Column);
                Active_Piece.setY(Clicked_Row);
                if (!Active_Piece.haveToKill()) {
                    Active_Piece = null;
                    turn_Counter++;
                }
            }
        } else if (Active_Piece != null && Active_Piece.canMove(Clicked_Column, Clicked_Row)
                && ((is_whites_turn && Active_Piece.isWhite()) || (!is_whites_turn && Active_Piece.isRed()))) {
            attemptToPromotePiece(Clicked_Row,Clicked_Column);
            Active_Piece.setX(Clicked_Column);
            Active_Piece.setY(Clicked_Row);
            Active_Piece = null;
            turn_Counter++;

        }
        if (red_pieces_counter == 0) {
            this.winner = this.whitePlayer;
        } else if (white_pieces_counter == 0) {
            this.winner = this.redPlayer;
        }

    }

    public void setPiecesEliminated() {
        int number_of_red_eaten = 18 - this.red_pieces_counter;
        int number_of_white_eaten = 18 - this.white_pieces_counter;
        for (int i = 0; i < number_of_red_eaten; i++) {
            if (number_of_red_eaten != 0) {
                Piece piece = new Men(0, 0, false, "red.png", logicBoard);
                piece.setX(this.eatenRedX);
                piece.setY(this.eatenRedY);
                this.incrementEatenRed();
                piece.setIsAlive(false);
                logicBoard.getRed_Pieces().add(piece);
            }
        }
        for (int i = 0; i < number_of_white_eaten; i++) {
            if (number_of_white_eaten != 0) {
                Piece piece = new Men(0, 0, true, "white.png", logicBoard);
                piece.setX(this.eatenWhiteX);
                piece.setY(this.eatenWhiteY);
                this.incrementEatenWhite();
                piece.setIsAlive(false);
                logicBoard.getWhite_Pieces().add(piece);
            }
        }
    }

    public void saveGame(String filePath) {
        try {
            try (FileWriter writer = new FileWriter(filePath)) {
                String saveFile = "";
                saveFile += Integer.toString(this.getTurnCounter()) + "\n";
                saveFile += whitePlayer + "\n";
                saveFile += redPlayer + "\n";
                saveFile += Integer.toString(this.red_pieces_counter) + "\n";
                saveFile += Integer.toString(this.white_pieces_counter) + "\n";
                saveFile += Integer.toString(8) + "\n";
                saveFile += Integer.toString(7) + "\n";
                saveFile += Integer.toString(8) + "\n";
                saveFile += Integer.toString(0) + "\n";
                saveFile += logicBoard.getMatrix();
                writer.write(saveFile);
            }
        } catch (IOException i) {
            System.out.println("Error writing on file");
        }
    }

    public void loadGame(String filePath) {
        BufferedReader buffreader;
        try {
            buffreader = new BufferedReader(new FileReader(filePath));
            String line = "";
            String tablero = "";
            for (int index = 0; index < 17; index++) {
                line = buffreader.readLine();
                if (index == 0) {
                    this.turn_Counter = Integer.parseInt(line);
                }
                if (index == 1) {
                    this.whitePlayer = line;
                }
                if (index == 2) {
                    this.redPlayer = line;
                }
                if (index == 3) {
                    this.red_pieces_counter = Integer.parseInt(line);
                }
                if (index == 4) {
                    this.white_pieces_counter = Integer.parseInt(line);
                }
                if (index == 5) {
                    this.eatenRedX = Integer.parseInt(line);
                }
                if (index == 6) {
                    this.eatenRedY = Integer.parseInt(line);
                }
                if (index == 7) {
                    this.eatenWhiteX = Integer.parseInt(line);
                }
                if (index == 8) {
                    this.eatenWhiteY = Integer.parseInt(line);
                }
                if (index >= 9) {
                    tablero += line;
                }
            }
            if (turn_Counter % 2 == 1) {
                graphicBoard.getBoardFrame().getTurnOfPlayer().setText("|  Turno de: " + redPlayer);
            } else {
                graphicBoard.getBoardFrame().getTurnOfPlayer().setText("|  Turno de: " + whitePlayer);
            }
            logicBoard.setMatrix(tablero);
            setPiecesEliminated();
            this.graphicBoard.drawBoard();
        } catch (IOException e) {
            System.out.println("No se pudo");
        }
    }
}
