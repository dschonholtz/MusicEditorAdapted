package cs3500.music.view;

import cs3500.music.model.SongRep;

import java.awt.event.KeyListener;


/**
 * The idea here is to abstract the work of key handlers to the controller as the view shouldn't care.
 * This also means that once per tick the screen and sounds are updated as needed but the controller controls an
 * individual tick.
 */
public class CompositeView implements IMusicView {
    private MidiViewImpl midi;
    public GuiViewFrame gui; //TODO DON'T FUCKING DO THIS
    private boolean playing;

    public CompositeView() {
        //TODO we need to put stuff here
    }

    public CompositeView(SongRep song) {
        //TODO TEST NULLS! This is proof of concept so I'm being lazy
        this.midi = new MidiViewImpl(song);
        this.gui = new GuiViewFrame(song);
        this.playing = true;
    }

    public CompositeView(SongRep song, boolean playing) {
        //TODO TEST NULLS! This is proof of concept so I'm being lazy
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

    public void addKeyListener(KeyListener keyListener) {
        gui.addKeyListener(keyListener);
    }

    public void changePlayState() {
        playing = !playing;
    }
}
