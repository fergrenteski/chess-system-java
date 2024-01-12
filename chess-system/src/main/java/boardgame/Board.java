
package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    /**
     * Contrutor com parâmetros
     * @param rows
     * @param columns 
     */
    public Board(int rows, int columns) {
        if(rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    /**
     * Retorna as linhas
     * @return 
     */
    public int getRows() {
        return rows;
    }

    /**
     * Retorna as colunas
     * @return 
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Retorna a peça ques está naquela posição (posição do tabuleiro)
     * @param row
     * @param column
     * @return 
     */
    public Piece piece(int row, int column) {
        if(!positionExists(row, column)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }
    
    /**
     * Retorna a peça ques está naquela posição (posição matriz)
     * @param position
     * @return 
     */
    public Piece piece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }
    
    /**
     * Coloca a peça na posição
     * @param piece
     * @param position 
     */
    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
    
    /**
     * Posição existente por linha e coluna (tabuleiro)
     * @param row
     * @param column
     * @return 
     */
    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0  && column < columns;
    }
    
    /**
     * Retorna se a posição existe
     * @param position
     * @return 
     */
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }
    
    /**
     * Retorna se existe peça naquela posição
     * @param position
     * @return 
     */
    public boolean thereIsAPiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }
}
