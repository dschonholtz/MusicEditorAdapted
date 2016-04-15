package cs3500.music.model;

/**
 * This is our Note Adapter.
 */
public class Note extends OurNote implements NoteRep {
    /** Public default constructor */
    public Note() {
        super();
    }

    /** @return an integer representation of a note //todo is this the same as midi representation? */
    public int toInt() {
        return 0;
    }

    /** @return a note with the given pitch */
    public static Note convertToNote(int pitch) {
        return null;
    }

    /** @return the pitch of this note as an int */
    public int pitchInInt() {
        return 0;
    }

    /** @return the start of the note */ //TODO IS THIS BEATS OR MICROSECONDS???
    public int getStartFrom() { //todo how does this calculate start in microseconds?
        return super.getStart(); //todo maybe this is wrong idk
    }
}
