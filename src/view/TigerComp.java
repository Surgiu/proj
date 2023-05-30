package view;

import model.PlayerColor;

import java.awt.*;

public class TigerComp extends ChessComp {
    public TigerComp(PlayerColor owner, int size, String name) {
        super(owner, size);
        this.setName(name);
    }

}
