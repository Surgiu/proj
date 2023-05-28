package controller;

import model.*;

import java.util.ArrayList;
import java.util.Random;

public class AI {
    private int pieceNum;
    private int go;
    private Chessboard chessboard;

    public AI(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public ChessboardPoint[] move() {
        ArrayList<ChessboardPoint> points = new ArrayList<>();
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessPiece piece1 = this.chessboard.getChessPieceAt(new ChessboardPoint(i, j));
                if (piece1 != null && piece1.getOwner().equals(PlayerColor.RED)) {
                    points.add(new ChessboardPoint(i, j));
                }
            }
        }
        ArrayList<ChessboardPoint> canMove;
        do {
            pieceNum = new Random().nextInt(points.size());
            canMove = chooseDest(points.get(pieceNum));
            go = new Random().nextInt(canMove.size());
        } while (canMove.size() == 0);
        ChessboardPoint[] step = new ChessboardPoint[2];
        step[0] = points.get(pieceNum);
        step[1] = canMove.get(go);
        return step;
    }

    public void doMove(ChessboardPoint[] step) {
        ChessboardPoint src = step[0];
        ChessboardPoint dest = step[1];
        if (chessboard.isValidMove(src, dest)) {
            chessboard.moveChessPiece(src, dest);
        } else if (chessboard.isValidCapture(src, dest)) {
            chessboard.captureChessPiece(src, dest);
        }
    }

    //该棋可走的范围
    private ArrayList<ChessboardPoint> chooseDest(ChessboardPoint point) {
        ArrayList<ChessboardPoint> destinations = new ArrayList<>();
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint dest = new ChessboardPoint(i, j);
                if (chessboard.getChessPieceAt(dest) == null) {
                    if (chessboard.isValidMove(point, dest)) {
                        destinations.add(dest);
                    }
                } else {
                    if (chessboard.isValidCapture(point, dest)) {
                        destinations.add(dest);
                    }
                }
            }
        }
        return destinations;
    }
}
