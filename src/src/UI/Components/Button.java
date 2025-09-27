package src.UI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {

    private Color backgroundColor = new Color(0, 153, 76);   // green
    private Color hoverColor = new Color(0, 204, 102);       // light green
    private Color pressedColor = new Color(0, 102, 51);      // dark green
    private Color borderColor = new Color(0, 102, 0);        // green border

    public Button(String text) {
        super(text);
        initStyle();
        addHoverEffect();
    }

    private void initStyle() {
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setBackground(backgroundColor);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setBorder(BorderFactory.createLineBorder(borderColor, 2, true)); // round border
        setContentAreaFilled(false); // we’ll handle painting manually
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedColor);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // smooth graphics
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // fill background with rounded rectangle
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // draw text centered
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(getText());
        int stringHeight = fm.getAscent();
        int x = (getWidth() - stringWidth) / 2;
        int y = (getHeight() + stringHeight) / 2 - 2;

        g2.setColor(getForeground());
        g2.setFont(getFont());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }
}
