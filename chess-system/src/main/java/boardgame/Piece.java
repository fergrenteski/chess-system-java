
package boardgame;

public abstract class Piece {
    protected Position position;
    private Board board;

    /**
     * Contrutor com parâmetros com posição nula
     * @param board 
     */
    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    /**
     * Retorna o tabuleiro
     * @return 
     */
    protected Board getBoard() {
        return board;
    }

    /**
     * Clase abstrata das possiveis possições de cada peça
     * @return 
     */
    public abstract boolean[][] possibleMoves();
    
    /**
     * Retorna a posição possivel da peça
     * @param position
     * @return 
     */
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }
    
    /**
     * Retorna se existe alguma possição possivel
     * @return 
     */
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if(mat[i][j]){
                    return true;       
                }
            }
        }
        return false;
    }
}
