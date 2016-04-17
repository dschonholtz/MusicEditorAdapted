package cs3500.music.controller;

import cs3500.music.model.Model;
import cs3500.music.model.SongRep;
import cs3500.music.otherView.MidiViewImpl;

import javax.swing.*;
import java.util.Objects;

/**
 * Controller for midi views
 */
public class MidiController implements IController {
    private Model model;
    private MidiViewImpl view;

    /**
     * @param model the song to play
     */
    public MidiController (Model model) {
        Objects.requireNonNull(model);
        this.model = model;
        view = new MidiViewImpl();

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
//        model.setCurrentBeat(model.getBeat() + 1);
//        view.run();
        view.displayOneBeat(model);
    }
}
