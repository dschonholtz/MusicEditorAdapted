package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.util.SongFactory;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ViewFactory;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;

/*
TODO
    midi broken when adding
    Testing
        Keyboard
        Mouse
        Mock Synth
        Mock Reciever
        test all methods yay

    Add non nulls and throw exceptions

    Lagging playback
    Factory build controller or plain view
 */
public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        if(args.length == 0) {
            new Controller().run();
        }
        else if(args.length == 1) {
            new Controller(new SongFactory().buildSong(args[0])).run();
        }
        else {
            if(args[1].toLowerCase().equals("composite")) {
                new Controller(new SongFactory().buildSong(args[0])).run();
            }
            else {
                IMusicView view = new ViewFactory().buildView(new SongFactory().buildSong(args[0]),args[1]);
                view.run();
            }
        }


    }
}
