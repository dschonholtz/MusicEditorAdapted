import cs3500.music.model.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/** Tests for the {@link Note} class */
public class NoteTest {
    @Test (expected = IllegalArgumentException.class)
    public void testNoteOctaveTooHighException() {
        new Note(5, 10, 100, Pitch.CS);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNoteOctaveNegativeException() {
        new Note(2, 3, -1, Pitch.D);
    }

    @Test (expected = NullPointerException.class)
    public void testNotePitchNonNull() {
        new Note(2, 3, 4, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNoteZeroDuration() {
        new Note(2, 0, 3, Pitch.C);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNoteNegativeStart() { new Note(-1, 2, 3, Pitch.D); }

    @Test
    public void testGetStart() {
        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(10, 2, 4, Pitch.C);

        assertEquals(0, n1.getStart());
        assertEquals(5, n2.getStart());
        assertEquals(10, n3.getStart());
    }

    @Test
    public void testGetDuration() {
        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(10, 2, 4, Pitch.C);

        assertEquals(1, n1.getDuration());
        assertEquals(4, n2.getDuration());
        assertEquals(2, n3.getDuration());
    }

    @Test
    public void testGetPitch() {
        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(10, 2, 4, Pitch.CS);

        assertEquals(Pitch.C, n1.getPitch());
        assertEquals(Pitch.D, n2.getPitch());
        assertEquals(Pitch.CS, n3.getPitch());
    }

    @Test
    public void testGetOctave() {
        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(10, 2, 4, Pitch.CS);

        assertEquals(4, n1.getOctave());
        assertEquals(3, n2.getOctave());
        assertEquals(4, n3.getOctave());
    }

    @Test
    public void testGetEnd() {
        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(10, 2, 4, Pitch.CS);
        Note n4 = new Note(0, 5, 4, Pitch.B);

        assertEquals(0, n1.getEnd());
        assertEquals(8, n2.getEnd());
        assertEquals(11, n3.getEnd());
        assertEquals(4, n4.getEnd());
    }

    @Test
    public void testNoteToString() {
        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(10, 2, 4, Pitch.CS);

        assertEquals("C4", n1.toString());
        assertEquals("D3", n2.toString());
        assertEquals("C#4", n3.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testChangeNoteThrowsExceptionNegativeOctave() {
        Note n1 = new Note();
        n1.changeNote(1 , -1, Pitch.C);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testChangeNoteThrowsExceptionHighOctave() {
        Note n1 = new Note();
        n1.changeNote(5, 105, Pitch.D);
    }

    @Test (expected = NullPointerException.class)
    public void testChangeNotePitchNonNull() {
        Note n1 = new Note();
        n1.changeNote(5, 5, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testChangeNoteThrowsExceptionNegativeStart() {
        Note n1 = new Note();
        n1.changeNote(-1, 5, Pitch.E);
    }

    @Test
    public void testChangeNote() {
        Note n1 = new Note(5, 4, 3, Pitch.D);

        n1.changeNote(5, 2, Pitch.D);
        assertEquals(5, n1.getStart());
        assertEquals(2, n1.getOctave());
        assertEquals(Pitch.D, n1.getPitch());
        assertEquals(4, n1.getDuration());

        n1.changeNote(2, 2, Pitch.D);
        assertEquals(2, n1.getStart());
        assertEquals(2, n1.getOctave());
        assertEquals(Pitch.D, n1.getPitch());
        assertEquals(4, n1.getDuration());

        n1.changeNote(2, 2, Pitch.E);
        assertEquals(2, n1.getStart());
        assertEquals(2, n1.getOctave());
        assertEquals(Pitch.E, n1.getPitch());
        assertEquals(4, n1.getDuration());

        n1.changeNote(0, 4, Pitch.C);
        assertEquals(0, n1.getStart());
        assertEquals(4, n1.getOctave());
        assertEquals(Pitch.C, n1.getPitch());
        assertEquals(4, n1.getDuration());
    }

    @Test
    public void testNoteCompareTo() {
        Note n1 = new Note();
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(5, 4, 3, Pitch.E);
        Note n4 = new Note(5, 4, 4, Pitch.D);

        assertTrue(n2.compareTo(n2) == 0);
        assertTrue(n2.compareTo(n4) < 0);
        assertTrue(n4.compareTo(n2) > 0);
        assertTrue(n2.compareTo(n3) < 0);
        assertTrue(n3.compareTo(n2) > 0);
        assertTrue(n1.compareTo(n2) > 0);
    }

    @Test
    public void testNoteEquality() {
        Note n1 = new Note(5, 4, 3, Pitch.D);
        Note n2 = new Note(5, 4, 3, Pitch.D);
        Note n3 = new Note(5, 4, 3, Pitch.E);

        assertEquals(n1, n2);
        assertEquals(n1, n1);
        assertEquals(n3, n3);
        assertNotEquals(n1, n3);
        assertNotEquals(n2, n3);
    }
}
