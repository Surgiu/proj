package listener;

import model.ChessboardPoint;
import view.CellComponent;
import view.ChessComp;

public interface GameListener {

    void onPlayerClickCell(ChessboardPoint point, CellComponent component);


    void onPlayerClickChessPiece(ChessboardPoint point, ChessComp component);

}
