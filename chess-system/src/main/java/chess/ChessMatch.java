
package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;
    
    /**
     * Construtor com o tabuleiro
     */
    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
    }
    
    /**
     * Retorna as peças de xadrez
     * @return 
     */
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++)  {
            for(int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
    
    /**
     * Coloca uma nova peça convertendo a posição Xadrez para a matriz
     * @param column
     * @param row
     * @param piece 
     */
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    
    /**
     * Setup de inicialização das peças
     */
    private void initialSetup() {
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new King(board, Color.BLACK));
    }
}