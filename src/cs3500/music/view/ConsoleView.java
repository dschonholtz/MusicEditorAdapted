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

    public ConsoleView(SongRep model, StringBuilder log) {
        this.model = model;
        log.append(model.getState());
    }

    @Override
    public void run() {
        System.out.println(model.getState());
    }
}
