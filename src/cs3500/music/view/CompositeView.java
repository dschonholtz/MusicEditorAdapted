package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
import cs3500.music.model.NoteRep;
import cs3500.music.model.SongRep;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Objects;


/**
 * The idea here is to abstract the work of key handlers to the controller as the view shouldn't care.
 * This also means that once per tick the screen and sounds are updated as needed but the controller controls an
 * individual tick.
 */
public class CompositeView implements GuiView {
    private MidiViewImpl midi;
    private GuiView gui;
    private boolean playing;

    /**
     * Default constructor builds a view with a model that contains one note.
     */
    public CompositeView() {
        GenericSong song = new GenericSong();
        song.addNote(new Note());
        this.midi = new MidiViewImpl(song);
        this.gui = new GuiViewFrame(song);
        this.playing = true;
    }

    /**
     * Builds a song
     * @param song -  This is the song that the view uses
     */
    public CompositeView(SongRep song) {
        Objects.requireNonNull(song);

        this.midi = new MidiViewImpl(song);
        this.gui = new GuiViewFrame(song);
        this.playing = true;
    }

    /**
     * Constructor for Composite view
     * @param song - The model the view uses
     * @param playing - Whether or not the song is initially playing
     */
    public CompositeView(SongRep song, boolean playing) {
        Objects.requireNonNull(song);
        Objects.requireNonNull(playing);

        this.midi = new MidiViewImpl(song);
        this.gui = new GuiViewFrame(song);
        this.playing = playing;
    }

    @Override
    public void run() {
        if (playing) {
            midi.run();
        }

        gui.run();
    }

    @Override
    public void addKeyListener(KeyListener keyListener) {
        gui.addKeyListener(keyListener);
    }


    @Override
    public void addMouseListener(MouseListener mouseListener) {
        gui.addMouseListener(mouseListener);
    }


    @Override
    public void scrollLeft() { gui.scrollLeft(); }


    @Override
    public void scrollRight() { gui.scrollRight(); }


    @Override
    public void scrollUp() { gui.scrollUp(); }

    @Override
    public void scrollDown() { gui.scrollDown(); }


    @Override
    public NoteRep getNoteAtMouseLocation(Point loc)
    {
        return gui.getNoteAtMouseLocation(loc);
    }


    @Override
    public boolean noteAtLocation(Point loc)
    {
        return gui.noteAtLocation(loc);
    }

    @Override
    public void changePlayState() {
        playing = !playing;
    }

    @Override
    public void jumpTo(int beat) {
        gui.jumpTo(beat);
    }

    /**
     * @return - returns a point with x and y where the mose was
     */
    public Point getMousePosition() {
        return gui.getMousePosition();
    }

}
