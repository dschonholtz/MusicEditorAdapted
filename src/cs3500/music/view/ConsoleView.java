package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.SongRep;

import java.awt.event.KeyListener;

/**
 * Outputs a string representation of the song to the console
 */
public class ConsoleView implements IMusicView {
    private SongRep model;

    public ConsoleView() {
        model = new GenericSong();
    }

    public ConsoleView(SongRep model) {
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println(model.getState());
    }

    @Override
    public void addKeyListener(KeyListener keyListener) {
        //TODO maybe just nothing?
    }

    @Override
    public void changePlayState() {

    }
}
