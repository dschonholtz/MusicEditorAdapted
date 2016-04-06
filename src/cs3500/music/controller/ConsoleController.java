package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.ConsoleView;

import java.util.Objects;

/**
 * Controller for a console view
 * */
public class ConsoleController implements IController {
    private ConsoleView view;

    public ConsoleController(SongRep model) {
        Objects.requireNonNull(model);
        view = new ConsoleView(model);
    }

    @Override
    public void run() {
        view.run();
    }
}
