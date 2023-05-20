package view;

import model.PlayerColor;

import java.awt.*;

public class DogComp extends ChessComp {
    public DogComp(PlayerColor owner, int size) {
        super(owner,size);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("隶书", Font.BOLD, getWidth() / 2);
        g2.setFont(font);
        g2.setColor(getOwner().getColor());
        g2.drawString("狗", getWidth() / 4, getHeight() * 5 / 8); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}