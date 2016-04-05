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
    private final IMusicView view;
    private boolean playing;

    public Controller(SongRep model, String viewType) {
        this.model = model;
        this.view = new ViewFactory().buildView(model, viewType);
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

    }

    @Override
    public void jumpToBeginning() {

    }

    private void setUpKeys() {
        Map<Integer, Runnable> keyPresses = new HashMap<>();
        keyPresses.put(KeyEvent.VK_SPACE, new Pause());
        keyPresses.put(KeyEvent.VK_DOWN, new ScrollDown());
        keyPresses.put(KeyEvent.VK_UP, new ScrollUp());
        keyPresses.put(KeyEvent.VK_LEFT, new ScrollLeft());
        keyPresses.put(KeyEvent.VK_RIGHT, new ScrollRight());

        KeyboardHandler kh = new KeyboardHandler();
        kh.setKeyPressedMap(keyPresses);
        view.addKeyListener(kh);
    }


    class Pause implements Runnable {
        public void run() {
            changePlayState();
        }
    }

    class ScrollLeft implements Runnable {
        @Override
        public void run() {
            ((CompositeView)view).gui.scrollLeft();
        }
    }

    class ScrollRight implements Runnable {
        public void run() {
            ((CompositeView)view).gui.scrollRight();
        }
    }

    class ScrollUp implements Runnable {
        public void run() {
            ((CompositeView)view).gui.scrollUp();
        }
    }

    class ScrollDown implements Runnable {
        public void run() {
            ((CompositeView) view).gui.scrollDown();
        }
    }
}
