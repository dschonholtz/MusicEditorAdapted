package cs3500.music.controller;

import cs3500.music.otherView.ConsoleViewImpl;
import cs3500.music.model.Model;

import java.util.Objects;

/**
 * Controller for a console view
 * */
public class ConsoleController implements IController {
    private ConsoleViewImpl view;
    private Model model;

    public ConsoleController(Model model) {
        Objects.requireNonNull(model);
        this.model = model;
        view = new ConsoleViewImpl();
    }

    @Override
    public void run() {
        view.display(model);
    }
}
