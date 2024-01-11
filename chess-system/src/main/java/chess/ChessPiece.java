
package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece{
    private Color color;

    /**
     * Construtor com parâmetros
     * @param board
     * @param color 
     */
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    /**
     * Retorna a cor da peça
     * @return 
     */
    public Color getColor() {
        return color;
    }
}
