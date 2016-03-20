package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.SongRep;

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
    public static final int BEAT_WIDTH = 5; // in pixels
    public static final int NOTE_HEIGHT = 10; // in pixels

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
        for (String s : rangeOfNotes) {
            // write out the note names on the left column

            // draw the lines for where the notes go

        }

        for (int j = 0; j <= songLength; j++) {
            int xValue = (j + 1) * BEAT_WIDTH * 4;
            if (j % 16 == 0) { // label every 16th beat / 4 measures
                g.drawString(Integer.toString(j), xValue,  NOTE_HEIGHT);
            }
            if (j % 4 == 0) { // draw lines separating every 4th beat / 1 measure
                g.drawLine(xValue, NOTE_HEIGHT * 2, xValue, (rangeOfNotes.size() + 1) * NOTE_HEIGHT * 2);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = songLength * BEAT_WIDTH * 4;
        System.out.println(songLength);
        int height = (rangeOfNotes.size() * NOTE_HEIGHT);
        return new Dimension(width, height); //TODO calculate this better.  look up scroll bars?
    }
}
