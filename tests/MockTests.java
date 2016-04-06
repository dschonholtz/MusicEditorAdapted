import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.*;
import cs3500.music.util.MockSynth;
import cs3500.music.view.MidiViewImpl;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * All testing having to do with mocks
 */
public class MockTests {
    private String ranKB = ""; // to assist in testing keyboard handler
    private String ranM = ""; // to assist in testing mouse handler

    @Test
    public void testKeyboardHandler() {
        KeyboardHandler kh = new KeyboardHandler();
        Map<Integer, Runnable> keyPresses = new HashMap<>();
        Map<Integer, Runnable> keyTyped = new HashMap<>();
        Map<Integer, Runnable> keyReleases = new HashMap<>();

        keyPresses.put(5, () -> testKBHelper("Pressed 5"));
        keyPresses.put(10, () -> testKBHelper("Pressed 10"));
        keyTyped.put(5, () -> testKBHelper("Typed 5"));
        keyTyped.put(10, () -> testKBHelper("Typed 10"));
        keyReleases.put(5, () -> testKBHelper("Released 5"));
        keyReleases.put(10, () -> testKBHelper("Released 10"));

        kh.setKeyPressedMap(keyPresses);
        kh.setKeyTypedMap(keyTyped);
        kh.setKeyReleasedMap(keyReleases);

        KeyEvent test1 = new KeyEvent(new Button("test"), 2, 2, 2, 5, ' ');
        KeyEvent test2 = new KeyEvent(new Button("test"), 2, 2, 2, 10, ' ');

        kh.keyPressed(test1);
        assertEquals(ranKB, "Pressed 5");
        kh.keyReleased(test1);
        assertEquals(ranKB, "Released 5");
        kh.keyTyped(test2);
        assertEquals(ranKB, "Typed 10");
        kh.keyReleased(test2);
        assertEquals(ranKB, "Released 10");
        kh.keyTyped(test1);
        assertEquals(ranKB, "Typed 5");
        kh.keyPressed(test2);
        assertEquals(ranKB, "Pressed 10");
    }

    @Test
    public void testMouseHandler() {
        MouseHandler mh = new MouseHandler();
        mh.setClickEvent(MouseEvent.BUTTON1, () -> testMouseHelper("Left click!"));
        mh.setClickEvent(MouseEvent.BUTTON3, () -> testMouseHelper("Right click!"));
        mh.setPressEvent(MouseEvent.BUTTON1, () -> testMouseHelper("Left press!"));
        mh.setReleaseEvent(MouseEvent.BUTTON3, () -> testMouseHelper("Right release!"));

        MouseEvent test1 = new MouseEvent(new Button("test"), 1, 1, 1, 10, 10, 1,
                false, MouseEvent.BUTTON1);
        MouseEvent test2 = new MouseEvent(new Button("test"), 1, 1, 1, 10, 10, 1,
                false, MouseEvent.BUTTON3);

        mh.mouseClicked(test1);
        assertEquals(ranM, "Left click!");
        mh.mousePressed(test1);
        assertEquals(ranM, "Left press!");
        mh.mousePressed(test2);
        assertEquals(ranM, "Left press!");
        mh.mouseReleased(test2);
        assertEquals(ranM, "Right release!");
        mh.mouseClicked(test2);
        assertEquals(ranM, "Right click!");
    }

    /**
     * Used to test if the correct runnable was run
     * @param s the string to identify the runnable
     */
    private void testKBHelper(String s) {
        ranKB = s;
    }

    /**
     * Used to test if the correct runnable was run
     * @param s the string to identify the runnable
     */
    private void testMouseHelper(String s) {
        ranM = s;
    }

    //todo
    @Test
    public void testMockMidi() {
        NoteRep n1 = new Note(0, 4, 4, Pitch.C, 1, 65);
        NoteRep n2 = new Note(3, 5, 5, Pitch.B, 1, 65);
        NoteRep n3 = new Note(10, 1, 3, Pitch.AS, 1, 65);
        ArrayList<NoteRep> notes = new ArrayList<>(Arrays.asList(n1, n2, n3));
        SongRep song1 = new GenericSong(notes, 200000);
        MidiViewImpl mvi = new MidiViewImpl(song1, new MockSynth());

        for (int i = 0; i <= song1.getLength(); i++) {
            mvi.run();
            song1.setCurrentBeat(song1.getBeat() + 1);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        mvi.close();
        System.out.flush();
        System.setOut(old);



        assertEquals("note 60 ON at beat 0\n" +
                "note 60 OFF at beat 4\n" +
                "note 83 ON at beat 3\n" +
                "note 83 OFF at beat 8\n" +
                "note 58 ON at beat 10\n" +
                "note 58 OFF at beat 11\n" +
                "\n", baos.toString());
        System.out.println(baos.toString());
    }
}
