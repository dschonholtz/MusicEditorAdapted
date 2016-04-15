package cs3500.music.controller;

import cs3500.music.model.OurNote;
import cs3500.music.model.NoteRep;
import cs3500.music.model.SongRep;
import cs3500.music.view.CompositeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the model for a CompositeView and adds user interaction functionality
 */
public class CompositeController implements IController {
    private final SongRep model;
    private final CompositeView view;
    private boolean playing;
    private boolean holdingShift;
    private int lengthOfNextNote; // the next created note will have this length in beats
    private NoteRep selectedNote;

    /**
     * Constructs a composite view with exactly one note in its model
     */
    public CompositeController() {
        throw new IllegalArgumentException("You must instantiate your controller properly");
    }

    /** Create a controller and initialize its view to use the given model */
    public CompositeController(SongRep model) {
        this.model = model;
        this.view = new CompositeView(model);
        this.playing = true;
        this.holdingShift = false;
        this.lengthOfNextNote = 0;
        setUpKeys();
        setUpMouse();
    }

    /**
     * Play the current beat of the song
     */
    private void play() {
        try {
            view.run();
            if (playing) {
                incrementBeat();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            SwingUtilities.invokeLater(() -> {
                Timer time = new Timer(model.getTempo() / 1000, (event -> play()));
                time.setInitialDelay(0);
                time.start();
            });
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Increase the current beat of the model by 1
     */
    public void incrementBeat() {
        model.setCurrentBeat(model.getBeat() + 1);
    }

    /**
     * Change the play state from pause to play or vice cersa
     */
    public void changePlayState() {
        playing = !playing;
        view.changePlayState();
    }

    /**
     * @return true if the current play state is true
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Jumps the current beat of the model and view to the given beat
     * @param beat the beat to jump to
     */
    private void jumpTo(int beat) {
        model.setCurrentBeat(beat);
        view.jumpTo(beat);
    }

    /**
     * Add all desired functionality for keyboard interaction
     */
    private void setUpKeys() {
        Map<Integer, Runnable> keyPresses = new HashMap<>();
        Map<Integer, Runnable> keyReleases = new HashMap<>();

        keyPresses.put(KeyEvent.VK_SPACE, new Pause());
        keyPresses.put(KeyEvent.VK_DOWN, new ScrollDown());
        keyPresses.put(KeyEvent.VK_UP, new ScrollUp());
        keyPresses.put(KeyEvent.VK_LEFT, new ScrollLeft());
        keyPresses.put(KeyEvent.VK_RIGHT, new ScrollRight());
        keyPresses.put(KeyEvent.VK_HOME, new SkipToStart());
        keyPresses.put(KeyEvent.VK_END, new SkipToEnd());
        keyPresses.put(KeyEvent.VK_SHIFT, new HoldShift());
        keyPresses.put(KeyEvent.VK_0, new SetNextNoteLength(0));
        keyPresses.put(KeyEvent.VK_1, new SetNextNoteLength(1));
        keyPresses.put(KeyEvent.VK_2, new SetNextNoteLength(2));
        keyPresses.put(KeyEvent.VK_3, new SetNextNoteLength(3));
        keyPresses.put(KeyEvent.VK_4, new SetNextNoteLength(4));
        keyPresses.put(KeyEvent.VK_5, new SetNextNoteLength(5));
        keyPresses.put(KeyEvent.VK_6, new SetNextNoteLength(6));
        keyPresses.put(KeyEvent.VK_7, new SetNextNoteLength(7));
        keyPresses.put(KeyEvent.VK_8, new SetNextNoteLength(8));
        keyPresses.put(KeyEvent.VK_9, new SetNextNoteLength(9));

        keyReleases.put(KeyEvent.VK_SHIFT, new ReleaseShift());

        KeyboardHandler kh = new KeyboardHandler();
        kh.setKeyPressedMap(keyPresses);
        kh.setKeyReleasedMap(keyReleases);
        view.addKeyListener(kh);
    }

    /** Add all desired functionality for mouse interaction */
    private void setUpMouse() {
        MouseHandler mh = new MouseHandler();
        mh.setClickEvent(MouseEvent.BUTTON1, new NoteGod());
        mh.setPressEvent(MouseEvent.BUTTON1, new NoteDragPress());
        mh.setReleaseEvent(MouseEvent.BUTTON1, new NoteDragRelease());
        view.addMouseListener(mh);
    }

    /**
     * Pause playback of the song
     */
    class Pause implements Runnable {
        public void run() {
            changePlayState();
        }
    }

    /**
     * Scroll the view up
     */
    class ScrollUp implements Runnable {
        public void run() {
            view.scrollUp();
        }
    }

    /**
     * Scroll the view down
     */
    class ScrollDown implements Runnable {
        public void run() { view.scrollDown(); }
    }

    /**
     * Scroll the view left
     */
    class ScrollLeft implements Runnable {
        public void run() {
            view.scrollLeft();

            if (holdingShift) {
                model.setCurrentBeat(Math.max(0, model.getBeat() - 4));
            }
        }
    }

    /**
     * Scroll the view right
     */
    class ScrollRight implements Runnable {
        public void run() {
            view.scrollRight();

            if (holdingShift) {
                model.setCurrentBeat(model.getBeat() + 4);
            }
        }
    }

    /**
     * Jump the current beat and the view to the beginning of the song
     */
    class SkipToStart implements Runnable {
        public void run() { jumpTo(0); }
    }

    /**
     * Jump the current beat and the view to the end of the song
     */
    class SkipToEnd implements Runnable {
        public void run() { jumpTo(model.getLength()); }
    }

    /**
     * Recognize that shift is being held down
     */
    class HoldShift implements Runnable {
        public void run() { holdingShift = true; }
    }

    /**
     * Recognize that shift has been released
     */
    class ReleaseShift implements Runnable {
        public void run() { holdingShift = false; }
    }

    /**
     * Add the given digit to the length of the next note to be added by mouse click
     */
    class SetNextNoteLength implements Runnable {
        private int numberPressed;
        SetNextNoteLength(int num) { this.numberPressed = num; }

        public void run() {
            lengthOfNextNote = Integer.valueOf(Integer.toString(lengthOfNextNote)
                    + Integer.toString(numberPressed));
        }
    }

    /**
     * Add or remove note at the current mouse location
     */
    public class NoteGod implements Runnable {
        Point givenMouseLoc;
        boolean test;

        public NoteGod() {
            this.test = false;
        }

        public NoteGod(Point mouseLoc, boolean test) {
            this.givenMouseLoc = mouseLoc;
            this.test = test;
        }

        public void run() {
            Point mouseLoc;
            if(test) {
                mouseLoc = givenMouseLoc;
            }
            else {
                mouseLoc = view.getMousePosition();
                }
            boolean noteAtLocation = view.noteAtLocation(mouseLoc);

            NoteRep temp = view.getNoteAtMouseLocation(mouseLoc);
            if (noteAtLocation) {
                model.removeNote(temp);
            } else if (lengthOfNextNote > 0) {
                OurNote n = new OurNote(temp.getStart(), lengthOfNextNote, temp.getOctave(),
                        temp.getPitch(), 1, 65);
                model.addNote(n);
                lengthOfNextNote = 0;
            }
        }
    }

    /**
     * This selects a note and then moves it when the mouse is released.
     */
    class NoteDragPress implements Runnable {
        public void run() {
            Point mouseLoc = view.getMousePosition();
            boolean noteAtLocation = view.noteAtLocation(mouseLoc);
            if(noteAtLocation) {
                selectedNote = view.getNoteAtMouseLocation(mouseLoc);
            }
        }
    }

    /**
     * Creates a new note when the mouse is released provided a note was previously selected
     */
    class NoteDragRelease implements Runnable {
        public void run() {
            Point mouseLoc = view.getMousePosition();
            NoteRep temp = view.getNoteAtMouseLocation(mouseLoc);

            if(selectedNote != null) {
                OurNote n = new OurNote(temp.getStart(), selectedNote.getDuration(), temp.getOctave(),
                        temp.getPitch(), selectedNote.getInstrument(), selectedNote.getVolume());
                try {
                    model.addNote(n);
                    model.removeNote(selectedNote);
                } catch(IllegalArgumentException e) {
                    e.printStackTrace();
                }
                selectedNote = null;
            }
        }
    }
}
