package cs3500.music.view;

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
public class CompositeView implements GuiView { //TODO i don't know how i feel about it but it;s the only way i can find to do this without exposing gui field
    private MidiViewImpl midi;
    private GuiView gui;
    private boolean playing;

    public CompositeView() {
        //TODO we need to put stuff here
    }

    public CompositeView(SongRep song) {
        Objects.requireNonNull(song);

        this.midi = new MidiViewImpl(song);
        this.gui = new GuiViewFrame(song);
        this.playing = true;
    }

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
        return null;
    }

    @Override
    public boolean noteAtLocation(Point loc)
    {
        return false;
    }

    @Override
    public void changePlayState() {
        playing = !playing;
    }

    @Override
    public void jumpTo(int beat) {
        gui.jumpTo(beat);
    }
}
