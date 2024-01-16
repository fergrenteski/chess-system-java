
package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    
    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();
    
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
     * Retorna o check
     * @return 
     */
    public boolean getCheck() {
        return check;
    }
    
    /**
     * Retorna o checkMate
     * @return 
     */
    public boolean getCheckMate(){
        return checkMate;
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
       
       if(testCheck(currentPlayer)) {
           undoMove(source, target, capturedPiece);
           throw new ChessException("You can't put yourself in check");
       }
       
       check = (testCheck(opponent(currentPlayer))) ? true : false;
       
       if(testCheckMate(opponent(currentPlayer))) {
           checkMate = true;
       } else {
            nextTurn(); 
       }
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
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        
        if(capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        
        return capturedPiece;
    }
    
    /**
     * Desfazer o movimento de peças.
     * @param source
     * @param target
     * @param capturedPiece 
     */
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);
        
        if(capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }
    
    /**
     * Verifica o próximo turno
     */
    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    /**
     * Retorna a Cor do oponente
     * @param color
     * @return 
     */
    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    /**
     * Retorna o rei do oponente
     * @param color
     * @return 
     */
    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no" + color + "king on the board");
    }
    
    /**
     * Testar se o rei está em cheque
     * @param color
     * @return 
     */
    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
        for(Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        } 
        return false;
    }
    
    /**
     * Testa se o rei está em cheque mate
     * @param color
     * @return 
     */
    private boolean testCheckMate(Color color) {
        if(!testCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for(int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if(mat[i][j]) {
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturePiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturePiece);
                        if(!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    /**
     * Coloca uma nova peça convertendo a posição Xadrez para a matriz
     * @param column
     * @param row
     * @param piece 
     */
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }
    
    /**
     * Setup de inicialização das peças
     */
    private void initialSetup() {
        
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
        
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
    }
}
