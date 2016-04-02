package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ari on 4/2/2016.
 */
public class KeyboardHandler implements KeyListener {
    public enum KeyPTR { PRESSED, TYPED, RELEASED }

    Map<Integer, Runnable> keyPressed;
    Map<Integer, Runnable> keyTyped;
    Map<Integer, Runnable> keyReleased;

    public KeyboardHandler() {
        keyPressed = new HashMap<>();
        keyTyped = new HashMap<>();
        keyReleased = new HashMap<>();
    }

    public KeyboardHandler(Map<Integer, Runnable> pressed, Map<Integer, Runnable> typed,
                           Map<Integer, Runnable> released) {
        this.keyPressed = pressed;
        this.keyTyped = typed;
        this.keyReleased = released;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyTyped.get(e.getKeyCode()).run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed.get(e.getKeyCode()).run();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyReleased.get(e.getKeyCode()).run();
    }

    public void addEvent(Integer key, Runnable event, KeyPTR ptr) {
        switch (ptr) {
            case PRESSED:
                keyPressed.put(key, event);
                break;
            case TYPED:
                keyTyped.put(key, event);
                break;
            case RELEASED:
                keyReleased.put(key, event);
                break;
        }
    }
}
