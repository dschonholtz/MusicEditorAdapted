package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.NoteRep;
import cs3500.music.model.SongRep;
import javafx.geometry.Side;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

/**
 * Panel that actually draws the music editor gui
 */
public class ConcreteGuiViewPanel extends JPanel {
    private SongRep model;                //TODO should this go in the guiviewframe or here?  I feel like this class needs
    private List<String> rangeOfNotes;            //TODO all this information to draw stuff.  but i'm not really sure wh'at sgoing on
    private int songLength;
    public static final int BEAT_WIDTH = 20; // in pixels
    public static final int NOTE_HEIGHT = 20; // in pixels

    public ConcreteGuiViewPanel(SongRep model) {
        super();
        this.model = model;
        this.rangeOfNotes = model.getRange();
        this.songLength = model.getLength();
    }

    public ConcreteGuiViewPanel() {
        super();
        this.model = new GenericSong();                                          //TODO what else can this be initialized as?
        this.rangeOfNotes = new ArrayList<>();
        this.songLength = 0;
    }

    @Override
    public void paintComponent(Graphics g){
        int count = 0;
        int SideWidth = 10;

        int xInit = BEAT_WIDTH + (SideWidth + 5);

        //top line
        g.drawLine(xInit, NOTE_HEIGHT, songLength * BEAT_WIDTH, NOTE_HEIGHT);

        //for (String s : rangeOfNotes) {
        for(int i = rangeOfNotes.size() - 1; i >= 0; i--) {
            String s = rangeOfNotes.get(i);
            // write out the note names on the left column
            int y = count * NOTE_HEIGHT + NOTE_HEIGHT * 2;
            g.drawString(s, SideWidth, y);
            // draw the lines for where the notes go
            g.drawLine(xInit, y, songLength * BEAT_WIDTH, y);

            count++;
        }

        for (int j = 0; j <= songLength; j++) {
            int xValue = (j + 1) * BEAT_WIDTH + SideWidth + 5;
            if (j % 16 == 0) { // label every 16th beat / 4 measures
                g.drawString(Integer.toString(j), xValue,  NOTE_HEIGHT);
            }
            if (j % 4 == 0) { // draw lines separating every 4th beat / 1 measure
                g.drawLine(xValue, NOTE_HEIGHT, xValue, (rangeOfNotes.size() + 1) * NOTE_HEIGHT);
            }
        }

        //Draw the notes themselves
        List<NoteRep> notes = model.getAllNotes();
        for(NoteRep n : notes) {
            //model.
            int noteY = NOTE_HEIGHT; // Calculate difference between n and lowest note add appropriate constant
            g.drawRect(n.getStart() * BEAT_WIDTH + xInit, noteY, BEAT_WIDTH * n.getDuration(), NOTE_HEIGHT);
            g.drawRect(n.getStart() * BEAT_WIDTH + xInit, noteY, BEAT_WIDTH, NOTE_HEIGHT);


        }

    }

    @Override
    public Dimension getPreferredSize() {
        int width = songLength * BEAT_WIDTH; //TODO whyd oes this need to be multiplied by 4? something is wrong
        System.out.println(songLength);
        int height = (rangeOfNotes.size() * NOTE_HEIGHT*2 + NOTE_HEIGHT * 7);
        return new Dimension(width, height); //TODO calculate this better.  look up scroll bars?
    }
}
