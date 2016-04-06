package cs3500.music.controller;

import cs3500.music.model.SongRep;

import java.util.Objects;

/**
 * Builds a controller based on a given view type and a model
 */
public class ControllerFactory {
    String viewType;
    SongRep model;

    /**
     * @param viewType "console", "midi", "visual", or "composite", the type of view you want
     * @param model what song the controller should build initially
     */
    public ControllerFactory(String viewType, SongRep model) {
        Objects.requireNonNull(viewType);
        Objects.requireNonNull(model);
        this.viewType = viewType;
        this.model = model;
    }

    /**
     * @return an {@link IController} based on the fields
     */
    public IController build() {
        switch(viewType) {
            case "composite":
                return new CompositeController(model);
            case "midi":
                return new MidiController(model);
            case "console":
                return new ConsoleController(model);
            case "visual":
                return new GuiController(model);
            default:
                throw new IllegalArgumentException("View does not exist");
        }
    }
}
