package cs3500.music.model;

import java.util.Objects;

/** Represents a musical note */
public final class Note implements NoteRep {

    /** The starting beat of the note */
    private int start;
    /** The amount of beats this note lasts */
    private int duration;
    /** The octave this note is in (positive number) */
    private int octave;
    /** The pitch of this note (C to B) */
    private Pitch pitch;
    /** The volume of all notes in decibels (65 is average for conversation) */
    private int volume;
    /*The instrument playing the note*/
    private int instrument;                                        //TODO: Should this be moved to an enum?

    /** Public default constructor defaults to middle C quarter note at time 0*/
    public Note() {
        this.start = 0;
        this.duration = 1;
        this.pitch = Pitch.C;
        this.octave = 4;
        this.instrument = 1;
        this.volume = 65;
    }

    /** Constructor choosing all fields */
    public Note(int start, int duration, int octave, Pitch pitch, int instrument, int volume) {
        if (octave < 0) throw new IllegalArgumentException("Octave must be positive");
        if (duration < 1) {
            throw new IllegalArgumentException("Duration must be at least 1");
        }
        if (start < 0) {
            throw new IllegalArgumentException("Starting beat must be positive");
        }

        Objects.requireNonNull(pitch);
        this.pitch = pitch;
        this.start = start;
        this.duration = duration;
        this.octave = octave;
        this.instrument = instrument;
        this.volume = volume;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getDuration() { return duration; }

    @Override
    public Pitch getPitch() { return pitch; }

    @Override
    public int getOctave() { return octave; }


    @Override
    public int getEnd() { return start + duration - 1; }

    @Override
    public String toString() { return pitch.getString() + Integer.toString(octave); }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public int getInstrument() {
        return this.instrument;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Note)) return false;
        Note n = (Note)obj;

        return (this.start == n.getStart()) && (this.duration == n.getDuration()) &&
                (this.octave == n.getOctave()) && (this.pitch.equals(n.getPitch()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, duration, octave, pitch);
    }
}
