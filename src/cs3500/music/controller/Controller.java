package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ViewFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by Ari on 4/2/2016.
 */
public class Controller implements IController {
    private final SongRep model;
    private final IMusicView view;
    private boolean playing;
    private final KeyListener listener;

    public Controller(SongRep model, String viewType) {
        this.model = model;
        this.view = new ViewFactory().buildView(model, viewType);
        this.playing = true;
        this.listener = setUpKeys();
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

    private KeyListener setUpKeys() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                changePlayState();
            }
        };

        KeyboardHandler kh = new KeyboardHandler();
        kh.addEvent(KeyEvent.VK_SPACE, r, KeyboardHandler.KeyPTR.PRESSED);
        return kh;
    }
}
