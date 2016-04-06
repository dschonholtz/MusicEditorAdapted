package cs3500.music.view;

import cs3500.music.model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Panel that actually draws the music editor gui
 */
public class ConcreteGuiViewPanel extends JPanel {
    private SongRep model;
    private List<String> rangeOfNotes;
    private int songLength;
    private List<Rectangle> rects = new ArrayList();
    public static final int BEAT_WIDTH = 20; // in pixels
    public static final int NOTE_HEIGHT = 20; // in  pixels
    public static final int SIDE_WIDTH = 20; // in pixels
    public static final int X_INIT = BEAT_WIDTH + (SIDE_WIDTH + 5); // upper left corner of grid
    private int xWinStart; // number of measures scrolled to right from zero


    public ConcreteGuiViewPanel(SongRep model) {
        super();
        this.model = model;
        this.rangeOfNotes = model.getRange();
        this.songLength = model.getLength();
        this.xWinStart = 0;
    }

    public ConcreteGuiViewPanel() {
        super();
        this.model = new GenericSong();   //The model will go here
        this.rangeOfNotes = new ArrayList<>();
        this.songLength = 0;
        this.xWinStart = 0; 
    }

    @Override
    public void paintComponent(Graphics g) {
        updateModel();
        super.paintComponent(g);

        paintNotes(g);
        paintTopLine(g);
        paintNoteLabels(g);
        paintMeasures(g);
        paintRedLine(g);
    }

    /**
     * Paints all the notes onto the panel
     * @param g the <code>Graphics</code> object to protect
     */
    private void paintNotes(Graphics g) {
        List<NoteRep> notes = model.getAllNotes();
        rects.clear();
        for (NoteRep n : notes) {
            int noteY = calculateY(n);

            Rectangle r;

            if (noteY <= NOTE_HEIGHT / 2) continue;
            if ((n.getStart() - xWinStart * 4) >= 0) {
                r = new Rectangle((n.getStart() - xWinStart * 4) * BEAT_WIDTH + X_INIT, noteY,
                        BEAT_WIDTH * n.getDuration(), NOTE_HEIGHT);

                rects.add(r);

                g.setColor(Color.CYAN);
                g.fillRect(r.x, r.y, r.width, r.height);
                g.setColor(Color.BLACK);
                g.fillRect((n.getStart() - xWinStart * 4) * BEAT_WIDTH + X_INIT, noteY,
                        BEAT_WIDTH, NOTE_HEIGHT);
            } else if (n.getDuration() + n.getStart() > xWinStart * 4) {
                // duration > difference between initial start and actual
                r = new Rectangle(X_INIT, noteY, BEAT_WIDTH * (n.getDuration() +
                        n.getStart() - xWinStart * 4), NOTE_HEIGHT);
                g.setColor(Color.CYAN);
                g.fillRect(r.x, r.y, r.width, r.height);
                // dont draw the initial beat as it would be handled in the first if.
                // Draw the beat starting at the initial space. draw it the length of
                // its duration - the difference in its start and the actual start
            }
        }
    }

    /**
     * Paints the very top, vertical line onto the panel
     * @param g the <code>Graphics</code> object to protect
     */
    private void paintTopLine(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(X_INIT, NOTE_HEIGHT, ((songLength + (songLength % 4)) * BEAT_WIDTH) - 5,
                NOTE_HEIGHT);
    }

    /**
     * Paints the names of all the notes in the range down the left column and the
     * lines separating them onto the panel
     * @param g the <code>Graphics</code> object to protect
     */
    private void paintNoteLabels(Graphics g) {
        int count = 0;
        for (int i = rangeOfNotes.size() - 1; i >= 0; i--) {
            String s = rangeOfNotes.get(i);
            // write out the note names on the left column
            int y = count * NOTE_HEIGHT + NOTE_HEIGHT * 2;
            g.drawString(s, SIDE_WIDTH / 3, y - 5);
            // draw the lines for where the notes go
            g.drawLine(X_INIT, y, ((songLength + (songLength % 4)) * BEAT_WIDTH) - 5, y);

            count++;
        }
    }

    /**
     * Paints the number of every measure along the top and the lines separating them
     * @param g the <code>Graphics</code> object to protect
     */
    private void paintMeasures(Graphics g) {
        for (int j = 0; j <= songLength + (songLength % 4); j++) {
            int xValue = (j + 1) * BEAT_WIDTH + SIDE_WIDTH + 5 - xWinStart * 4 * BEAT_WIDTH;
            if (j % 16 == 0) { // label every 16th beat / 4 measures
                g.drawString(Integer.toString(j), xValue,  NOTE_HEIGHT);
            }
            if (j % 4 == 0) { // draw lines separating every 4th beat / 1 measure
                g.drawLine(xValue, NOTE_HEIGHT, xValue, (rangeOfNotes.size() + 1) * NOTE_HEIGHT);
            }
        }
    }

    /**
     * Paints the red line representing the current beat onto the panel
     * @param g the <code>Graphics</code> object to protect
     */
    private void paintRedLine(Graphics g) {
        g.setColor(Color.RED);
        int cbx = currentBeatX();
        if ((model.getBeat() + 1) - xWinStart * 4 > 0) { // don't draw when not in view
            g.drawLine(cbx, NOTE_HEIGHT, cbx, NOTE_HEIGHT + NOTE_HEIGHT * rangeOfNotes.size());
        }
    }

    /**
     * @return the X value of the current beat
     */
    public int currentBeatX() {
        return ((model.getBeat() + 1) - xWinStart * 4) * BEAT_WIDTH + SIDE_WIDTH + 5;
    }

    /**
     * @param n the {@link NoteRep} whose Y location we want
     * @return the Y location in pixels that the given note should be painted at
     */
    private int calculateY(NoteRep n) {
        String high = rangeOfNotes.get(rangeOfNotes.size() - 1);
        int octave = noteOctave(high);
        Pitch p = notePitch(high);

        int ret = (octave - n.getOctave()) * 12 + p.ordinal() - n.getPitch().ordinal();
        ret =  ret * NOTE_HEIGHT + NOTE_HEIGHT;

        return ret;
    }

    /**
     * @param s the {@link String} representation of a note
     * @return the correct {@link Pitch} extracted from the string
     */
    private Pitch notePitch(String s) {
        Pitch p;

        if (s.charAt(1) == '#') {
            p = Pitch.valueOf(s.substring(0,1) + "S");
        }
        else {
            p = Pitch.valueOf(s.substring(0,1));
        }

        return p;
    }

    /**
     * @param s the {@link String} representation of a note
     * @return the correct int extracted from the string
     */
    private int noteOctave(String s) {
        int octave;

        if (s.charAt(1) == '#') {
            octave = Integer.valueOf(s.substring(2));
        }
        else {
            octave = Integer.valueOf(s.substring(1));
        }

        return octave;
    }

    @Override
    public Dimension getPreferredSize() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        return new Dimension(width, height);
    }

    /**
     * Called to update the model's length so new notes will have space drawn for them
     */
    private void updateModel() {
        this.songLength = model.getLength();
    }

    /**
     * Removes the lowest note from stored rangeOfNotes and adds the note one higher than the
     * highest to effectively shift the range up by one note
     */
    public void shiftRangeUp() {
        if(!rangeOfNotes.get(rangeOfNotes.size() - 1).equals("G10")) {
            this.rangeOfNotes.remove(0);
            String s = this.rangeOfNotes.get(rangeOfNotes.size() - 1);

            Pitch p = notePitch(s);
            int o = noteOctave(s);
            Note n;

            if (p.equals(Pitch.B)) { // end of the octave
                n = new Note(0, 1, o + 1, Pitch.C, 1, 65);
            } else {
                n = new Note(0, 1, o, Pitch.values()[p.ordinal() + 1], 1, 65);
            }

            this.rangeOfNotes.add(n.toString());
        }

    }

    /**
     * Removes the highest note from stored rangeOfNotes and adds the note one lower than the
     * lowest to effectively shift the range down by one note
     */
    public void shiftRangeDown() {
        if(!rangeOfNotes.get(0).equals("C-1")) {
            this.rangeOfNotes.remove(rangeOfNotes.size() - 1);
            String s = this.rangeOfNotes.get(0);

            Pitch p = notePitch(s);
            int o = noteOctave(s);
            Note n;

            if (p.equals(Pitch.C)) { // end of the octave
                n = new Note(0, 1, o - 1, Pitch.B, 1, 65);
            } else {
                n = new Note(0, 1, o, Pitch.values()[p.ordinal() - 1], 1, 65);
            }

            this.rangeOfNotes.add(0, n.toString());
        }

    }

    /**
     * @param loc the point that we are checking
     * @return true if there is a note drawn at that location
     */
    public boolean noteAtLocation(Point loc) {
        for(Rectangle r : rects) {
            if(r.contains(loc.getX() - BEAT_WIDTH / 2, loc.getY() - NOTE_HEIGHT * 2)) { //todo wtf why
            //if(r.contains(loc.getX(), loc.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param loc the location we are checking
     * @return the note that would be at the given location regardless of whether one exists
     */
    public NoteRep getNoteAtLocation(Point loc) {
        if(loc.getY() > NOTE_HEIGHT && loc.getY() < NOTE_HEIGHT * (rangeOfNotes.size())) { // no minus one on purpose!
            int ind = ((int) (loc.getY() / NOTE_HEIGHT)) - 3; // index on range of notes
            String sNote = rangeOfNotes.get(rangeOfNotes.size() - 1 - ind);
            int octave = noteOctave(sNote);
            Pitch p = notePitch(sNote);
            // beat mouse is in
            int mouseBeat = ((int)loc.getX() - X_INIT) / BEAT_WIDTH + xWinStart * 4;

            List<NoteRep> notes = model.getNotesPlayingAtT(mouseBeat);
            for(NoteRep n : notes) {
                if(n.getPitch().equals(p) && n.getOctave() == octave) {
                    return n;
                }
            }
            return new Note(mouseBeat, 1, octave, p, 1, 65);
        }
        else {
            throw new IllegalArgumentException("That points was outside the bounds of the clickable area");
        }
    }

    public void scrollLeft() {
        if (xWinStart > 0) {
            xWinStart--;
        }
    }

    public void scrollRight() {
        xWinStart++;
    }

    public int getXWinStart() {
        return xWinStart;
    }

    public void setXWinStart(int x) {
        if(x > 0) {
            xWinStart = x;
        }
    }
}
