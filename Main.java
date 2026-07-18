import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
    private static final String CARD_MENU = "menu";
    private static final String CARD_SKIN = "skin";
    private static final String CARD_GAME = "game";

    private final JFrame window = new JFrame("Car Game");
    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);

    private MenuPanel menuPanel;
    private SkinSelectPanel skinSelectPanel;
    private GamePanel gamePanel;
    private boolean multiplayerMode;

    public Main() {
        menuPanel = new MenuPanel(this::onPlay, this::onMultiplayer, this::onExit);
        skinSelectPanel = new SkinSelectPanel(this::onSkinConfirmed, this::showMenu);

        root.add(menuPanel, CARD_MENU);
        root.add(skinSelectPanel, CARD_SKIN);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(root);
        window.pack();
        window.setLocationRelativeTo(null);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopGame();
            }
        });
        window.setVisible(true);

        showMenu();
    }

    private void onPlay() {
        multiplayerMode = false;
        skinSelectPanel.refreshSkins();
        cards.show(root, CARD_SKIN);
    }

    private void onMultiplayer() {
        JOptionPane.showMessageDialog(
            window,
            "Multiplayer em breve!",
            "Multiplayer",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void onExit() {
        stopGame();
        System.exit(0);
    }

    private void onSkinConfirmed(String skinPath) {
        stopGame();

        if (gamePanel != null) {
            root.remove(gamePanel);
        }

        gamePanel = new GamePanel(skinPath, multiplayerMode);
        root.add(gamePanel, CARD_GAME);
        cards.show(root, CARD_GAME);
        window.pack();

        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
    }

    private void showMenu() {
        stopGame();
        cards.show(root, CARD_MENU);
    }

    private void stopGame() {
        if (gamePanel != null) {
            gamePanel.stopGameThread();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
