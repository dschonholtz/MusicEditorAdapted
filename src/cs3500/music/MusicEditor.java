package cs3500.music;

import cs3500.music.controller.ControllerFactory;
import cs3500.music.util.SongFactory;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;

public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {

        if(args.length > 2) {
            throw new IllegalArgumentException("You havfta have at least two" +
                    " command line arrrggs");
        }

        new ControllerFactory(args[1], new SongFactory().buildSong(args[0])).build().run();
    }
}
