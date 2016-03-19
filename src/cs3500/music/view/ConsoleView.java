package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.SongRep;

/**
 * Created by Ari on 3/19/2016.
 */
public class ConsoleView implements IMusicView {
    private SongRep model;

    public ConsoleView() {
        model = new GenericSong();
    }

    public ConsoleView(GenericSong model) {
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println(model.getState());
    }
}
