package controller;

import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessComp;
import view.ChessboardComponent;

import javax.swing.*;
import java.io.*;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 */
public class GameController implements GameListener {

    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private PlayerColor winner;
    private AI AI;
    private String address = "C:\\Users\\DELL\\IdeaProjects\\pro\\resource\\gameInfo";

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        view.registerController(this);
        viewInitialize();
        view.repaint();
    }

    private void viewInitialize() {
        view.initiateChessComponent(model);
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = (currentPlayer == PlayerColor.BLUE) ? PlayerColor.RED : PlayerColor.BLUE;
    }

    private boolean isWin() {
        ChessPiece winnerPiece1 = model.getGrid()[0][3].getPiece();
        ChessPiece winnerPiece2 = model.getGrid()[8][3].getPiece();
        boolean case1 = winnerPiece1 != null && winnerPiece1.getOwner().equals(PlayerColor.BLUE);
        boolean case2 = winnerPiece2 != null && winnerPiece2.getOwner().equals(PlayerColor.RED);
        if (case1) {
            winner = PlayerColor.BLUE;
        } else if (case2) {
            winner = PlayerColor.RED;
        }
        return case1 || case2;
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null) {
            model.escapeTrap(selectedPoint, point);
            highlightOff(selectedPoint);
            model.moveChessPiece(selectedPoint, point);
            model.inTrap(point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            if (isWin()) {
                view.showWin(winner);
                return;
            }
            selectedPoint = null;
            swapColor();
            view.repaint();
        }
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComp component) {
        if (selectedPoint == null) {//如果还没被选中，那么就让它被选中
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                highlightOn(selectedPoint);
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {//如果放到自己的位置，就放弃选中
            selectedPoint = null;
            component.setSelected(false);
            highlightOff(point);
            component.repaint();
        } else {
            if (model.isValidCapture(selectedPoint, point)) {//如果是有效动作，进行相应操作
                highlightOff(selectedPoint);
                model.captureChessPiece(selectedPoint, point);
                view.removeChessComponentAtGrid(point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                swapColor();
            } else if (!model.isValidCapture(selectedPoint, point)) {
                selectedPoint = null;
                System.err.println("Illegal capture");
            }
            view.repaint();
        }
    }

    public void restart() {
        view.clear();
        model.initialize();
        view.initiateGridComponents();
        view.initiateChessComponent(model);
        view.repaint();
        currentPlayer = PlayerColor.BLUE;
        selectedPoint = null;
        winner = null;
        test();
    }

    private void test() {
        int[][] test = new int[9][7];
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[i].length; j++) {
                if (view.getGridComponents()[i][j].getComponents() != null) {
                    test[i][j] = 1;
                }
            }
        }
        for (int[] ints : test) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
    }
    public void highlightOn(ChessboardPoint point) {
        view.drawHighlight(model.highlight(point));
        view.repaint();
    }
    public void highlightOff (ChessboardPoint point) {
        view.drawHighlightOff(model.highlight(point));
        view.repaint();
    }
    public void saveGame() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(address));
            Memory gameInfo = new Memory(model);
            stream.writeObject(gameInfo);
            stream.close();
            JOptionPane.showMessageDialog( null,"存档成功！");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("存档失败");
        }
    }
    public void deleteMemory() {
        File file = new File(address);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"存档已清除");
    }
    public void loadMemory() {
        Memory gameInfo = null;
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(address));
            gameInfo = (Memory) stream.readObject();
            stream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert gameInfo != null;
        Chessboard chessboard = gameInfo.getChessboard();

    }
}
