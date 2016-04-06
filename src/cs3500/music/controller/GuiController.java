package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import javax.swing.*;

/**
 * Created by dschonholtz on 4/6/2016.
 */
public class GuiController implements IController{

    SongRep model;
    GuiViewFrame view;

    GuiController (SongRep model) {
        this.model = model;
        view = new GuiViewFrame(model);

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
        view.run();
    }
}
