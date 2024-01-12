
package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    
    private int turn;
    private Color currentPlayer;
    private Board board;
    
    /**
     * Construtor com o tabuleiro
     */
    public ChessMatch(){
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }
    
    /**
     * Retorna o turno do jogo
     * @return 
     */
    public int getTurn(){
        return turn;
    }
    
    /**
     * Retorna o player de jogada atual
     * @return 
     */
    public Color getCurrentPlayer() {
        return currentPlayer;
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
     * Movimentos possíveis da peça
     * @param sourcePosition
     * @return 
     */
    public boolean [][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    
    /**
     * Movimento da peça
     * @param sourcePosition
     * @param targetPosition
     * @return 
     */
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
       Position source = sourcePosition.toPosition();
       Position target = targetPosition.toPosition();
       validateSourcePosition(source);
       validateTargetPosition(source, target);
       Piece capturedPiece = makeMove(source, target);
       nextTurn();
       return (ChessPiece) capturedPiece;
    }
    
    /**
     * Valida a posição de entrada da jogada
     * @param position 
     */
    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position");
        }
        if(currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("The choosen piece not yours");
        }
        if(!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is not possible moves for the chosen piece");
        }
    }
    
    /**
     * Valida a posição de destino da jogada
     * @param source
     * @param Target 
     */
    private void validateTargetPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The choosen piece can't move to target position");
        }
    }
    
    /**
     * Realiza o movimento de peças
     * @param source
     * @param target
     * @return 
     */
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        
        return capturedPiece;
    }
    
    /**
     * Verifica o próximo turno
     */
    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
        
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
