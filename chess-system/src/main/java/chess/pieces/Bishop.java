
package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{
/**
     * Construtor Super
     * @param board
     * @param color 
     */
    public Bishop(Board board, Color color) {
        super(board, color);
    }
    
    /**
     * Letra da peça no tabuleiro
     * @return 
     */
    @Override
    public String toString(){
        return "B";
    }

    /**
     * Possível movimento da peça
     * @return 
     */
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0, 0);
        
        // NW
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        while(getBoard().positionExists(p)  && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        // NE
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        while(getBoard().positionExists(p)  && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        // SE
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        while(getBoard().positionExists(p)  && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        // SW
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        while(getBoard().positionExists(p)  && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        return mat;
    }
}
