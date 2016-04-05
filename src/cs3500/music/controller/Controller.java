package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ViewFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Ari on 4/2/2016.
 */
public class Controller implements IController {
    private final SongRep model;
    private final CompositeView view;
    private boolean playing;

    public Controller(SongRep model) {
        this.model = model;
        this.view = new CompositeView(model);
        this.playing = true;
        setUpKeys();
    }

    @Override
    public void run() {
        view.run();

        if (playing) {
            incrementBeat();
        }
    }

    @Override
    public void incrementBeat() {
        model.setCurrentBeat(model.getBeat() + 1);
    }

    @Override
    public void changePlayState() {
        playing = !playing;
        view.changePlayState();
    }

    @Override
    public void moveNote() {

    }

    @Override
    public void jumpToEnd() {
        model.setCurrentBeat(model.getLength());
    }

    @Override
    public void jumpToBeginning() {
        model.setCurrentBeat(0);
    }

    private void setUpKeys() {
        Map<Integer, Runnable> keyPresses = new HashMap<>();
        keyPresses.put(KeyEvent.VK_SPACE, new Pause());
        keyPresses.put(KeyEvent.VK_DOWN, new ScrollDown());
        keyPresses.put(KeyEvent.VK_UP, new ScrollUp());
        keyPresses.put(KeyEvent.VK_LEFT, new ScrollLeft());
        keyPresses.put(KeyEvent.VK_RIGHT, new ScrollRight());
        keyPresses.put(KeyEvent.VK_HOME, new SkipToStart());
        keyPresses.put(KeyEvent.VK_END, new SkipToEnd());

        KeyboardHandler kh = new KeyboardHandler();
        kh.setKeyPressedMap(keyPresses);
        view.addKeyListener(kh);
    }


    class Pause implements Runnable {
        public void run() {
            changePlayState();
        }
    }

    class ScrollUp implements Runnable {
        public void run() {
            view.scrollUp();
        }
    }

    class ScrollDown implements Runnable {
        public void run() { view.scrollDown(); }
    }

    class ScrollLeft implements Runnable { //TODO note: if scrolling left of red line, will scroll you back automatically
        public void run() { view.scrollLeft(); }
    }

    class ScrollRight implements Runnable {
        public void run() { view.scrollRight(); }
    }

    class SkipToStart implements Runnable {
        public void run() { jumpToBeginning(); }
    }

    class SkipToEnd implements Runnable {
        public void run() { jumpToEnd(); }
    }
}
