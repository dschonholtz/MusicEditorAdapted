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
    public static final int BEAT_WIDTH = 20; // in pixels
    public static final int NOTE_HEIGHT = 20; // in pixels
    public int xWinStart; //TODO ARI LOOK HERE! I tried to use accessors but this is declared as a jpanel so
    //TODO they weren't visible.... But this is our global variable that will move us left and right.

    public ConcreteGuiViewPanel(SongRep model) {
        super();
        this.model = model;
        this.rangeOfNotes = model.getRange();
        this.songLength = model.getLength();
        this.xWinStart = 2;
    }

    public ConcreteGuiViewPanel() {
        super();
        this.model = new GenericSong();   //The model will go here
        this.rangeOfNotes = new ArrayList<>();
        this.songLength = 0;
        this.xWinStart = 2; // number of measures scrolled to right from zero
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int count = 0;
        int SideWidth = 10;

        int xInit = BEAT_WIDTH + (SideWidth + 5);
        //Draw the notes themselves
        List<NoteRep> notes = model.getAllNotes();

        for(NoteRep n : notes) {
            int noteY = calculateY(n);

            if((n.getStart() - xWinStart * 4) >= 0) {
                g.setColor(Color.CYAN);
                g.fillRect((n.getStart() - xWinStart * 4) * BEAT_WIDTH + xInit, noteY, BEAT_WIDTH * n.getDuration(),
                        NOTE_HEIGHT);
                g.setColor(Color.BLACK);
                g.fillRect((n.getStart() - xWinStart * 4) * BEAT_WIDTH + xInit, noteY, BEAT_WIDTH, NOTE_HEIGHT);
            }

            // TODO notes before hand aren't being
            // TODO rendered please help me figure out how to make the seeable this case almost does it....
            else if ((n.getDuration() - (n.getStart() - xWinStart * 4)) > 0) { // if the duration is > then the difference between the initial start
                                                                                // and the actual
                g.setColor(Color.CYAN);
                g.fillRect(xInit, noteY, BEAT_WIDTH * (n.getDuration() - (n.getStart() - xWinStart * 4)),
                        NOTE_HEIGHT);
                // dont draw the initial beat as it would be handled in the first if.
                // Draw the beat starting at the initial space. draw it the length of its duration - the difference in its start and the actual start
            }


        }

        //top line
        g.drawLine(xInit, NOTE_HEIGHT, ((songLength + (songLength % 4)) * BEAT_WIDTH) - 5,
                NOTE_HEIGHT);

        for(int i = rangeOfNotes.size() - 1; i >= 0; i--) {
            String s = rangeOfNotes.get(i);
            // write out the note names on the left column
            int y = count * NOTE_HEIGHT + NOTE_HEIGHT * 2;
            g.drawString(s, SideWidth, y - 5);
            // draw the lines for where the notes go
            g.drawLine(xInit, y, ((songLength + (songLength % 4)) * BEAT_WIDTH) - 5, y);

            count++;
        }

        for (int j = 0; j <= songLength + (songLength % 4); j++) {
            int xValue = (j + 1) * BEAT_WIDTH + SideWidth + 5 - xWinStart * 4 * BEAT_WIDTH;
            if (j % 16 == 0) { // label every 16th beat / 4 measures
                g.drawString(Integer.toString(j), xValue,  NOTE_HEIGHT);
            }
            if (j % 4 == 0) { // draw lines separating every 4th beat / 1 measure
                g.drawLine(xValue, NOTE_HEIGHT, xValue, (rangeOfNotes.size() + 1) * NOTE_HEIGHT);
            }
        }

        // red time line
        g.setColor(Color.RED);
        if(((model.getBeat() + 1) - xWinStart * 4) > 0) { // doesn't draw line when the line isn't in view :)
            g.drawLine(((model.getBeat() + 1) - xWinStart * 4) * BEAT_WIDTH + SideWidth + 5, NOTE_HEIGHT,
                    ((model.getBeat() + 1) - xWinStart * 4) * BEAT_WIDTH + SideWidth + 5,
                    NOTE_HEIGHT + NOTE_HEIGHT * rangeOfNotes.size());
        }

    }

    private int calculateY(NoteRep n) {
        String high = rangeOfNotes.get(rangeOfNotes.size()-1);
        int octave;
        Pitch p;
        if(high.charAt(1) == '#') {
            p = Pitch.valueOf(high.substring(0,1) + "S");
            octave = Integer.valueOf(high.substring(2));
        }
        else {
            p = Pitch.valueOf(high.substring(0,1));
            octave = Integer.valueOf(high.substring(1));
        }

        int ret = (octave - n.getOctave()) * 12 + p.ordinal() - n.getPitch().ordinal();
        ret =  ret * NOTE_HEIGHT + NOTE_HEIGHT;

        return ret;
    }

    @Override
    public Dimension getPreferredSize() {
        int width = ((songLength + (songLength % 4)) * BEAT_WIDTH) + BEAT_WIDTH*2;
        int height = (rangeOfNotes.size() * NOTE_HEIGHT + NOTE_HEIGHT * 4);
        return new Dimension(width, height);
    }

}
