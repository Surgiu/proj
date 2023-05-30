package view;

import model.PlayerColor;

import java.awt.*;

public class LeopardComp extends ChessComp {
    public LeopardComp(PlayerColor owner, int size, String name) {
        super(owner, size);
        this.setName(name);
    }

}
