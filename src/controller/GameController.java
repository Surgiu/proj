package controller;

import listener.GameListener;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 */
public class GameController implements GameListener, Serializable {

    @Serial
    private static final long serialVersionUID = -4947732304289783372L;
    private Chessboard model;
    private final ChessboardComponent view;
    private PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private AI AI;
    private int runTime = 60;
    private static Timer timer;
    private static Timer timer0;
    private String currentStatus = "";
    private int steps = 0;

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
        File file = new File("resource/gameInformation");
        File[] files = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            files[i].delete();
        }
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = (currentPlayer == PlayerColor.BLUE) ? PlayerColor.RED : PlayerColor.BLUE;
        timerStart();
        view.getChessGameFrame().statusUpgrading(currentStatus);
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
        return case1 || case2;
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null) {
            model.escapeTrap(selectedPoint, point);
            highlightOff(selectedPoint);
            model.moveChessPiece(selectedPoint, point);
            if (model.getGridAt(point).getType() == 1) {
                musicInWater();
            } else {
                musicPlay();
            }
            model.inTrap(point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            view.repaint();
            timerEnd();
            if (isWin()) {
                if (currentPlayer == PlayerColor.BLUE) {
                    new WinFrame(view);
                } else {
                    new LoseFrame(view);
                }
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
                musicSelect();
                highlightOn(selectedPoint);
                component.setSelected(true);
                if (model.getNum() == 0) {
                    timerStart();
                }
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {//如果放到自己的位置，就放弃选中
            selectedPoint = null;
            component.setSelected(false);
            musicPlay();
            highlightOff(point);
            component.repaint();
        } else {
            if (model.isValidCapture(selectedPoint, point)) {//如果是有效动作，进行相应操作
                highlightOff(selectedPoint);
                model.escapeTrap(selectedPoint, point);
                model.captureChessPiece(selectedPoint, point);
                musicPlay();
                view.removeChessComponentAtGrid(point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                view.repaint();
                selectedPoint = null;
                timerEnd();
                autoSave();
                if (isWin()) {
                    if (currentPlayer == PlayerColor.BLUE) {
                        new WinFrame(view);
                    } else {
                        new LoseFrame(view);
                    }
                    return;
                }
                swapColor();
            } else if (!model.isValidCapture(selectedPoint, point)) {//否则取消行动
                highlightOff(selectedPoint);
                ChessComp chess = (ChessComp) view.getGridComponentAt(selectedPoint).getComponents()[0];
                chess.setSelected(false);
                selectedPoint = null;
                System.out.println("Illegal capture");
            }
            AIGo();
            view.repaint();
        }
    }

    @Override
    public void onPlayerHoverPoint(ChessboardPoint point) {

    }

    public void restart() {
        initMemory();
        model.initialize();
        view.initiateGridComponents();
        view.initiateChessComponent(model);
        currentPlayer = PlayerColor.BLUE;
        selectedPoint = null;
        autoSave();
        timerEnd();
        setCurrentStatus(status());
        view.getChessGameFrame().statusUpgrading(currentStatus);
        view.repaint();
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
                ChessGameFrame frame = view.getChessGameFrame();
                boolean isChanged = frame.getPicture2().isVisible();
                boolean themeChanged = frame.getContentPane().getBackground() == Color.BLACK;
                Memory gameInfo = new Memory(model, this.runTime, selectedPoint, isChanged, themeChanged);
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
        int count = 0;
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            boolean is = files[i].delete();
            if (!is) {
                count++;
            }
        }
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "存档已清除");
        } else {
            JOptionPane.showMessageDialog(null, "清除失败，请重试");
        }
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
                break;
            }
        }
        if (currentTurn != 0) {
            this.model.setNum(currentTurn - 1);
            swapColor();
        }
        timerEnd();
        timerStart();
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
        selectedPoint = gameInfo.getChessboardPoint();
        if (selectedPoint != null) {
            highlightOn(selectedPoint);
        }
        view.initiateChessComponent(this.model);
        if (gameInfo.isChange()) {
            view.getChessGameFrame().addChangePicture();
        }
        if (gameInfo.isTheme()) {
            view.getChessGameFrame().addChangeBackground();
        }
        this.runTime = gameInfo.getCurrentTime();
        timerStart();
        view.repaint();
    }

    public void autoSave() {
        int turn = model.getNum();
        File file = new File("resource/gameInformation/" + turn);
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            Memory status = new Memory(this.model, 30, selectedPoint, false, false);
            stream.writeObject(status);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public Chessboard getModel() {
        return model;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void timerStart() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    GameController.this.setRunTime(GameController.this.getRunTime() - 1);
                    setCurrentStatus(status());
                    view.getChessGameFrame().statusUpgrading(currentStatus);
                    if (GameController.this.getRunTime() == 0) {
                        if (GameController.this.currentPlayer == PlayerColor.RED) {
                            new TimeFrame(view, "红方");
                        } else {
                            new TimeFrame(view, "蓝方");
                        }
                        timerEnd();
                    }
                }
            }, 1, 1000);
        }
    }

    public void timerEnd() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            setRunTime(60);
        }
    }

    public void playBack() {
        timer0 = new Timer();
        timerEnd();
        timer0.schedule(new TimerTask() {
            final File file = new File("resource/gameInformation/");
            final File[] files = file.listFiles();

            @Override
            public void run() {
                assert files != null;

                doLoad(files[steps]);
                timerEnd();
                steps++;
                if (steps == files.length) {
                    steps = 0;
                    timer0.cancel();
                }

            }
        }, 1000, 1573);
    }

    public String status() {
        String player = currentPlayer == PlayerColor.BLUE ? "蓝方" : "红方";
        return "<html>当前玩家:   " + player + "<br/>    回合数: 0" + model.getNum() / 2 + "<br/>       剩余时间: " + runTime + "s<br/></html>";
    }

    public void musicPlay() {
        MusicTool.MusicTool2();
    }

    public void musicSelect() {
        MusicTool.MusicTool3();
    }

    public void musicInWater() {
        MusicTool.MusicTool4();
    }
}
