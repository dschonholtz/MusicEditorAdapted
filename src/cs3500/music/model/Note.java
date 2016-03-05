package cs3500.music.model;

import java.util.Objects;

/** Represents a musical note */
public class Note implements Comparable<Note> {


    /** The starting beat of the note */
    private int start;
    /** The amount of beats this note lasts */
    private int duration;
    /** The octave this note is in */
    private int octave;
    /** The pitch of this note (C to B) */
    private Pitch pitch;

    /** Public default constructor defaults to middle C quarter note */
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

        Objects.requireNonNull(pitch);
        this.pitch = pitch;
        this.start = start;
        this.duration = duration;
        this.octave = octave;
    }

    /** @return the starting beat of the note */
    public int getStart() {
        return start;
    }
    /** @return the number of beats the note lasts */
    public int getDuration() { return duration; }
    /** @return the {@Link Pitch} of the note */
    public Pitch getPitch() { return pitch; }
    /** @return the octave of the note (between 0 and 99) */
    public int getOctave() { return octave; }
    /** @return the beat the note ends on */
    public int getEnd() { return start + duration - 1; }
    /** @return the pitch and octave of the note */
    public String toString() { return pitch.getString() + Integer.toString(octave); }

    /** Change the pitch of this note */
    public void changePitch(int octave, Pitch pitch) {
        if (octave > 99 || octave < 0) throw new IllegalArgumentException("Octave must be between 0 and 99");
        Objects.requireNonNull(pitch);
        this.octave = octave;
        this.pitch = pitch;
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
}
