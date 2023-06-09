package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    private final CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    private final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell = new HashSet<>();
    private final Set<ChessboardPoint> densCell = new HashSet<>();
    private final Set<ChessboardPoint> trapCell = new HashSet<>();

    private final ChessGameFrame chessGameFrame;
    private GameController gameController;

    public ChessboardComponent(ChessGameFrame chessGameFrame, int chessSize) {
        this.chessGameFrame = chessGameFrame;
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 7;
        int height = CHESS_SIZE * 9;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);
        initiateGridComponents();
    }

    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (grid[i][j].getPiece() != null) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    switch (chessPiece.getName()) {
                        case "Elephant" ->
                                gridComponents[i][j].add(new ElephantComp(chessPiece.getOwner(), CHESS_SIZE, "象"));
                        case "Lion" -> gridComponents[i][j].add(new LionComp(chessPiece.getOwner(), CHESS_SIZE, "狮"));
                        case "Tiger" ->
                                gridComponents[i][j].add(new TigerComp(chessPiece.getOwner(), CHESS_SIZE, "虎"));
                        case "Leopard" ->
                                gridComponents[i][j].add(new LeopardComp(chessPiece.getOwner(), CHESS_SIZE, "豹"));
                        case "Wolf" -> gridComponents[i][j].add(new WolfComp(chessPiece.getOwner(), CHESS_SIZE, "狼"));
                        case "Dog" -> gridComponents[i][j].add(new DogComp(chessPiece.getOwner(), CHESS_SIZE, "狗"));
                        case "Cat" -> gridComponents[i][j].add(new CatComp(chessPiece.getOwner(), CHESS_SIZE, "猫"));
                        case "Rat" -> gridComponents[i][j].add(new RatComp(chessPiece.getOwner(), CHESS_SIZE, "鼠"));
                    }
                }
            }
        }
        this.repaint();
    }

    public void initiateGridComponents() {
        this.removeAll();
        riverCell.add(new ChessboardPoint(3, 1));
        riverCell.add(new ChessboardPoint(3, 2));
        riverCell.add(new ChessboardPoint(4, 1));
        riverCell.add(new ChessboardPoint(4, 2));
        riverCell.add(new ChessboardPoint(5, 1));
        riverCell.add(new ChessboardPoint(5, 2));

        riverCell.add(new ChessboardPoint(3, 4));
        riverCell.add(new ChessboardPoint(3, 5));
        riverCell.add(new ChessboardPoint(4, 4));
        riverCell.add(new ChessboardPoint(4, 5));
        riverCell.add(new ChessboardPoint(5, 4));
        riverCell.add(new ChessboardPoint(5, 5));

        trapCell.add(new ChessboardPoint(0, 2));
        trapCell.add(new ChessboardPoint(0, 4));
        trapCell.add(new ChessboardPoint(1, 3));
        trapCell.add(new ChessboardPoint(8, 2));
        trapCell.add(new ChessboardPoint(8, 4));
        trapCell.add(new ChessboardPoint(7, 3));

        densCell.add(new ChessboardPoint(0, 3));
        densCell.add(new ChessboardPoint(8, 3));

        for (int i = 3; i < 6; i++) {//river area
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else {
                    cell = new CellComponent(Color.LIGHT_GRAY, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                }
                gridComponents[i][j] = cell;
            }
        }

        for (int i = 0; i < 3; i++) {//red terrain
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (trapCell.contains(temp)) {
                    cell = new CellComponent(Color.magenta, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else if (densCell.contains(temp)) {
                    cell = new CellComponent(Color.blue, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else {
                    cell = new CellComponent(Color.PINK, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                }
                gridComponents[i][j] = cell;
            }
        }

        for (int i = 6; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (trapCell.contains(temp)) {
                    cell = new CellComponent(Color.magenta, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else if (densCell.contains(temp)) {
                    cell = new CellComponent(Color.blue, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else {
                    cell = new CellComponent(Color.white, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                }
                gridComponents[i][j] = cell;
            }
        }
    }


    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChessComponentAtGrid(ChessboardPoint point, ChessComp chess) {
        getGridComponentAt(point).add(chess);
    }

    public ChessComp removeChessComponentAtGrid(ChessboardPoint point) {
        // Note re-validation is required after remove / removeAll.
        ChessComp chess = (ChessComp) getGridComponentAt(point).getComponents()[0];
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    public CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y / CHESS_SIZE + ", " + point.x / CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y / CHESS_SIZE, point.x / CHESS_SIZE);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ChessComp) clickedComponent.getComponents()[0]);
            }
        } else if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            JComponent enteredComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (enteredComponent != null) {
                gameController.onPlayerHoverPoint(getChessboardPoint(e.getPoint()));
            }
        } else if (e.getID() == MouseEvent.MOUSE_EXITED) {
            JComponent exitedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (exitedComponent != null) {
                gameController.onPlayerHoverPoint(null);
            }
        }
    }

    public void drawHighlight(ArrayList<ChessboardPoint> points) {
        for (ChessboardPoint point : points) {
            gridComponents[point.getRow()][point.getCol()].setOpaque(true);
        }
    }

    public void drawHighlightOff(ArrayList<ChessboardPoint> points) {
        for (ChessboardPoint point : points) {
            gridComponents[point.getRow()][point.getCol()].setOpaque(false);
        }
    }

    public void hoverHighlight(ChessboardPoint point) {
        if (point != null) {
            gridComponents[point.getRow()][point.getCol()].setOpaque(true);
        } else {
            for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
                for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                    gridComponents[i][j].setOpaque(false);
                }
            }
        }
    }

    public GameController getGameController() {
        return gameController;
    }

    public CellComponent[][] getGridComponents() {
        System.out.println();
        return gridComponents;
    }

    public int getCHESS_SIZE() {
        return CHESS_SIZE;
    }

    public ChessGameFrame getChessGameFrame() {
        return chessGameFrame;
    }
}
