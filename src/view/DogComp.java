package view;

import model.PlayerColor;

public class DogComp extends ChessComp {
    public DogComp(PlayerColor owner, int size,String name) {
        super(owner,size);
        this.setName(name);
    }
}
