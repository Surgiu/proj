package controller;

import listener.GameListener;
import model.*;
import view.*;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

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

    public GameController(ChessboardComponent view, Chessboard model, Mode gameMode) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        view.registerController(this);
        viewInitialize();
        initMemory();
        autoSave();
        view.repaint();
        if (gameMode.equals(Mode.SINGLEPLAYER)) {
            AI = new AI(model);
        }
    }

    private void viewInitialize() {
        view.initiateChessComponent(model);
    }

    public void initMemory() {
        File file = new File("resource/gameInformation/");
        File[] files = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            files[i].delete();
        }
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = (currentPlayer == PlayerColor.BLUE) ? PlayerColor.RED : PlayerColor.BLUE;
    }

    private boolean isWin() {
        ChessPiece winnerPiece1 = model.getGrid()[0][3].getPiece();
        ChessPiece winnerPiece2 = model.getGrid()[8][3].getPiece();
        int redCount = -1, blueCount = -1;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessPiece piece = model.getChessPieceAt(new ChessboardPoint(i, j));
                if (piece != null && piece.getOwner().equals(PlayerColor.RED)) {
                    redCount++;
                } else if (piece != null && piece.getOwner().equals(PlayerColor.BLUE)) {
                    blueCount++;
                }
            }
        }
        boolean case1 = (winnerPiece1 != null && winnerPiece1.getOwner().equals(PlayerColor.BLUE)) || redCount == -1;
        boolean case2 = (winnerPiece2 != null && winnerPiece2.getOwner().equals(PlayerColor.RED)) || blueCount == -1;
        if (case1) {
            winner = PlayerColor.BLUE;
            view.getChessGameFrame().grading();
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
            autoSave();
            swapColor();
            AIGo();
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
                model.escapeTrap(selectedPoint, point);
                model.captureChessPiece(selectedPoint, point);
                view.removeChessComponentAtGrid(point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                autoSave();
                swapColor();
                if (isWin()) {
                    view.showWin(winner);
                    return;
                }
            } else if (!model.isValidCapture(selectedPoint, point)) {
                highlightOff(selectedPoint);
                ChessComp chess = (ChessComp) view.getGridComponentAt(selectedPoint).getComponents()[0];
                chess.setSelected(false);
                selectedPoint = null;
                System.err.println("Illegal capture");
            }
            AIGo();
            view.repaint();
        }
    }

    public void restart() {
//        view.clear();
        initMemory();
        model.initialize();
        view.initiateGridComponents();
        view.initiateChessComponent(model);
        System.out.println(111);
        view.repaint();
        currentPlayer = PlayerColor.BLUE;
        selectedPoint = null;
        winner = null;
//        test();
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
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public void highlightOn(ChessboardPoint point) {
        view.drawHighlight(model.highlight(point));
        view.repaint();
    }

    public void highlightOff(ChessboardPoint point) {
        view.drawHighlightOff(model.highlight(point));
        view.repaint();
    }

    public void saveGame() throws IOException {
        JFileChooser fileChooser = new JFileChooser("resource/SavedGames");
        int m = fileChooser.showSaveDialog(view.getChessGameFrame());
        File file = null;
        if (m == JFileChooser.APPROVE_OPTION) {
            try {
                file = fileChooser.getSelectedFile();
                String add = file.getCanonicalPath();
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(add));
                Memory gameInfo = new Memory(model);
                stream.writeObject(gameInfo);
                stream.close();
                JOptionPane.showMessageDialog(null, "存档成功！");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("存档失败");
            }
        }
    }

    public void deleteMemory() {
        File file = new File("resource/SavedGames/");
        File[] files = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            files[i].delete();
        }
        JOptionPane.showMessageDialog(null, "存档已清除");
    }

    public void loadMemory() {
        JFileChooser fileChooser = new JFileChooser("resource/SavedGames");
        int n = fileChooser.showOpenDialog(view.getChessGameFrame());
        File file = null;
        if (n == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            doLoad(file);
        }
    }

    private ChessComp getChessViewByPiece(ChessPiece chessPiece) {
        //当心：动态绑定机制
        PlayerColor color = chessPiece.getOwner();
        switch (chessPiece.getName()) {
            case "Elephant" -> {
                return new ElephantComp(color, view.getCHESS_SIZE(), "象");
            }
            case "Lion" -> {
                return new LionComp(color, view.getCHESS_SIZE(), "狮");
            }
            case "Tiger" -> {
                return new TigerComp(color, view.getCHESS_SIZE(), "虎");
            }
            case "Leopard" -> {
                return new LeopardComp(color, view.getCHESS_SIZE(), "豹");
            }
            case "Wolf" -> {
                return new WolfComp(color, view.getCHESS_SIZE(), "狼");
            }
            case "Dog" -> {
                return new DogComp(color, view.getCHESS_SIZE(), "狗");
            }
            case "Cat" -> {
                return new CatComp(color, view.getCHESS_SIZE(), "猫");
            }
            case "Rat" -> {
                return new RatComp(color, view.getCHESS_SIZE(), "鼠");
            }
        }
        return null;
    }

    public void AIGo() {
        if (currentPlayer.equals(PlayerColor.BLUE) || AI == null) {
            return;
        }
        ChessboardPoint[] step = AI.move();
        ChessboardPoint src = step[0];
        ChessboardPoint dest = step[1];
        AI.doMove(step);
        if (model.getChessPieceAt(dest) != null) {
            view.getGridComponentAt(dest).removeAll();
        }
        view.setChessComponentAtGrid(dest, view.removeChessComponentAtGrid(src));
        autoSave();
        swapColor();
    }

    public void undo() {
        int currentTurn = model.getNum();
        File file = new File("resource/gameInformation/");
        File[] files = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            if (files[i].getName().equals(String.valueOf(currentTurn - 1))) {
                doLoad(files[i]);
            }
        }
        this.model.setNum(currentTurn - 1);
    }

    private void doLoad(File file) {
        Memory gameInfo = null;
        model.initialize();
        view.removeAll();
        view.initiateGridComponents();
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
            gameInfo = (Memory) stream.readObject();
            stream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert gameInfo != null;
        this.model = gameInfo.getChessboard();
        view.initiateChessComponent(this.model);
    }

    public void autoSave() {
        int turn = model.getNum();
        File file = new File("resource/gameInformation/" + turn);
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            Memory status = new Memory(this.model);
            stream.writeObject(status);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
