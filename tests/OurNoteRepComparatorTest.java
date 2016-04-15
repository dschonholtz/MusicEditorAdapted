import cs3500.music.model.NoteRepComparator;
import cs3500.music.model.OurNote;
import cs3500.music.model.Pitch;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/** Tests for the {@link NoteRepComparator} */
public class OurNoteRepComparatorTest {
    @Test
    public void testNoteRepComparatorCompare() {
        NoteRepComparator nrc = new NoteRepComparator();
        OurNote n1 = new OurNote();
        OurNote n2 = new OurNote();
        OurNote n3 = new OurNote(5, 4, 3, Pitch.D, 1, 65);

        assertTrue(nrc.compare(n1, n1) == 0);
        assertTrue(nrc.compare(n1, n2) == 0);
        assertTrue(nrc.compare(n1, n3) > 0);
        assertTrue(nrc.compare(n3, n1) < 0);
    }
}
