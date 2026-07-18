import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class SkinSelectPanel extends JPanel {
    private BufferedImage background;
    private String selectedSkinPath;
    private final Consumer<String> onConfirm;
    private final Runnable onBack;
    private final JPanel skinsGrid;
    private SkinCard selectedCard;

    public SkinSelectPanel(Consumer<String> onConfirm, Runnable onBack) {
        this.onConfirm = onConfirm;
        this.onBack = onBack;

        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setLayout(new BorderLayout(0, 16));
        setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));
        loadBackground();

        JLabel title = new JLabel("Escolha sua skin", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        skinsGrid = new JPanel(new GridLayout(0, 3, 16, 16));
        skinsGrid.setOpaque(false);

        JScrollPane scroll = new JScrollPane(skinsGrid);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        footer.setOpaque(false);

        MinecraftButton backButton = new MinecraftButton("Voltar");
        backButton.setPreferredSize(new Dimension(160, 40));
        backButton.addActionListener(e -> this.onBack.run());

        MinecraftButton confirmButton = new MinecraftButton("Confirmar");
        confirmButton.setPreferredSize(new Dimension(160, 40));
        confirmButton.addActionListener(e -> {
            if (selectedSkinPath != null) {
                this.onConfirm.accept(selectedSkinPath);
            }
        });

        footer.add(backButton);
        footer.add(confirmButton);
        add(footer, BorderLayout.SOUTH);

        loadSkins();
    }

    public void refreshSkins() {
        skinsGrid.removeAll();
        selectedCard = null;
        selectedSkinPath = null;
        loadSkins();
        revalidate();
        repaint();
    }

    private void loadSkins() {
        File imgDir = new File(Constants.IMG_PATH);
        File[] files = imgDir.listFiles((dir, name) ->
            name.matches("(?i)car[0-9]*\\.png")
        );

        if (files == null || files.length == 0) {
            JLabel empty = new JLabel("Nenhuma skin encontrada (car*.png)", SwingConstants.CENTER);
            empty.setForeground(Color.WHITE);
            skinsGrid.add(empty);
            return;
        }

        Arrays.sort(files, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));

        for (File file : files) {
            SkinCard card = new SkinCard(file);
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectCard(card);
                }
            });
            skinsGrid.add(card);

            if (selectedSkinPath == null) {
                selectCard(card);
            }
        }
    }

    private void selectCard(SkinCard card) {
        if (selectedCard != null) {
            selectedCard.setSelected(false);
        }
        selectedCard = card;
        selectedCard.setSelected(true);
        selectedSkinPath = card.file.getAbsolutePath();
        repaint();
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

        g2.setColor(new Color(0, 0, 0, 140));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }

    private static class SkinCard extends JPanel {
        final File file;
        private final BufferedImage preview;
        private boolean selected;

        SkinCard(File file) {
            this.file = file;
            BufferedImage loaded = null;
            try {
                loaded = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.preview = loaded;

            setPreferredSize(new Dimension(140, 140));
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setToolTipText(file.getName());
        }

        void setSelected(boolean selected) {
            this.selected = selected;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

            Color fill = selected ? new Color(140, 140, 140) : new Color(100, 100, 100);
            g2.setColor(fill);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(new Color(255, 255, 255, 90));
            g2.fillRect(0, 0, getWidth(), 2);
            g2.fillRect(0, 0, 2, getHeight());
            g2.setColor(new Color(0, 0, 0, 120));
            g2.fillRect(0, getHeight() - 2, getWidth(), 2);
            g2.fillRect(getWidth() - 2, 0, 2, getHeight());

            if (selected) {
                g2.setColor(new Color(255, 255, 100));
                g2.drawRect(3, 3, getWidth() - 7, getHeight() - 7);
                g2.drawRect(4, 4, getWidth() - 9, getHeight() - 9);
            }

            if (preview != null) {
                int size = 72;
                int ix = (getWidth() - size) / 2;
                int iy = 18;
                g2.drawImage(preview, ix, iy, size, size, null);
            }

            g2.setFont(new Font("SansSerif", Font.BOLD, 12));
            g2.setColor(Color.WHITE);
            String name = file.getName();
            int nx = (getWidth() - g2.getFontMetrics().stringWidth(name)) / 2;
            g2.drawString(name, nx, getHeight() - 16);

            g2.dispose();
        }
    }
}
