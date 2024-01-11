
package application;

import chess.ChessMatch;

/**
 *
 * @author luiz.grenteski
 */
public class Program {

    public static void main(String[] args) {
        
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
    }
}
