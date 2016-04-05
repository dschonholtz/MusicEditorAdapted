package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ari on 4/2/2016.
 */
public class KeyboardHandler implements KeyListener {
    Map<Integer, Runnable> keyPressed;
    Map<Integer, Runnable> keyTyped;
    Map<Integer, Runnable> keyReleased;

    public KeyboardHandler() {
        keyPressed = new HashMap<>();
        keyTyped = new HashMap<>();
        keyReleased = new HashMap<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (keyTyped.containsKey(e.getKeyCode())) {
            keyTyped.get(e.getKeyCode()).run();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyPressed.containsKey(e.getKeyCode())) {
            keyPressed.get(e.getKeyCode()).run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyReleased.containsKey(e.getKeyCode())) {
            keyReleased.get(e.getKeyCode()).run();
        }
    }

    /**
     * Set the map for key typed events. Key typed events in Java Swing are characters
     */

    public void setKeyTypedMap(Map<Integer, Runnable> map) {
        keyTyped = map;
    }

    /**
     * Set the map for key pressed events. Key pressed events in Java Swing are integer codes
     */

    public void setKeyPressedMap(Map<Integer, Runnable> map) {
        keyPressed = map;
    }

    /**
     * Set the map for key released events. Key released events in Java Swing are integer codes
     */

    public void setKeyReleasedMap(Map<Integer, Runnable> map) {
        keyReleased = map;
    }
}
