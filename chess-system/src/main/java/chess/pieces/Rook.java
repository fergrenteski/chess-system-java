
package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{
    
    /**
     * Construtor Super
     * @param board
     * @param color 
     */
    public Rook(Board board, Color color) {
        super(board, color);
    }
    
    /**
     * Letra da peça no tabuleiro
     * @return 
     */
    @Override
    public String toString(){
        return "R";
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
