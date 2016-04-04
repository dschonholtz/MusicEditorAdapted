package cs3500.music.controller;

import cs3500.music.model.SongRep;
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
public class Controller implements IController, ActionListener {
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
        System.out.println("you didn't fuck up quite as bad");
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

        KeyboardHandler kh = new KeyboardHandler();
        kh.setKeyPressedMap(keyPresses);
        view.addKeyListener(kh);
        System.out.println("Controller added key listener");
        System.out.println(kh.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hello");
        changePlayState();
//        switch (e.getActionCommand()) {
//            case :
//                changePlayState();
//                break;
//            default:
//                break;
//        }
    }

    class Pause implements Runnable {
        public void run() {
            System.out.println("fuck");
        }
    }
}
