import cs3500.music.model.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/** Tests for the {@link GenericSong} class */
public class GenericSongTest {
    @Test (expected = NullPointerException.class)
    public void testConstructorNotesNonNull() {
        new GenericSong(null);
    }

    @Test
    public void testCurrentBeat() {
        SongRep gs1 = new GenericSong();
        assertEquals(0, gs1.getBeat());

        gs1.setCurrentBeat(5);
        assertEquals(5, gs1.getBeat());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetCurrentBeatNonNegative() {
        SongRep gs1 = new GenericSong();
        gs1.setCurrentBeat(-6);
    }

    @Test
    public void testAddNote() {
        SongRep gs1 = new GenericSong();
        assertEquals(0, gs1.getNotes().size());
        gs1.addNote(new Note());
        assertEquals(1, gs1.getNotes().size());
        gs1.addNote(new Note(5, 4, 3, Pitch.C));
        assertEquals(2, gs1.getNotes().size());
        gs1.addNote(new Note(5, 4, 3, Pitch.C));
        assertEquals(2, gs1.getNotes().size());
        gs1.addNote(new Note(6, 4, 3, Pitch.C)); //overlapping note won't be added
        assertEquals(2, gs1.getNotes().size());
        gs1.addNote(new Note(10, 4, 3, Pitch.C));
        assertEquals(3, gs1.getNotes().size()); //consecutive notes will be added
    }

    @Test (expected = NullPointerException.class)
    public void testAddNoteNonNull() {
        SongRep gs1 = new GenericSong();
        gs1.addNote(null);
    }

    @Test
    public void testRemoveNote() {
        SongRep gs1 = new GenericSong();
        gs1.addNote(new Note());
        gs1.addNote(new Note(5, 4, 3, Pitch.C));
        gs1.addNote(new Note(5, 4, 3, Pitch.D));
        assertEquals(3, gs1.getNotes().size());
        gs1.removeNote(new Note(5, 4, 3, Pitch.C));
        assertEquals(2, gs1.getNotes().size());
        gs1.removeNote(new Note(3, 3, 3, Pitch.E));
        assertEquals(2, gs1.getNotes().size());
    }

    @Test (expected = NullPointerException.class)
    public void testRemoveNoteNonNull() {
        SongRep gs1 = new GenericSong();
        gs1.removeNote(null);
    }

    @Test
    public void testGetNotes() {
        SongRep gs1 = new GenericSong();
        assertEquals(new ArrayList<NoteRep>(), gs1.getNotes());
        gs1.addNote(new Note());
        gs1.addNote(new Note(5, 4, 3, Pitch.B));
        assertEquals(new ArrayList<NoteRep>(Arrays.asList(new Note(), new Note(5, 4, 3, Pitch.B))),
                gs1.getNotes());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testGetNotesUnmodifiable() {
        SongRep gs1 = new GenericSong();
        gs1.addNote(new Note());
        gs1.getNotes().add(new Note(5, 4, 3, Pitch.F));
    }

    @Test
    public void testCombineSimultaneously() {
        SongRep gs1 = new GenericSong();
        SongRep gs2 = new GenericSong();

        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.G);
        Note n3 = new Note(1, 2, 3, Pitch.F);

        gs1.addNote(n1);
        gs1.addNote(n2);

        gs2.addNote(n1);
        gs2.addNote(n3);

        gs1.combineSimultaneously(gs2);
        assertEquals(new ArrayList<NoteRep>(Arrays.asList(n1, n2, n3)), gs1.getNotes());
        assertEquals(new ArrayList<NoteRep>(Arrays.asList(n1, n3)), gs2.getNotes());
    }

    @Test (expected = NullPointerException.class)
    public void testCombineSimultaneousNonNull() {
        SongRep gs1 = new GenericSong();
        gs1.combineSimultaneously(null);
    }

    @Test
    public void testCombineConsecutively() {
        SongRep gs1 = new GenericSong();
        SongRep gs2 = new GenericSong();

        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.G);
        Note n3 = new Note(1, 2, 3, Pitch.F);
        Note n4 = new Note();

        gs1.addNote(n1);
        gs1.addNote(n2);

        gs2.addNote(n4);
        gs2.addNote(n3);

        gs1.combineConsecutively(gs2);
        assertEquals(new ArrayList<NoteRep>(Arrays.asList(n1, n2, new Note(8, 1, 4, Pitch.C),
                new Note(9, 2, 3, Pitch.F))), gs1.getNotes());
    }

    @Test (expected = NullPointerException.class)
    public void testCombineConsecutiveNonNull() {
        SongRep gs1 = new GenericSong();
        gs1.combineConsecutively(null);
    }

    @Test
    public void testGetState() {
        SongRep maryHadALittleLamb = new GenericSong();
        assertEquals("This song is empty", maryHadALittleLamb.getState());

        // melody
        maryHadALittleLamb.addNote(new Note(0, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(2, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(4, 2, 4, Pitch.C));
        maryHadALittleLamb.addNote(new Note(6, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(8, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(10, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(12, 3, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(16, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(18, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(20,4, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(24, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(26, 2, 4, Pitch.G));
        maryHadALittleLamb.addNote(new Note(28, 4, 4, Pitch.G));
        maryHadALittleLamb.addNote(new Note(32, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(34, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(36, 2, 4, Pitch.C));
        maryHadALittleLamb.addNote(new Note(38, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(40, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(42, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(44, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(46, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(48, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(50, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(52, 2, 4, Pitch.E));
        maryHadALittleLamb.addNote(new Note(54, 2, 4, Pitch.D));
        maryHadALittleLamb.addNote(new Note(56, 8, 4, Pitch.C));

        // bass
        maryHadALittleLamb.addNote(new Note(0, 7, 3, Pitch.G));
        maryHadALittleLamb.addNote(new Note(8, 7, 3, Pitch.G));
        maryHadALittleLamb.addNote(new Note(16, 8, 3, Pitch.G));
        maryHadALittleLamb.addNote(new Note(24, 2, 3, Pitch.G));
        maryHadALittleLamb.addNote(new Note(32, 8, 3, Pitch.G));
        maryHadALittleLamb.addNote(new Note(40, 8, 3, Pitch.G));
        maryHadALittleLamb.addNote(new Note(48, 8, 3, Pitch.G));
        maryHadALittleLamb.addNote(new Note(56, 8, 3, Pitch.E));

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
                "         ",
                maryHadALittleLamb.getState());
    }

    @Test
    public void testGetStateDoubleNotes() {
        SongRep test = new GenericSong();
        test.addNote(new Note());
        test.addNote(new Note());

        assertEquals("    C4\n" +
                     " 0  X  ", test.getState());
    }

    @Test
    public void testGetStateManyShortNotes() {
        SongRep test = new GenericSong();
        test.addNote(new Note(0, 1, 4, Pitch.AS));
        test.addNote(new Note(1, 1, 4, Pitch.AS));
        test.addNote(new Note(2, 1, 4, Pitch.AS));
        test.addNote(new Note(3, 1, 4, Pitch.AS));
        test.addNote(new Note(2, 2, 4, Pitch.E));

        assertEquals("    E4   F4  F#4   G4  G#4   A4  A#4\n" +
                     " 0                                X  \n" +
                     " 1                                X  \n" +
                     " 2  X                             X  \n" +
                     " 3  |                             X  ", test.getState());
    }
}
