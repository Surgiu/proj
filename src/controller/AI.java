package controller;

import model.Chessboard;

public class AI {
    private int piece;

    private Chessboard chessboard;

    public AI(Chessboard chessboard) {
        this.chessboard = chessboard;
    }


    public Chessboard getChessboard() {
        return chessboard;
    }
}
