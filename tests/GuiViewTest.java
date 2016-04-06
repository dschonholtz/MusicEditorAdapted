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
    public void test() {

    }
}
