package view;

import model.PlayerColor;

import java.awt.*;

public class WolfComp extends ChessComp {
    public WolfComp(PlayerColor owner, int size, String name) {
        super(owner, size);
        this.setName(name);
    }

}
