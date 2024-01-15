
package chess;

import boardgame.Position;

public class ChessPosition {
    private char column;
    private int row;

    /**
     * Contrutor com parâmetros
     * @param column
     * @param row 
     */
    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error instantiating ChessPosition. Vliad are from a1 to h8.");
        }
        this.column = column;
        this.row = row;
    }

    /**
     * Retorna a Coluna
     * @return 
     */
    public char getColumn() {
        return column;
    }
    
    /**
     * Retorna a Linha
     * @return 
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Converte a operação de Xadrez por Matriz
     * @return 
     */
    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }
    
    /**
     * Converte a operação de Matriz por Xadrez
     * @param position
     * @return 
     */
    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
    }
    
    /**
     * Concatenação das colunas e linhas
     * @return 
     */
    @Override
    public String toString() {
        return "" + column + row;
    }
}
