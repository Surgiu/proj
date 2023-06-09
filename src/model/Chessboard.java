package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard implements Serializable {
    @Serial
    private static final long serialVersionUID = 88010802L;
    private Cell[][] grid;
    private int num;
    private PlayerColor currentPlayer;


    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19
        initialize();
    }

    public void initialize() {
        initGrid();
        clear();
        initPieces();
        num = 0;
    }

    public void initGrid() {//默认上红下蓝
        grid = new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];
        //dens
        grid[0][3] = new Cell(3);
        grid[8][3] = new Cell(3);
        //trap
        grid[0][2] = new Cell(2);
        grid[0][4] = new Cell(2);
        grid[1][3] = new Cell(2);
        grid[7][3] = new Cell(2);
        grid[8][2] = new Cell(2);
        grid[8][4] = new Cell(2);
        //river
        for (int i = 3; i <= 5; i++) {
            for (int j = 1; j <= 2; j++) {
                grid[i][j] = new Cell(1);
                grid[i][j + 3] = new Cell(1);
            }
        }
        //ground
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = new Cell(0);
                }
            }
        }
        //terrain
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i < 3) {
                    grid[i][j].setTerrain(10);
                } else if (i > 5) {
                    grid[i][j].setTerrain(20);
                }
            }
        }
    }

    public void initPieces() {
        grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "Lion", 7));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "Tiger", 6));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "Dog", 3));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "Cat", 2));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "Wolf", 4));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "Rat", 1));
        grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "Elephant", 8));
        grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant", 8));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf", 4));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat", 1));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat", 2));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog", 3));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger", 6));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion", 7));
    }

    public void clear() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.getPiece() != null) {
                    cell.setPiece(null);
                }
            }
        }
    }

    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    public Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    public int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    public void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);//把指定的棋子放到指定坐标
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        ChessPiece piece0 = getChessPieceAt(src);
        if (piece0 != null) {
            updateOccupy(src, dest, piece0);
        }
    }

    private void updateOccupy(ChessboardPoint src, ChessboardPoint dest, ChessPiece piece0) {
        if (piece0.getOwner().equals(PlayerColor.BLUE)) {
            getGridAt(dest).setOccupy(2);
        } else {
            getGridAt(dest).setOccupy(1);
        }
        getGridAt(src).setOccupy(0);
        setChessPiece(dest, removeChessPiece(src));
        num++;
        updateCurrentPlayer();
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        } else {
            ChessPiece predator = getChessPieceAt(src);
            updateOccupy(src, dest, predator);
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece piece = getChessPieceAt(src);
        if (piece == null || getChessPieceAt(dest) != null) {
            return false;
        }
        if (piece.getName().equals("Tiger") || piece.getName().equals("Lion")) {
            if (calculateDistance(src, dest) > 1) {//cross river
                if (src.getCol() == dest.getCol() || src.getRow() == dest.getRow()) {
                    if (pureRiver(src, dest)) {
                        return getGridAt(dest).getType() == 0 && dens(src, dest);
                    }
                }
            } else if (calculateDistance(src, dest) == 1) {//other cases
                return getGridAt(dest).getType() != 1 && dens(src, dest);
            }
        }
        if (piece.getName().equals("Rat")) {
            return calculateDistance(src, dest) == 1 && dens(src, dest);
        }
        if (getGridAt(dest).getType() != 1) {
            return calculateDistance(src, dest) == 1 && dens(src, dest);
        }
        return false;
    }

    private boolean dens(ChessboardPoint src, ChessboardPoint dest) {//不能走到自己方的兽穴里
        if (getGridAt(dest).getType() == 3) {
            return (!getChessPieceAt(src).getOwner().equals(PlayerColor.BLUE)
                    || getGridAt(dest).getTerrain() != 20)
                    && (!getChessPieceAt(src).getOwner().equals(PlayerColor.RED)
                    || getGridAt(dest).getTerrain() != 10);
        }
        return true;
    }

    private boolean pureRiver(ChessboardPoint start, ChessboardPoint end) {
        if (start.getRow() == end.getRow()) {//横跳
            int left = Math.min(start.getCol(), end.getCol()) + 1;
            int right = Math.max(start.getCol(), end.getCol()) - 1;
            int count = -1;
            for (int i = left; i <= right; i++) {
                if (grid[start.getRow()][i].getType() != 1) {
                    count++;
                }
                if (count == -1 && noRowBarrier(start.getRow(), left, right)) {
                    return true;
                }
            }
        } else if (start.getCol() == end.getCol()) {//纵跳
            int upper = Math.min(start.getRow(), end.getRow()) + 1;
            int lower = Math.max(start.getRow(), end.getRow()) - 1;
            int count = -1;
            for (int i = upper; i <= lower; i++) {
                if (grid[i][start.getCol()].getType() != 1) {
                    count++;
                }
                if (count == -1 && noColBarrier(start.getCol(), upper, lower)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean noRowBarrier(int row, int s, int e) {
        int count = -1;
        for (int i = s; i <= e; i++) {
            if (getChessPieceAt(new ChessboardPoint(row, i)) != null) {
                count++;
            }
        }
        return count == -1;
    }

    private boolean noColBarrier(int col, int s, int e) {
        int count = -1;
        for (int i = s; i <= e; i++) {
            if (getChessPieceAt(new ChessboardPoint(i, col)) != null) {
                count++;
            }
        }
        return count == -1;
    }


    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        ChessPiece predator = getChessPieceAt(src);
        ChessPiece target = getChessPieceAt(dest);
        if (predator != null && dest != null
                && getGridAt(src).getOccupy() != getGridAt(dest).getOccupy()) {
            switch (predator.getName()) {
                case "Lion", "Tiger" -> {
                    if (calculateDistance(src, dest) > 1) {//lion or tiger near the river
                        if (src.getCol() == dest.getCol() || src.getRow() == dest.getRow()) {
                            if (pureRiver(src, dest) && getGridAt(dest).getType() == 0) {
                                return predator.canCapture(target);
                            }
                        }
                    } else {//lion or tiger on the ground
                        return predator.canCapture(target) && getGridAt(dest).getType() != 1;
                    }
                }
                case "Rat" -> {
                    if (getGridAt(src).getType() == 1) {//rat in the river
                        return getGridAt(dest).getType() == 1 && getChessPieceAt(dest) != null && calculateDistance(dest, src) == 1;
                    } else if (getGridAt(src).getType() != 1 && getGridAt(dest).getType() == 2) {
                        return calculateDistance(src, dest) == 1;
                    } else if (getGridAt(src).getType() != 1) {//rat on the ground
                        return predator.canCapture(target) && calculateDistance(src, dest) == 1;
                    }
                }
                default -> {
                    return predator.canCapture(target)
                            && calculateDistance(src, dest) == 1
                            && getGridAt(dest).getType() != 1;
                }
            }
        }
        return false;
    }

    public void inTrap(ChessboardPoint chessboardPoint) {
        if (getChessPieceAt(chessboardPoint) != null) {
            if ((getChessPieceAt(chessboardPoint).getOwner().equals(PlayerColor.BLUE) && getGridAt(chessboardPoint).getTerrain() == 10)
                    || (getChessPieceAt(chessboardPoint).getOwner().equals(PlayerColor.RED) && getGridAt(chessboardPoint).getTerrain() == 20)) {
                if (getGridAt(chessboardPoint).getType() == 2) {
                    getChessPieceAt(chessboardPoint).setRank(0);
                }
            }
        }
    }

    public void escapeTrap(ChessboardPoint s, ChessboardPoint e) {
        ChessPiece piece = getGridAt(s).getPiece();
        if (getGridAt(s).getType() == 2 && getGridAt(e).getType() != 2) {
            int rank = switch (piece.getName()) {
                case "Elephant" -> 8;
                case "Lion" -> 7;
                case "Tiger" -> 6;
                case "Leopard" -> 5;
                case "Wolf" -> 4;
                case "Dog" -> 3;
                case "Cat" -> 2;
                case "Rat" -> 1;
                default -> 0;
            };
            piece.setRank(rank);
        }
    }

    public ArrayList<ChessboardPoint> highlight(ChessboardPoint here) {
        if (getChessPieceAt(here) == null) {
            return null;
        } else if (getChessPieceAt(here).getName().equals("Lion") || getChessPieceAt(here).getName().equals("Tiger")) {
            ArrayList<ChessboardPoint> chessboardPoints = new ArrayList<>();
            for (int i = here.getRow() - 4; i <= here.getRow() + 4; i++) {
                for (int j = here.getCol() - 4; j <= here.getCol() + 4; j++) {
                    doHighlight(here, chessboardPoints, i, j);
                }
            }
            return chessboardPoints;
        } else {
            ArrayList<ChessboardPoint> chessboardPoints = new ArrayList<>();
            for (int i = here.getRow() - 1; i <= here.getRow() + 1; i++) {
                for (int j = here.getCol() - 1; j <= here.getCol() + 1; j++) {
                    doHighlight(here, chessboardPoints, i, j);
                }
            }
            return chessboardPoints;
        }
    }

    private void doHighlight(ChessboardPoint here, ArrayList<ChessboardPoint> chessboardPoints, int i, int j) {
        if (i >= 0 && i < Constant.CHESSBOARD_ROW_SIZE.getNum()
                && j >= 0 && j < Constant.CHESSBOARD_COL_SIZE.getNum()) {
            if (getChessPieceAt(new ChessboardPoint(i, j)) == null) {
                if (isValidMove(here, new ChessboardPoint(i, j))) {
                    chessboardPoints.add(new ChessboardPoint(i, j));
                }
            } else {
                if (isValidCapture(here, new ChessboardPoint(i, j))) {
                    chessboardPoints.add(new ChessboardPoint(i, j));
                }
            }
        }
    }

    void updateCurrentPlayer() {
        if (this.num % 2 == 0) {
            currentPlayer = PlayerColor.BLUE;
        } else {
            currentPlayer = PlayerColor.RED;
        }
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
