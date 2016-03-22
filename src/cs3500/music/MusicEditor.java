package cs3500.music;

import cs3500.music.model.Pitch;
import cs3500.music.view.*;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        if(args.length < 2) {
            throw new IOException("You havfta have at least two command line arrrggs");
        }
        IMusicView view = new ViewFactory().buildView(args[0], args[1]);
        IMusicView view2 = new ViewFactory().buildView(args[0], args[2]);
        view.run();
        view2.run();
//        IMusicView testView = new ViewFactory().buildView("mary-little-lamb.txt", "test");
//        testView.run();
    }
}
