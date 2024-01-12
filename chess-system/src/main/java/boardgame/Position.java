
package boardgame;

public class Position {
    private int row;
    private int column;

    /**
     * Construtor com parâmetros
     * @param row
     * @param column 
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Retorna a linha da peça
     * @return 
     */
    public int getRow() {
        return row;
    }

    /**
     * Coloca a linha da peça
     * @param row 
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Retorna a coluna da peça
     * @return 
     */
    public int getColumn() {
        return column;
    }

    /**
     * Coloca a coluna da peça
     * @param column 
     */
    public void setColumn(int column) {
        this.column = column;
    }
    
    /**
     * Coloca os valores das linhas e colunas
     * @param row
     * @param column 
     */
    public void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    /**
     * Retorna a posição da peça
     * @return 
     */
    @Override
    public String toString(){
        return row + "," + column;
    }
}
