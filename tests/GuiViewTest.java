import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
import cs3500.music.model.SongRep;
import cs3500.music.util.SongFactory;
import cs3500.music.view.ConcreteGuiViewPanel;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertEquals;

/**
 * To test the views for the music editor
 */
public class GuiViewTest {

    @Test
    public void testGetPreferredSize() {
        SongRep gs1 = new GenericSong();
        gs1.addNote(new Note());
        JPanel guiView = new ConcreteGuiViewPanel(gs1);
        assertEquals(guiView.getPreferredSize(), new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height));
    }

    @Test
    public void testCurrentBeatX() {
        try {
            SongRep song = new SongFactory().buildSong("mary-little-lamb.txt");
            ConcreteGuiViewPanel gui = new ConcreteGuiViewPanel(song);
            assertEquals(45, gui.currentBeatX());
            gui.scrollRight();
            assertEquals(-35, gui.currentBeatX());
            gui.scrollLeft();
            gui.scrollLeft();
            assertEquals(45, gui.currentBeatX());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShiftRangeUp() {
        try {
            SongRep song = new SongFactory().buildSong("mary-little-lamb.txt");
            ConcreteGuiViewPanel gui = new ConcreteGuiViewPanel(song);
            gui.shiftRangeUp();
            assertEquals("[E3, F3, F#3, G3, G#3, A3, A#3, B3, C4, C#4, D4, D#4, E4, F4, F#4, G4]",
                    " ");
            gui.shiftRangeUp();
            gui.shiftRangeUp();
            gui.shiftRangeUp();
            gui.shiftRangeUp();
            gui.shiftRangeUp();
            gui.shiftRangeUp();
            assertEquals("Yeah this is all wrong ",
                    song.getRange().toString());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShiftRangeDown() {

    }

    @Test
    public void testNoteAtLocation() {

    }

    @Test
    public void testGetNoteAtLocation() {

    }
}
