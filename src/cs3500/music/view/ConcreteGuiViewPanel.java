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
    private SongRep model;                //TODO should this go in the guiviewframe or here?  I feel like this class needs it to draw

    public ConcreteGuiViewPanel(SongRep model) {
        super();
        this.model = model;
    }

    public ConcreteGuiViewPanel() {
        super();
        this.model = new GenericSong();                                          //TODO what else can this be initialized as?
    }

    @Override
    public void paintComponent(Graphics g){
        // Look for more documentation about the Graphics class,
        // and methods on it that may be useful
//        g.drawString("Hello World", 25, 25);
        List<String> range = model.getRange();
        int i = 10;
        for (String s : range) {
            g.drawString(s, 10, i);
            i += 10;
        }
    }

}
