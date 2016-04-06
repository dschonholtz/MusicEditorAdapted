package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.GuiViewFrame;
import javax.swing.*;
import java.util.Objects;

/**
 * A controller to control a gui view
 */
public class GuiController implements IController {
    SongRep model;
    GuiViewFrame view;

    /**
     * @param model the song to show in the gui view
     */
    public GuiController (SongRep model) {
        Objects.requireNonNull(model);
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

    /**
     * runs the view
     */
    private void play() {
        view.run();
        model.setCurrentBeat(model.getBeat() + 1);
    }
}
