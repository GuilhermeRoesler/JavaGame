import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MinecraftButton extends JButton {
    private boolean hovered;
    private boolean pressed;

    public MinecraftButton(String text) {
        super(text);
        setPreferredSize(new Dimension(280, 40));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFont(new Font("SansSerif", Font.BOLD, 16));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                pressed = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHints(new RenderingHints(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        ));

        int w = getWidth();
        int h = getHeight();

        Color fill = hovered
            ? new Color(120, 120, 120)
            : new Color(100, 100, 100);
        if (!isEnabled()) {
            fill = new Color(70, 70, 70);
        }
        if (pressed) {
            fill = new Color(80, 80, 80);
        }

        g2.setColor(fill);
        g2.fillRect(0, 0, w, h);

        // Borda em relevo (estilo Minecraft)
        g2.setColor(new Color(255, 255, 255, 90));
        g2.fillRect(0, 0, w, 2);
        g2.fillRect(0, 0, 2, h);
        g2.setColor(new Color(0, 0, 0, 120));
        g2.fillRect(0, h - 2, w, 2);
        g2.fillRect(w - 2, 0, 2, h);

        String text = getText();
        FontMetrics fm = g2.getFontMetrics();
        int textX = (w - fm.stringWidth(text)) / 2;
        int textY = (h - fm.getHeight()) / 2 + fm.getAscent();

        g2.setColor(new Color(0, 0, 0, 160));
        g2.drawString(text, textX + 2, textY + 2);
        g2.setColor(isEnabled() ? Color.WHITE : new Color(160, 160, 160));
        g2.drawString(text, textX, textY);

        g2.dispose();
    }
}
