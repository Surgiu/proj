import controller.GameController;
import controller.Mode;
import model.Chessboard;
import view.ChessGameFrame;
import view.EnterFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnterFrame enter = new EnterFrame();
//            mainFrame.setVisible(true);
            enter.setVisible(true);
            ChessGameFrame mainFrame = new ChessGameFrame(1100,810);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), Mode.MULTIPLAYER);

        });
    }
}
