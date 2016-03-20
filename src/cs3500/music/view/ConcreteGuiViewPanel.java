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
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {
    private SongRep model;                //TODO should this go in the guiviewframe or here?  I feel like this class needs
    List<String> rangeOfNotes;            //TODO all this information to draw stuff.  but i'm not really sure wh'at sgoing on
    int songLength;

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
        // Look for more documentation about the Graphics class,
        // and methods on it that may be useful
//        g.drawString("Hello World", 25, 25);
        int i = 60;
        for (String s : rangeOfNotes) {
            g.drawString(s, 10, i);
            i += 30;
        }

        for (i = 0; i <= songLength; i++) {
            if (i % 16 == 0) {
                g.drawString(Integer.toString(i), 60 + (i * 15), 10);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = songLength * 30;
        int height = 90 + (rangeOfNotes.size() * 30);
        return new Dimension(width, height);
    }
}
