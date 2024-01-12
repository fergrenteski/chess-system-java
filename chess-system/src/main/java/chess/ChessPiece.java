
package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
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
    
    /**
     * Verifica se tem alguma peça adversária em uma posição
     * @param position
     * @return 
     */
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }
}
