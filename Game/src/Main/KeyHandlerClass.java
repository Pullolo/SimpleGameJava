package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerClass implements KeyListener {
    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 87){
            up = true;
        }
        if (e.getKeyCode() == 65){
            left = true;
        }
        if (e.getKeyCode() == 83){
            down = true;
        }
        if (e.getKeyCode() == 68){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 87){
            up = false;
        }
        if (e.getKeyCode() == 65){
            left = false;
        }
        if (e.getKeyCode() == 83){
            down = false;
        }
        if (e.getKeyCode() == 68){
            right = false;
        }
    }
}

