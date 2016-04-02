package cs3500.music.controller;

import cs3500.music.model.SongRep;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ViewFactory;

/**
 * Created by Ari on 4/2/2016.
 */
public class Controller implements IController {
    private final SongRep model;
    private final IMusicView view;

    public Controller(SongRep model, String viewType) {
        this.model = model;
        this.view = new ViewFactory().buildView(model, viewType);
    }

    @Override
    public void incrementBeat() {

    }

    @Override
    public void keyListener() {

    }

    @Override
    public void changePlayState() {

    }

    @Override
    public void mouseListener() {

    }

    @Override
    public void moveNote() {

    }

    @Override
    public void jumpToEnd() {

    }

    @Override
    public void jumpToBeginning() {

    }
}
