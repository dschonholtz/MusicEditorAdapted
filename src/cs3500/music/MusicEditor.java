package cs3500.music;

import cs3500.music.model.GenericSong;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
//import cs3500.music.view.GuiViewFrame;
//import cs3500.music.view.MidiViewImpl;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
//        GuiViewFrame view = new GuiViewFrame();
//        MidiViewImpl midiView = new MidiViewImpl();
        MusicReader mr = new MusicReader();
        CompositionBuilder<GenericSong> cb = new GenericSong.Builder();

        ConsoleView test1 = new ConsoleView(mr.parseFile(new FileReader("C:\\Users\\Ari\\Dropbox\\Code\\Java\\MusicEditor\\src\\mystery-3.txt"), cb));
        test1.run();
//         You probably need to connect these views to your model, too...
    }
}
