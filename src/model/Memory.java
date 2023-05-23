package model;

import java.io.Serial;
import java.io.Serializable;

public class Memory implements Serializable {
    @Serial
    private static final long serialVersionUID = 550081;
    private Chessboard chessboard;

    public Memory(Chessboard chessboard) {
        this.chessboard = chessboard;
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


    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
}
