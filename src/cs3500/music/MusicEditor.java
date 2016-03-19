package cs3500.music;

import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        String file = args[0];
        String view = args[1];
        switch (view) {
            case "console" :


                break;
            default :
                throw new IOException("Invalid Command Line Argument");
        }

        //GuiViewFrame view = new GuiViewFrame();
        //MidiView midiView = new MidiViewImpl();
//         You probably need to connect these views to your model, too...
    }
}
