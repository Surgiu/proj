package view;

import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class ChessComp extends JComponent {
    private PlayerColor owner;
    protected String name;

    private boolean selected;

    public ChessComp(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        setSize(size / 2, size / 2);
        setLocation(0, 0);
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
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("隶书", Font.PLAIN, getWidth() / 2);
        g2.setFont(font);
        g2.setColor(getOwner().getColor());
        g2.drawString(name, getWidth() / 4, getHeight() * 5 / 8);
        // FIXME: Use library to find the correct offset.
        g2.drawOval(0, 0, getWidth(), getHeight());
        if (isSelected()) { // Highlights the model if selected.
            BasicStroke circle = new BasicStroke(120.0f);
            Graphics2D graphics2D= (Graphics2D)g;
            graphics2D.setStroke(circle);
            graphics2D.setColor(Color.cyan);
            graphics2D.drawOval(0, 0, getWidth(), getHeight());
        }
    }

    public PlayerColor getOwner() {
        return owner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
