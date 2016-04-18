package cs3500.music.controller;

import cs3500.music.model.Model;
import cs3500.music.otherView.GuiViewImpl;
import java.util.Objects;

/**
 * A controller to control a gui view
 */
public class GuiController implements IController {
    Model model;
    GuiViewImpl view;

    /**
     * @param model the song to show in the gui view
     */
    public GuiController (Model model) {
        Objects.requireNonNull(model);
        this.model = model;
        view = new GuiViewImpl();
    }

    @Override
    public void run() {
        play();
    }

    /**
     * runs the view
     */
    private void play() {
        view.display(model);
    }
}
