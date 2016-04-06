/**
 * Created by dschonholtz on 4/6/2016.
 */
import cs3500.music.controller.CompositeController;
import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
import cs3500.music.model.SongRep;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompositeControllerTest {

    @Test
    public void testController() {
        SongRep s = new GenericSong();
        s.addNote(new Note());
        CompositeController c = new CompositeController(s);
        c.changePlayState(); // paused
        c.run();
        c.incrementBeat();
        assertEquals(1, s.getBeat());
        c.jumpTo(20);
        assertEquals(20, s.getBeat());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testControllerException() {
        SongRep s = new GenericSong();
        s.addNote(new Note());
        CompositeController c = new CompositeController(s);
        c.changePlayState(); // paused
        c.run();
        c.incrementBeat();
        assertEquals(1, s.getBeat());
        c.jumpTo(20);
        assertEquals(20, s.getBeat());
        c.jumpTo(-1);
    }

}
