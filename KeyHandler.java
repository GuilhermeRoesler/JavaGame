import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        else if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        else if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }
}
