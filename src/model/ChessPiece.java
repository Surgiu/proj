package model;


import java.io.Serial;
import java.io.Serializable;

public class ChessPiece implements Serializable {
    @Serial
    private static final long serialVersionUID = 5152478909569497901L;
    private final PlayerColor owner;
    private String name;
    private int rank;

    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
    }

    public boolean canCapture(ChessPiece target) {
        if (!this.getOwner().equals(target.getOwner())) {
            if (this.name.equals("Rat") && target.getName().equals("Elephant")) {
                return true;
            } else if (this.name.equals("Elephant") && target.getName().equals("Rat")) {
                return false;
            } else {
                return this.rank >= target.getRank();
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
