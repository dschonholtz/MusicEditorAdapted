import cs3500.music.controller.*;
import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
import cs3500.music.model.SongRep;
import cs3500.music.util.SongFactory;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for controllers
 */
public class ControllerTest {

    @Test
    public void testCompositeController1() {
        SongRep s = new GenericSong();
        s.addNote(new Note());
        CompositeController c = new CompositeController(s);
        assertEquals(c.isPlaying(), true);
        c.changePlayState(); // paused
        assertEquals(c.isPlaying(), false);
        c.run();
        c.incrementBeat();
        assertEquals(1, s.getBeat());

    }

    @Test
    public void testCompositeController2() {
        SongRep s = new GenericSong();
        s.addNote(new Note());
        CompositeController c = new CompositeController(s);
        c.changePlayState(); // paused
        c.run();
        c.incrementBeat();
        assertEquals(1, s.getBeat());
    }

    @Test (expected = NullPointerException.class)
    public void testConsoleControllerNull() {
        new ConsoleController(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCompositeControllerDefault() {
        new CompositeController();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testControllerFactoryDefault() {
        new ControllerFactory("breakme", new GenericSong()).build();
    }

    @Test (expected = NullPointerException.class)
    public void testControllerFactoryNullSong() {
        new ControllerFactory("breakme", null).build();
    }

    @Test (expected = NullPointerException.class)
    public void testControllerFactoryNullType() {
        new ControllerFactory(null, new GenericSong()).build();
    }

    @Test
    public void testControllerFactoryBuildReturn() {
        GenericSong gs = new GenericSong();
        gs.addNote(new Note());
        assertTrue(new ControllerFactory("visual", gs).build() instanceof GuiController);
        assertTrue(new ControllerFactory("midi", gs).build() instanceof MidiController);
        assertTrue(new ControllerFactory("console", gs).build() instanceof ConsoleController);
        assertTrue(new ControllerFactory("composite", gs).build() instanceof CompositeController);
    }

    @Test (expected = NullPointerException.class)
    public void testGuiControllerNonNull() {
        new GuiController(null);
    }

    @Test (expected = NullPointerException.class)
    public void testKHTypedNull() {
        new KeyboardHandler().keyTyped(null);
    }

    @Test (expected = NullPointerException.class)
    public void testKHPressedNull() {
        new KeyboardHandler().keyPressed(null);
    }

    @Test (expected = NullPointerException.class)
    public void testKHReleasedNull() {
        new KeyboardHandler().keyReleased(null);
    }

    @Test (expected = NullPointerException.class)
    public void testKHSetTypeNull() {
        new KeyboardHandler().setKeyTypedMap(null);
    }

    @Test (expected = NullPointerException.class)
    public void testKHSetPressNull() {
        new KeyboardHandler().setKeyPressedMap(null);
    }

    @Test (expected = NullPointerException.class)
    public void testKHSeyReleaseNull() {
        new KeyboardHandler().setKeyReleasedMap(null);
    }

    @Test (expected = NullPointerException.class)
    public void testMidiControllerNull() {
        new MidiController(null);
    }

    @Test (expected = NullPointerException.class)
    public void testMouseClickedNull() {
        new MouseHandler().mouseClicked(null);
    }

    @Test (expected = NullPointerException.class)
    public void testMousePressedNull() {
        new MouseHandler().mousePressed(null);
    }

    @Test (expected = NullPointerException.class)
    public void testMouseReleasedNull() {
        new MouseHandler().mouseReleased(null);
    }

    @Test (expected = NullPointerException.class)
    public void testMouseEnteredNull() {
        new MouseHandler().mouseEntered(null);
    }

    @Test (expected = NullPointerException.class)
    public void testMouseExitNull() {
        new MouseHandler().mouseExited(null);
    }

    @Test (expected = NullPointerException.class)
    public void testMouseSetClickedNull() {
        new MouseHandler().setClickEvent(1, null);
    }

    @Test (expected = NullPointerException.class)
    public void testMouseSetPressNull() {
        new MouseHandler().setPressEvent(1, null);
    }

    @Test (expected = NullPointerException.class)
    public void testMouseSetReleaseNull() {
        new MouseHandler().setReleaseEvent(1, null);
    }

    @Test (expected = NullPointerException.class)
    public void testSetMouseEnterNull() {
        new MouseHandler().setMouseEnter(null);
    }

    @Test (expected = NullPointerException.class)
    public void testSetMouseExit() {
        new MouseHandler().setMouseExit(null);
    }

    @Test
    public void testConsoleViewOutput() {
        StringBuilder log = new StringBuilder();
        try {
            new ConsoleView(new SongFactory().buildSong("mary-little-lamb.txt"), log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("     E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4" +
                "   F4  F#4" +
                "   G4\n" +
                "  0                 X                                            X       " +
                "          \n" +
                "  1                 |                                            |       " +
                "          \n" +
                "  2                 |                                  X                 " +
                "          \n" +
                "  3                 |                                  |                 " +
                "          \n" +
                "  4                 |                        X                           " +
                "          \n" +
                "  5                 |                        |                           " +
                "          \n" +
                "  6                 |                                  X                 " +
                "          \n" +
                "  7                                                    |                 " +
                "          \n" +
                "  8                 X                                            X       " +
                "          \n" +
                "  9                 |                                            |       " +
                "          \n" +
                " 10                 |                                            X       " +
                "          \n" +
                " 11                 |                                            |       " +
                "          \n" +
                " 12                 |                                            X       " +
                "          \n" +
                " 13                 |                                            |       " +
                "          \n" +
                " 14                 |                                            |       " +
                "          \n" +
                " 15                                                                      " +
                "          \n" +
                " 16                 X                                  X                  " +
                "         \n" +
                " 17                 |                                  |                  " +
                "         \n" +
                " 18                 |                                  X                  " +
                "         \n" +
                " 19                 |                                  |                  " +
                "         \n" +
                " 20                 |                                  X                  " +
                "         \n" +
                " 21                 |                                  |                  " +
                "         \n" +
                " 22                 |                                  |                  " +
                "         \n" +
                " 23                 |                                  |                  " +
                "         \n" +
                " 24                 X                                            X        " +
                "         \n" +
                " 25                 |                                            |        " +
                "         \n" +
                " 26                                                                       " +
                "      X  \n" +
                " 27                                                                       " +
                "      |  \n" +
                " 28                                                                       " +
                "      X  \n" +
                " 29                                                                       " +
                "      |  \n" +
                " 30                                                                       " +
                "      |  \n" +
                " 31                                                                       " +
                "      |  \n" +
                " 32                 X                                            X        " +
                "         \n" +
                " 33                 |                                            |        " +
                "         \n" +
                " 34                 |                                  X                  " +
                "         \n" +
                " 35                 |                                  |                  " +
                "         \n" +
                " 36                 |                        X                            " +
                "         \n" +
                " 37                 |                        |                            " +
                "         \n" +
                " 38                 |                                  X                  " +
                "         \n" +
                " 39                 |                                  |                  " +
                "         \n" +
                " 40                 X                                            X        " +
                "         \n" +
                " 41                 |                                            |        " +
                "         \n" +
                " 42                 |                                            X        " +
                "         \n" +
                " 43                 |                                            |        " +
                "         \n" +
                " 44                 |                                            X        " +
                "         \n" +
                " 45                 |                                            |        " +
                "         \n" +
                " 46                 |                                            X        " +
                "         \n" +
                " 47                 |                                            |        " +
                "         \n" +
                " 48                 X                                  X                  " +
                "         \n" +
                " 49                 |                                  |                  " +
                "         \n" +
                " 50                 |                                  X                  " +
                "         \n" +
                " 51                 |                                  |                  " +
                "         \n" +
                " 52                 |                                            X        " +
                "         \n" +
                " 53                 |                                            |        " +
                "         \n" +
                " 54                 |                                  X                  " +
                "         \n" +
                " 55                 |                                  |                  " +
                "         \n" +
                " 56  X                                       X                            " +
                "         \n" +
                " 57  |                                       |                            " +
                "         \n" +
                " 58  |                                       |                            " +
                "         \n" +
                " 59  |                                       |                            " +
                "         \n" +
                " 60  |                                       |                            " +
                "         \n" +
                " 61  |                                       |                            " +
                "         \n" +
                " 62  |                                       |                            " +
                "         \n" +
                " 63  |                                       |                            " +
                "         ", log.toString());
    }
}
