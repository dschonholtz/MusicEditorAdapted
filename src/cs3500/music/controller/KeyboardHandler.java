package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Binds keys to runnable classes to add keyboard functionality
 */
public class KeyboardHandler implements KeyListener {
    private Map<Integer, Runnable> keyPressed;
    private Map<Integer, Runnable> keyTyped;
    private Map<Integer, Runnable> keyReleased;

    /**
     * Default constructor sets all maps to empty
     */
    public KeyboardHandler() {
        keyPressed = new HashMap<>();
        keyTyped = new HashMap<>();
        keyReleased = new HashMap<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Objects.requireNonNull(e);
        if (keyTyped.containsKey(e.getKeyCode())) {
            keyTyped.get(e.getKeyCode()).run();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Objects.requireNonNull(e);
        if (keyPressed.containsKey(e.getKeyCode())) {
            keyPressed.get(e.getKeyCode()).run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Objects.requireNonNull(e);
        if (keyReleased.containsKey(e.getKeyCode())) {
            keyReleased.get(e.getKeyCode()).run();
        }
    }

    /**
     * Set the map for key typed events.
     */
    public void setKeyTypedMap(Map<Integer, Runnable> map) {
        Objects.requireNonNull(map);
        keyTyped = map;
    }

    /**
     * Set the map for key pressed events.
     */
    public void setKeyPressedMap(Map<Integer, Runnable> map) {
        Objects.requireNonNull(map);
        keyPressed = map;
    }

    /**
     * Set the map for key released events.
     */
    public void setKeyReleasedMap(Map<Integer, Runnable> map) {
        Objects.requireNonNull(map);
        keyReleased = map;
    }
}
