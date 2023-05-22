package view;

import model.PlayerColor;

import java.awt.*;

public class DogComp extends ChessComp {
    public DogComp(PlayerColor owner, int size,String name) {
        super(owner,size);
        this.setName(name);
    }

}
