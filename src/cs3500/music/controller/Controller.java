package cs3500.music.controller;

import cs3500.music.model.Note;
import cs3500.music.model.NoteRep;
import cs3500.music.model.SongRep;
import cs3500.music.view.CompositeView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;


/**
 * Controls the model for a CompositeView and adds user interaction functionality
 */
public class Controller implements IController {
    private final SongRep model;
    private final CompositeView view;
    private boolean playing;
    private boolean holdingShift;
    private int lengthOfNextNote; // the next created note will have this length in beats

    //todo public default constructor

    /** Create a controller and initialize its view to use the given model */
    public Controller(SongRep model) {
        this.model = model;
        this.view = new CompositeView(model);
        this.playing = true;
        this.holdingShift = false;
        this.lengthOfNextNote = 0;
        setUpKeys();
        setUpMouse();
    }

    @Override
    public void run() {
        view.run();

        if (playing) {
            incrementBeat();
        }
    }

    @Override
    public void incrementBeat() {
        model.setCurrentBeat(model.getBeat() + 1);
    }

    @Override
    public void changePlayState() {
        playing = !playing;
        view.changePlayState();
    }

    @Override
    public void moveNote() {
        //todo
    }

    @Override
    public void jumpTo(int beat) {
        model.setCurrentBeat(beat);
        view.jumpTo(beat);
    }

    /**
     * Add all desired functionality for keyboard interaction
     */
    private void setUpKeys() {
        Map<Integer, Runnable> keyPresses = new HashMap<>();
        Map<Integer, Runnable> keyReleases = new HashMap<>();
        Map<Integer, Runnable> keyTypes = new HashMap<>(); //TODO not sure how these work

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
    class ScrollLeft implements Runnable { //TODO note: if scrolling left of red line, will scroll you back automatically
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
    class NoteGod implements Runnable {
        public void run() {
            Point mouseLoc = view.getMousePosition();
            NoteRep temp = view.getNoteAtMouseLocation(mouseLoc); //TODO This is a temporary solution...

            if (view.noteAtLocation(mouseLoc)) {
                model.removeNote(temp);
            } else if (lengthOfNextNote > 0) {
                Note n = new Note(temp.getStart(), lengthOfNextNote, temp.getOctave(),
                        temp.getPitch(), 1, 65);
                model.addNote(n);
                lengthOfNextNote = 0;
            }
        }
    }
}
