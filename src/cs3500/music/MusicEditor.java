package cs3500.music;

import cs3500.music.view.*;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        if(args.length < 2) {
            throw new IOException("You havfta have at least two command line arrrggs");
        }

        IMusicView view = new ViewFactory().buildView(args[0], args[1].toLowerCase());
        view.run();

        if (args.length > 2) {
            IMusicView view2 = new ViewFactory().buildView(args[0], args[2].toLowerCase());
            view2.run();
        }
    }
}
