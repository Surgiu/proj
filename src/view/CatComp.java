package view;

import model.PlayerColor;

import java.awt.*;

public class CatComp extends ChessComp {

    public CatComp(PlayerColor owner, int size,String name) {
        super(owner,size);
        this.setName(name);
    }
}
