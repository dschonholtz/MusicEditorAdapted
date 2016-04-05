package cs3500.music.view;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

/**
 * Panel that actually draws the music editor gui
 */
public class ConcreteGuiViewPanel extends JPanel {
    private SongRep model;
    private List<String> rangeOfNotes;
    private int songLength;
    public static final int BEAT_WIDTH = 20; // in pixels
    public static final int NOTE_HEIGHT = 20; // in  pixels
    public static final int SIDE_WIDTH = 20; // in pixels
    public int xWinStart;

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
        this.xWinStart = 0; // number of measures scrolled to right from zero
    }

    @Override
    public void paintComponent(Graphics g) {
        updateModel();

        super.paintComponent(g);
        int count = 0;

        int xInit = BEAT_WIDTH + (SIDE_WIDTH + 5);
        //Draw the notes themselves
        List<NoteRep> notes = model.getAllNotes();

        for (NoteRep n : notes) {
            int noteY = calculateY(n);

            if (noteY <= NOTE_HEIGHT / 2) continue;
            if ((n.getStart() - xWinStart * 4) >= 0) {
                g.setColor(Color.CYAN);
                g.fillRect((n.getStart() - xWinStart * 4) * BEAT_WIDTH + xInit, noteY, BEAT_WIDTH * n.getDuration(),
                        NOTE_HEIGHT);
                g.setColor(Color.BLACK);
                g.fillRect((n.getStart() - xWinStart * 4) * BEAT_WIDTH + xInit, noteY, BEAT_WIDTH, NOTE_HEIGHT);
            } else if (n.getDuration() + n.getStart() > xWinStart * 4) { // if the duration is > then the difference between the initial start
                                                                                // and the actual
                g.setColor(Color.CYAN);
                g.fillRect(xInit, noteY, BEAT_WIDTH * (n.getDuration() + n.getStart() - xWinStart * 4),
                        NOTE_HEIGHT);
                // dont draw the initial beat as it would be handled in the first if.
                // Draw the beat starting at the initial space. draw it the length of its duration - the difference in its start and the actual start
            }


        }

        //top line
        g.setColor(Color.BLACK);
        g.drawLine(xInit, NOTE_HEIGHT, ((songLength + (songLength % 4)) * BEAT_WIDTH) - 5,
                NOTE_HEIGHT);

        for(int i = rangeOfNotes.size() - 1; i >= 0; i--) {
            String s = rangeOfNotes.get(i);
            // write out the note names on the left column
            int y = count * NOTE_HEIGHT + NOTE_HEIGHT * 2;
            g.drawString(s, SIDE_WIDTH / 3, y - 5);
            // draw the lines for where the notes go
            g.drawLine(xInit, y, ((songLength + (songLength % 4)) * BEAT_WIDTH) - 5, y);

            count++;
        }

        for (int j = 0; j <= songLength + (songLength % 4); j++) {
            int xValue = (j + 1) * BEAT_WIDTH + SIDE_WIDTH + 5 - xWinStart * 4 * BEAT_WIDTH;
            if (j % 16 == 0) { // label every 16th beat / 4 measures
                g.drawString(Integer.toString(j), xValue,  NOTE_HEIGHT);
            }
            if (j % 4 == 0) { // draw lines separating every 4th beat / 1 measure
                g.drawLine(xValue, NOTE_HEIGHT, xValue, (rangeOfNotes.size() + 1) * NOTE_HEIGHT);
            }
        }

        // red line
        g.setColor(Color.RED);
        int cbx = currentBeatX();
        if(((model.getBeat() + 1) - xWinStart * 4) > 0) { // doesn't draw line when the line isn't in view :)
            g.drawLine(cbx, NOTE_HEIGHT, cbx, NOTE_HEIGHT + NOTE_HEIGHT * rangeOfNotes.size());
        }

    }

    public int currentBeatX() {
        return ((model.getBeat() + 1) - xWinStart * 4) * BEAT_WIDTH + SIDE_WIDTH + 5;
    }

    private int calculateY(NoteRep n) {
        String high = rangeOfNotes.get(rangeOfNotes.size() - 1);
        int octave = noteOctave(high);
        Pitch p = notePitch(high);

        int ret = (octave - n.getOctave()) * 12 + p.ordinal() - n.getPitch().ordinal();
        ret =  ret * NOTE_HEIGHT + NOTE_HEIGHT;

        return ret;
    }

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
//        int width = 1920; // entire song: ((songLength + (songLength % 4)) * BEAT_WIDTH) + BEAT_WIDTH*2;
//        int height = 1080; // entire song: (rangeOfNotes.size() * NOTE_HEIGHT + NOTE_HEIGHT * 4);
        return new Dimension(width, height);
    }

    private void updateModel() {
        this.songLength = model.getLength();
    }

    public void shiftRangeUp() {
        this.rangeOfNotes.remove(0);
        String s = this.rangeOfNotes.get(rangeOfNotes.size() - 1);

        Pitch p = notePitch(s);
        int o = noteOctave(s);
        Note n;

        if (p.equals(Pitch.B)) {
            n = new Note(0, 1, o + 1, Pitch.C, 1, 65);
        } else {
            n = new Note(0, 1, o, Pitch.values()[p.ordinal() + 1], 1, 65);
        }

        this.rangeOfNotes.add(n.toString());
    }

    public void shiftRangeDown() {
        this.rangeOfNotes.remove(rangeOfNotes.size() - 1);
        String s = this.rangeOfNotes.get(0);

        Pitch p = notePitch(s);
        int o = noteOctave(s);
        Note n;

        if (p.equals(Pitch.C)) {
            n = new Note(0, 1, o - 1, Pitch.B, 1, 65);
        } else {
            n = new Note(0, 1, o, Pitch.values()[p.ordinal() - 1], 1, 65);
        }

        this.rangeOfNotes.add(0, n.toString());
    }

    public boolean noteAtLocation(Point loc) {
        List<NoteRep> notes = model.getAllNotes();
        int xInit = BEAT_WIDTH + (SIDE_WIDTH + 5);

        //todo
//        for (NoteRep n : notes) {
//            int noteY = calculateY(n);
//            int x1, x2, y1, y2;
//
//            if (noteY <= NOTE_HEIGHT / 2) continue;
//            if ((n.getStart() - xWinStart * 4) >= 0) {
//                x1 = (n.getStart() - xWinStart * 4) * BEAT_WIDTH + xInit;
//                y1 = noteY;
//                x2 = BEAT_WIDTH * n.getDuration();
//                y2 = NOTE_HEIGHT;
//
//                boolean withinX = loc.getX() <= x2 && loc.getX() >= x1;
//                boolean withinY = loc.getY() <= y2 && loc.getY() >= y1;
//                return withinX && withinY;
//            } else if (n.getDuration() + n.getStart() > xWinStart * 4) {
//                x1 = xInit;
//                y1 = noteY;
//                x2 = BEAT_WIDTH * (n.getDuration() + n.getStart() - xWinStart * 4);
//                y2 = NOTE_HEIGHT;
//
//                boolean withinX = loc.getX() <= x2 && loc.getX() >= x1;
//                boolean withinY = loc.getY() <= y2 && loc.getY() >= y1;
//                return withinX && withinY;
//            }
//        }

        return false;
    }

    public NoteRep getNoteAtLocation(Point loc) { //todo
        int start;
        int duration;
        int octave;
        Pitch p;

        String high = rangeOfNotes.get(rangeOfNotes.size() - 1);
        int o2 = noteOctave(high);
        Pitch p2 = notePitch(high);

//        int ret = (octave - n.getOctave()) * 12 + p.ordinal() - n.getPitch().ordinal();
//        ret =  ret * NOTE_HEIGHT + NOTE_HEIGHT;

        Note n = new Note(start, duration, octave, p, 1, 65);
        return new Note(start, duration, octave, p, 1, 65);
    }
}
