package cs3500.music.model;

import java.util.Objects;

/** Represents a musical note */
public class Note implements Comparable<Note>, NoteRep {
    /** The starting beat of the note */
    private int start;
    /** The amount of beats this note lasts */
    private int duration;
    /** The octave this note is in */
    private int octave;
    /** The pitch of this note (C to B) */
    private Pitch pitch;

    /** Public default constructor defaults to middle C quarter note at time 0*/
    public Note() {
        this.start = 0;
        this.duration = 1;
        this.pitch = Pitch.C;
        this.octave = 4;
    }

    /** Constructor choosing all fields */
    public Note(int start, int duration, int octave, Pitch pitch) {
        if (octave > 99 || octave < 0) {
            throw new IllegalArgumentException("Octave must be between 0 and 99");
        }
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
    public void changeNote(int start, int octave, Pitch pitch) {
        if (octave > 99 || octave < 0) {
            throw new IllegalArgumentException("Octave must be between 0 and 99");
        }
        if (start < 0) {
            throw new IllegalArgumentException("Starting beat must be positive");
        }
        Objects.requireNonNull(pitch);

        this.octave = octave;
        this.pitch = pitch;
        this.start = start;
    }

    /**
     * @return
     * negative if this note is lower (as on a piano)<p></p>
     * 0 if this is the same note<p></p>
     * positive if this note is higher (as on a piano)
     */
    @Override
    public int compareTo(Note o) {
        if (this.octave == o.octave) return this.pitch.compareTo(o.pitch);
        return this.octave - o.octave;
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
