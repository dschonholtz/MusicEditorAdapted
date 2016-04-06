package cs3500.music.controller;

import cs3500.music.model.SongRep;

/**
 * Created by dschonholtz on 4/6/2016.
 */
public class ControllerFactory {

    String viewType;
    SongRep model;

    public ControllerFactory(String viewType, SongRep model) {
        this.viewType = viewType;
        this.model = model;
    }

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
