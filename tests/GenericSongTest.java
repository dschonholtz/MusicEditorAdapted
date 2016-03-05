import cs3500.music.model.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/** Tests for the {@link GenericSong} class */
public class GenericSongTest {
//    @Test
//    public void testGetState() {
//        SongRep gs1 = new GenericSong();
//        assertEquals("E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4", gs1.getState());
//        gs1.addNote(new Note(2, Pitch.D));
//        gs1.addNote(new Note(3, Pitch.D));
//        gs1.addNote(new Note(1, 2, 2, Pitch.D));
//        gs1.addNote(new Note(10, 4, 3, Pitch.E));
//        System.out.println(gs1.getState());
//    }

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

    }
}
