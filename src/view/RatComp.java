package view;

import model.PlayerColor;

public class RatComp extends ChessComp {
    public RatComp(PlayerColor owner, int size, String name) {
        super(owner, size);
        this.setName(name);
    }

}
