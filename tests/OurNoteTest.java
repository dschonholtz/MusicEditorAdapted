import cs3500.music.model.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/** Tests for the {@link OurNote} class */
public class OurNoteTest {
    @Test (expected = NullPointerException.class)
    public void testNotePitchNonNull() {
        new OurNote(2, 3, 4, null, 1, 65);
    }

    @Test //should not throw and exception
    public void testNoteZeroDuration() {
        new OurNote(2, 0, 3, Pitch.C, 1, 65);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNoteNegativeStart() { new OurNote(-1, 2, 3, Pitch.D, 1, 65); }

    @Test (expected = IllegalArgumentException.class)
    public void testNoteNegativeInstrument() { new OurNote(1, 1, 1, Pitch.C, -1, 65); }

    @Test (expected = IllegalArgumentException.class)
    public void testNoteNegativeVolume() { new OurNote(1, 1, 1, Pitch.C, 2, -1); }

    @Test
    public void testGetStart() {
        OurNote n1 = new OurNote();
        OurNote n2 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n3 = new OurNote(10, 2, 4, Pitch.C, 1, 65);

        assertEquals(0, n1.getStart());
        assertEquals(5, n2.getStart());
        assertEquals(10, n3.getStart());
    }

    @Test
    public void testGetDuration() {
        OurNote n1 = new OurNote();
        OurNote n2 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n3 = new OurNote(10, 2, 4, Pitch.C, 1, 65);

        assertEquals(1, n1.getDuration());
        assertEquals(4, n2.getDuration());
        assertEquals(2, n3.getDuration());
    }

    @Test
    public void testGetPitch() {
        OurNote n1 = new OurNote();
        OurNote n2 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n3 = new OurNote(10, 2, 4, Pitch.CS, 1, 65);

        assertEquals(Pitch.C, n1.getPitch());
        assertEquals(Pitch.D, n2.getPitch());
        assertEquals(Pitch.CS, n3.getPitch());
    }

    @Test
    public void testGetOctave() {
        OurNote n1 = new OurNote();
        OurNote n2 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n3 = new OurNote(10, 2, 4, Pitch.CS, 1, 65);

        assertEquals(4, n1.getOctave());
        assertEquals(3, n2.getOctave());
        assertEquals(4, n3.getOctave());
    }

    @Test
    public void testGetEnd() {
        OurNote n1 = new OurNote();
        OurNote n2 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n3 = new OurNote(10, 2, 4, Pitch.CS, 1, 65);
        OurNote n4 = new OurNote(0, 5, 4, Pitch.B, 1, 65);

        assertEquals(0, n1.getEnd());
        assertEquals(8, n2.getEnd());
        assertEquals(11, n3.getEnd());
        assertEquals(4, n4.getEnd());
    }

    @Test
    public void testNoteToString() {
        OurNote n1 = new OurNote();
        OurNote n2 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n3 = new OurNote(10, 2, 4, Pitch.CS, 1, 65);

        assertEquals("C4", n1.toString());
        assertEquals("D3", n2.toString());
        assertEquals("C#4", n3.toString());
    }

    @Test
    public void testNoteEquality() {
        OurNote n1 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n2 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n3 = new OurNote(5, 4, 3, Pitch.E, 1, 65);
        OurNote n4 = new OurNote(5, 4, 3, Pitch.E, 3, 70);

        assertEquals(n1, n2);
        assertEquals(n1, n1);
        assertEquals(n3, n3);
        assertNotEquals(n1, n3);
        assertNotEquals(n2, n3);
        assertNotEquals(n3, n4);

        assertEquals(n1.hashCode(), n1.hashCode());
        assertEquals(n1.hashCode(), n2.hashCode());
        assertNotEquals(n1.hashCode(), n3.hashCode());
        assertNotEquals(n3.hashCode(), n4.hashCode());
    }


    @Test
    public void testGetVolume() {
        OurNote n1 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n2 = new OurNote(5, 4, 2, Pitch.C, 5, 500);

        assertEquals(65, n1.getVolume());
        assertEquals(500, n2.getVolume());
    }

    @Test
    public void testGetInstrument() {
        OurNote n1 = new OurNote(5, 4, 3, Pitch.D, 1, 65);
        OurNote n2 = new OurNote(5, 4, 2, Pitch.C, 5, 500);

        assertEquals(1, n1.getInstrument());
        assertEquals(5, n2.getInstrument());
    }
}
