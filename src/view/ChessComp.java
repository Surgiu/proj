package view;
import model.PlayerColor;
import javax.swing.*;
import java.awt.*;

public class ChessComp extends JComponent {
    private final PlayerColor owner;
    protected String name;

    private boolean selected;

    public ChessComp(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        setSize(size, size);
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
        g2.setColor(Color.ORANGE);
        g2.fillOval(0, 0, getWidth(), getHeight());
        Font font = new Font("隶书", Font.PLAIN, getWidth() / 2);
        g2.setFont(font);
        g2.setColor(getOwner().getColor());
        g2.drawString(name, getWidth() / 4, getHeight() * 5 / 8);
        BasicStroke b2 = new BasicStroke(3);
        g2.setStroke(b2);
        g2.drawOval(0, 0, getWidth(), getHeight());
        if (isSelected()) { // Highlights the model if selected.
            BasicStroke circle = new BasicStroke(4);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(circle);
            graphics2D.setColor(Color.MAGENTA);
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
