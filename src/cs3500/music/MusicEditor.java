package cs3500.music;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        if(args.length < 2) {
            throw new IOException("You havfta have at least two command line arrrggs");
        }
    }
}
