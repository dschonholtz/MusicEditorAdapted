package cs3500.music.model;

/**
 * This is our Note Adapter.
 */
public class Note implements NoteRep {
    NoteRep n;

    /** Public default constructor */
    public Note(NoteRep n) {
        this.n = n;
    }

    /** @return an integer representation of a note //todo what's the difference between this and PitchInInt? */
    public int toInt() {
        return pitchInInt();
    }

    /** @return a note with the given pitch */
    public static Note convertToNote(int pitch) {
        int octave = (pitch / 12) - 1;
        Pitch p = Pitch.values()[pitch - ((octave + 1) * 12)];
        return new Note(new OurNote(0, 4, octave, p, 1, 65));
    }

    /** @return the pitch of this note as an int */
    public int pitchInInt() {
        int value = (n.getOctave() + 1) * 12;
        value += n.getPitch().ordinal();
        return value;
    }

    /** @return the start of the note */ //TODO IS THIS BEATS OR MICROSECONDS???
    public int getStartFrom() { //todo how does this calculate start in microseconds?
        return getStart(); //todo maybe this is wrong idk
    }

    @Override
    public int getStart() {
        return n.getStart();
    }

    @Override
    public int getDuration() {
        return n.getDuration();
    }

    @Override
    public int getEnd() {
        return n.getEnd();
    }

    @Override
    public Pitch getPitch() {
        return n.getPitch();
    }

    @Override
    public int getOctave() {
        return n.getOctave();
    }

    @Override
    public int getVolume() {
        return n.getVolume();
    }

    @Override
    public int getInstrument() {
        return n.getInstrument();
    }
}
