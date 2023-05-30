package view;

import model.PlayerColor;

import java.awt.*;

public class LionComp extends ChessComp {

    public LionComp(PlayerColor owner, int size, String name) {
        super(owner, size);
        this.setName(name);
    }
}
