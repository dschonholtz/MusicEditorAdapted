/**
 * Created by dschonholtz on 4/6/2016.
 */
import cs3500.music.controller.Controller;
import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
import cs3500.music.model.SongRep;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    @Test
    public void testController() {
        SongRep s = new GenericSong();
        s.addNote(new Note());
        Controller c = new Controller(s);
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
        Controller c = new Controller(s);
        c.changePlayState(); // paused
        c.run();
        c.incrementBeat();
        assertEquals(1, s.getBeat());
        c.jumpTo(20);
        assertEquals(20, s.getBeat());
        c.jumpTo(-1);
    }

}
