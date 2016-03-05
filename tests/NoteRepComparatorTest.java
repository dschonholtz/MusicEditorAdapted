import cs3500.music.model.NoteRepComparator;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/** Tests for the {@link NoteRepComparator} */
public class NoteRepComparatorTest {
    @Test
    public void testNoteRepComparatorCompare() {
        NoteRepComparator nrc = new NoteRepComparator();
        Note n1 = new Note();
        Note n2 = new Note();
        Note n3 = new Note(5, 4, 3, Pitch.D);

        assertTrue(nrc.compare(n1, n1) == 0);
        assertTrue(nrc.compare(n1, n2) == 0);
        assertTrue(nrc.compare(n1, n3) > 0);
        assertTrue(nrc.compare(n3, n1) < 0);
    }
}
