package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.ConsoleView;

/**
 * Created by dschonholtz on 4/6/2016.
 */
public class ConsoleController implements IController{
    SongRep model;
    ConsoleView view;

    ConsoleController (SongRep model) {
        this.model = model;
        view = new ConsoleView(model);
    }

    @Override
    public void run() {
        view.run();
    }

}
