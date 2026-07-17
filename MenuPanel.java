import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel {
    private BufferedImage background;

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public MenuPanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setLayout(new BorderLayout());
        loadBackground();

        JLabel title = new JLabel("CAR GAME", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(48, 0, 0, 0));
        add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);

        JButton playButton = new JButton("Jogar");
        JButton multiplayerButton = new JButton("Multiplayer");
        JButton exitButton = new JButton("Sair");

        playButton.addActionListener(null);
        multiplayerButton.addActionListener(null);
        exitButton.addActionListener(null);

        gbc.gridy = 0;
        buttons.add(playButton, gbc);
        gbc.gridy = 1;
        buttons.add(multiplayerButton, gbc);
        gbc.gridy = 2;
        buttons.add(Box.createVerticalStrut(16), gbc);
        gbc.gridy = 3;
        buttons.add(exitButton, gbc);

        add(buttons, BorderLayout.CENTER);
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(new File(Constants.IMG_PATH + "menu_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (background != null) {
            g2.drawImage(background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
        } else {
            g2.setColor(new Color(40, 40, 40));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
    }
}
