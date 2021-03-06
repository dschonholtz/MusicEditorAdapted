package cs3500.music.controller;

import cs3500.music.model.*;
import cs3500.music.otherView.CompositeViewImpl;
import cs3500.music.view.CompositeView;

import javax.swing.*;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the model for a CompositeView and adds user interaction functionality
 */
public class CompositeController implements IController {
    private final Model model;
    private final CompositeViewImpl view;
    private boolean playing;
    private int lengthOfNextNote; // the next created note will have this length in beats
    private NoteRep selectedNote;

    /**
     * Constructs a composite view with exactly one note in its model
     */
    public CompositeController() {
        throw new IllegalArgumentException("You must instantiate your controller properly");
    }

    /** Create a controller and initialize its view to use the given model */
    public CompositeController(Model model) {
        this.model = model;
        this.view = new CompositeViewImpl();
        this.playing = true;
        this.lengthOfNextNote = 0;
        setUpKeys();
        setUpMouse();
    }

    @Override
    public void run() {
        view.display(model);
        view.play(model);
    }

    /**
     * Change the play state from pause to play or vice versa
     */
    public void changePlayState() {
        if (playing) view.pause();
        if (!playing) view.play(model);
    }

    /**
     * @return true if the current play state is true
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Add all desired functionality for keyboard interaction
     */
    private void setUpKeys() {
        Map<Integer, Runnable> keyPresses = new HashMap<>();
        Map<Integer, Runnable> keyReleases = new HashMap<>();
//
        keyPresses.put(KeyEvent.VK_SPACE, new Pause());
        keyPresses.put(KeyEvent.VK_DOWN, new ScrollDown());
        keyPresses.put(KeyEvent.VK_UP, new ScrollUp());
        keyPresses.put(KeyEvent.VK_LEFT, new ScrollLeft());
        keyPresses.put(KeyEvent.VK_RIGHT, new ScrollRight());
        keyPresses.put(KeyEvent.VK_HOME, new SkipToStart());
        keyPresses.put(KeyEvent.VK_END, new SkipToEnd());
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
        public void run() { view.controlPanel(model, "U"); }
    }

    /**
     * Scroll the view down
     */
    class ScrollDown implements Runnable {
        public void run() { view.controlPanel(model, "D"); }
    }

    /**
     * Scroll the view left
     */
    class ScrollLeft implements Runnable {
        public void run() { view.controlPanel(model, "L"); }
    }

    /**
     * Scroll the view right
     */
    class ScrollRight implements Runnable {
        public void run() { view.controlPanel(model, "R"); }
    }

    /**
     * Jump the current beat and the view to the beginning of the song
     */
    class SkipToStart implements Runnable {
        public void run() { view.play(model); }
    }

    /**
     * Jump the current beat and the view to the end of the song
     */
    class SkipToEnd implements Runnable {
        public void run() {
            view.getDisplayPanel().setLineX(model.getMaxBeats() * 20 + 60);
            view.controlPanel(model, "E"); }
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
            if (test) {
                mouseLoc = givenMouseLoc;
            }
            else {
                mouseLoc = view.getDisplayPanel().getMousePosition();
            }

            NoteRep temp = view.getNote(new Point(mouseLoc.x/20 - 2, mouseLoc.y/20 - 1));

            if (temp != null) {
                model.removeNote(temp.returnNote());
            } else if (lengthOfNextNote > 0) {
                //TEMP IS NULL!!!!!
                List<String> range = model.getRange();
                String noteS = range.get(range.size() - (mouseLoc.y/20));
                System.out.println(noteS);
                Pitch p = notePitch(noteS);
                int octave = noteOctave(noteS);
                OurNote n = new OurNote(mouseLoc.x/20 - 2, lengthOfNextNote, octave,
                        p, 1, 65);
                model.addNote(new Note(n));
                lengthOfNextNote = 0;
            }
        }
    }

    private int noteOctave(String s) {
        int o;

        if(s.charAt(1) == '#') {
            o = Integer.valueOf(s.substring(2));
        }
        else {
            o = Integer.valueOf(s.substring(1));
        }
        return o;
    }

    private Pitch notePitch(String s) {
        Pitch p;

        if(s.charAt(1) ==  '#') {
            p = Pitch.valueOf(s.substring(0,1) + "S");
        }
        else {
            p = Pitch.valueOf(s.substring(0,1));
        }
        return p;
    }

    /**
     * This selects a note and then moves it when the mouse is released.
     */
    class NoteDragPress implements Runnable {
        public void run() {
            Point mouseLoc = view.getDisplayPanel().getMousePosition();
            NoteRep noteAtLocation = view.getNote(new Point(mouseLoc.x / 20 - 2,
                    mouseLoc.y / 20 - 1));
            if (noteAtLocation != null) {
                selectedNote = noteAtLocation;
            }
        }
    }

    /**
     * Creates a new note when the mouse is released provided a note was previously selected
     */
    class NoteDragRelease implements Runnable {
        public void run() {
            Point mouseLoc = view.getDisplayPanel().getMousePosition();

            List<String> range = model.getRange();
            String noteS = range.get(range.size() - (mouseLoc.y / 20));
            Pitch p = notePitch(noteS);
            int octave = noteOctave(noteS);
            OurNote temp1 = new OurNote(mouseLoc.x / 20 - 1, lengthOfNextNote, octave, p, 1, 65);
            NoteRep temp2 = new Note(temp1);

            if (selectedNote != null) {
                OurNote n = new OurNote(temp2.getStart() - 1, selectedNote.getDuration(),
                        temp2.getOctave(),
                        temp2.getPitch(), selectedNote.getInstrument(), selectedNote.getVolume());
                try {
                    model.addNote(n);
                    model.removeNote(selectedNote.returnNote());
                } catch(IllegalArgumentException e) {
                    e.printStackTrace();
                }
                selectedNote = null;
            }
        }
    }
}
