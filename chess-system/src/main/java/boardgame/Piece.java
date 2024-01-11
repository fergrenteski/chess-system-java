
package boardgame;

public class Piece {
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

}
