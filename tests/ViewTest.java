import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.SongRep;
import cs3500.music.view.ConcreteGuiViewPanel;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

/**
 * To test the views for the music editor
 */
public class ViewTest {

    @Test
    public void testGetPreferredSize() {
        SongRep gs1 = new GenericSong();
        gs1.addNote(new Note());
        gs1.addNote(new Note(5, 4, 3,Pitch.C, 3, 65));
        gs1.addNote(new Note(5, 4, 3, Pitch.D, 3, 65));
        JPanel guiView = new ConcreteGuiViewPanel(gs1);

        assertEquals(guiView.getPreferredSize(), new Dimension(200, 340));

        gs1.addNote(new Note(10, 50, 1, Pitch.D, 3, 65));
        //THIS LINE IS NECESSARY THEY ARE NOT CONNECTED BY REFERENCE... WHY????
        guiView = new ConcreteGuiViewPanel(gs1);
        assertEquals(guiView.getPreferredSize(), new Dimension(1280, 780));
    }
}