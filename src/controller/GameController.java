package controller;

import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessComp;
import view.ChessboardComponent;

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

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;
        view.registerController(this);
        initialize();
        view.repaint();
    }

    private void initialize() {
        view.initiateChessComponent(model);
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = (currentPlayer == PlayerColor.BLUE) ? PlayerColor.RED : PlayerColor.BLUE;
    }

    private boolean win() {
        ChessPiece winnerPiece1 = model.getGrid()[0][3].getPiece();
        ChessPiece winnerPiece2 = model.getGrid()[8][3].getPiece();
        boolean case1 = winnerPiece1 != null && winnerPiece1.getOwner().equals(PlayerColor.BLUE);
        boolean case2 = winnerPiece2 != null && winnerPiece2.getOwner().equals(PlayerColor.RED);
        return case1 || case2;
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            model.moveChessPiece(selectedPoint, point);
            model.inTrap(point);
            model.escapeTrap(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            if (model.inDens(point)) {
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
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else {
            if (model.isValidCapture(selectedPoint, point)) {
                model.captureChessPiece(selectedPoint, point);
                view.removeChessComponentAtGrid(point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
            } else if (!model.isValidCapture(selectedPoint, point)) {
                System.err.println("Illegal capture");
            }
            swapColor();
            view.repaint();
        }
    }
}
