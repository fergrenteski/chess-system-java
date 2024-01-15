
package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
    private Color color;
    private int moveCount;

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
     * Retorna a contagem de movimento
     * @return 
     */
    public int getMoveCount(){
        return moveCount;
    }
    
    /**
     * Adiciona contagem de movimento
     */
    public void increaseMoveCount(){
        moveCount++;
    }
    
    /**
     * Retira contagem de movimento
     */
    public void decreaseMoveCount(){
        moveCount--;
    }
    
    /**
     * Retorna a posição da peça
     * @return 
     */
    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
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
