
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
     * Coloca as linhas
     * @param rows 
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Retorna as colunas
     * @return 
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Coloca as colunas
     * @param columns 
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }
    /**
     * Retorna a peça ques está naquela posição (posição do tabuleiro)
     * @param row
     * @param column
     * @return 
     */
    public Piece piece(int row, int column) {
        return pieces[row][column];
    }
    
    /**
     * Retorna a peça ques está naquela posição (posição matriz)
     * @param position
     * @return 
     */
    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }
    
    /**
     * Coloca a peça na posição
     * @param piece
     * @param position 
     */
    public void placePiece(Piece piece, Position position) {
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
}
