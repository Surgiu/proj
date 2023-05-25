package model;

import java.io.Serializable;

/**
 * This class describe the slot for Chess in Chessboard
 */
public class Cell implements Serializable {
    private ChessPiece piece;
    private final int type;//0:ground,1:river,2:trap,3:dens
    private int occupy;//0:noOccupation;1:red;2:blue
    private int terrain = 0;//0:none;10:red;20:blue

    public Cell(int type) {
        this.type = type;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public int getType() {
        return type;
    }

    public int getOccupy() {
        return occupy;
    }

    public void setOccupy(int occupy) {
        this.occupy = occupy;
    }

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
    }

}
