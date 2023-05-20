package view;

import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class ChessComp extends JComponent {
    private PlayerColor owner;

    private boolean selected;

    public ChessComp(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    protected void paintComponent(Graphics g) {

    }

}
