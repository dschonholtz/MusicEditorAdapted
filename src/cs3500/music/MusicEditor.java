package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.SongRep;
import cs3500.music.util.SongFactory;

import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

/*
TODO
    FIX bottom rendering for notes!
    midi broken when adding
    Testing
        Keyboard
        Mouse
        Mock Synth
        Mock Reciever
        test all methods yay

    Add non nulls and throw exceptions
    Lagging playback
    Move timer to controller

 */
public class MusicEditor {
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        if(args.length > 2) {
            throw new IOException("You havfta have at least two command line arrrggs");
        }
        SongRep song = new SongFactory().buildSong(args[0]);
        Controller c = new Controller(song);
        c.run();


    }
}
