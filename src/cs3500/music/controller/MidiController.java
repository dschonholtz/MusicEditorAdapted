package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.MidiViewImpl;

import javax.swing.*;

/**
 * Created by dschonholtz on 4/6/2016.
 */
public class MidiController implements IController {

    SongRep model;
    MidiViewImpl view;

    MidiController (SongRep model) {
        this.model = model;
        view = new MidiViewImpl(model);

    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            Timer time = new Timer(model.getTempo() / 1000, (event -> play()));
            time.setInitialDelay(0);
            time.start();
        });
    }

    private void play() {
        model.setCurrentBeat(model.getBeat() + 1);
        view.run();
    }
}
