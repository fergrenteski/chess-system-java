
package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    /**
     * Contrutor Super
     * @param board
     * @param color 
     */
    public King(Board board, Color color) {
        super(board, color);
    }
    
    /**
     * Letra da peça no tabuleiro
     * @return 
     */
    @Override
    public String toString() {
        return "K";
    }
    
    /**
     * Possível movimento da peça
     * @return 
     */
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
