package cs3500.music.controller;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.SongRep;
import cs3500.music.view.CompositeView;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Ari on 4/2/2016.
 */
public class Controller implements IController {
    private final SongRep model;
    private final CompositeView view;
    private boolean playing;
    private boolean holdingShift;
    private int lengthOfNextNote; // the next created note will have this length in beats

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

    }

    @Override
    public void jumpTo(int beat) {
        model.setCurrentBeat(beat);
        view.jumpTo(beat);
    }

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

    private void setUpMouse() {
        MouseHandler mh = new MouseHandler();
        mh.setClickEvent(MouseEvent.BUTTON1, new NoteGod(mh.x, mh.y));
        view.addMouseListener(mh);
    }


    class Pause implements Runnable {
        public void run() {
            changePlayState();
        }
    }

    class ScrollUp implements Runnable {
        public void run() {
            view.scrollUp();
        }
    }

    class ScrollDown implements Runnable {
        public void run() { view.scrollDown(); }
    }

    class ScrollLeft implements Runnable { //TODO note: if scrolling left of red line, will scroll you back automatically
        public void run() {
            view.scrollLeft();

            if (holdingShift) {
                model.setCurrentBeat(Math.max(0, model.getBeat() - 4));
            }
        }
    }

    class ScrollRight implements Runnable {
        public void run() {
            view.scrollRight();

            if (holdingShift) {
                model.setCurrentBeat(model.getBeat() + 4);
            }
        }
    }

    class SkipToStart implements Runnable {
        public void run() { jumpTo(0); }
    }

    class SkipToEnd implements Runnable {
        public void run() { jumpTo(model.getLength()); }
    }

    class HoldShift implements Runnable {
        public void run() { holdingShift = true; }
    }

    class ReleaseShift implements Runnable {
        public void run() { holdingShift = false; }
    }

    class SetNextNoteLength implements Runnable {
        private int numberPressed;
        SetNextNoteLength(int num) { this.numberPressed = num; }

        public void run() {
            lengthOfNextNote = Integer.valueOf(Integer.toString(lengthOfNextNote)
                    + Integer.toString(numberPressed));
        }
    }

    class NoteGod implements Runnable {
        private int x;
        private int y;
        NoteGod (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void run() {
            System.out.println("Click received at " + x + ", " + y + "!");
            System.out.println("Length of next note should be: " + lengthOfNextNote);
            if (false) { //todo if there is a note at the position x and y
                //todo remove that note
            } else if (lengthOfNextNote > 0) {
                //todo add a new note of length lengthOfNextNote at position  of note at x and y
                Note n = new Note(50, lengthOfNextNote, 4, Pitch.C, 1, 65);
                model.addNote(n);
                lengthOfNextNote = 0;
            }
        }
    }
}
