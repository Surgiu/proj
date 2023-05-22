package view;

import model.PlayerColor;

import java.awt.*;

public class RatComp extends ChessComp {
    public RatComp(PlayerColor owner, int size,String name) {
        super(owner,size);
        this.setName(name);
    }

}
