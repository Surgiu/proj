package view;

import model.PlayerColor;

import java.awt.*;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class ElephantComp extends ChessComp {
    public ElephantComp(PlayerColor owner, int size, String name) {
        super(owner, size);
        this.setName(name);
    }

}
