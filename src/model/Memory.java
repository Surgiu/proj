package model;

import java.io.Serial;
import java.io.Serializable;

public class Memory implements Serializable {
    @Serial
    private static final long serialVersionUID = 550081L;
    private final Chessboard chessboard;
    private int currentTime;
    private ChessboardPoint chessboardPoint;
    private boolean change;
    private boolean theme;


    public Memory(Chessboard chessboard, int currentTime, ChessboardPoint previous, boolean change, boolean theme) {
        this.chessboard = chessboard;
        this.currentTime = currentTime;
        this.chessboardPoint = previous;
        this.change = change;
        this.theme = theme;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public boolean isTheme() {
        return theme;
    }

    public void setTheme(boolean theme) {
        this.theme = theme;
    }
}
//int[][] status = new int[64][2];

    /*64个长度为2的数组构成的二维数组
    第一个数组代表当前执行方。11为蓝，12为红
    往后的63个数组代表棋盘信息。
    第一位：
        0：代表未被占领
        1：代表被蓝占领
        2：代表被红占领
    第二位：
        -1：无占领
        其他值：占领者的rank
     */
//    public void updateMemory() {
//        int count = 0;
//        for (int i = 0; i < chessboard.getGrid().length; i++) {
//            for (int j = 0; j < chessboard.getGrid()[i].length; j++) {
//                ChessPiece piece = chessboard.getChessPieceAt(new ChessboardPoint(i, j));
//                count++;
//                if (piece == null) {
//                    status[count][0] = 0;
//                    status[count][1] = -1;
//                } else {
//                    if (piece.getOwner().equals(PlayerColor.BLUE)) {
//                        status[count][0] = 1;
//                        status[count][1] = piece.hashCode();
//                    }
//                }
//            }
//        }
//    }