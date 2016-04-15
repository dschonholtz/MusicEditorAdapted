package cs3500.music.model;

import java.util.Objects;

/** Represents a musical note */
public class OurNote implements NoteRep {

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
    /** The instrument playing the note */
    private int instrument;

    /** Public default constructor defaults to middle C quarter note at time 0*/
    public OurNote() {
        this.start = 0;
        this.duration = 1;
        this.pitch = Pitch.C;
        this.octave = 4;
        this.instrument = 1;
        this.volume = 65;
    }

    /** Constructor choosing all fields */
    public OurNote(int start, int duration, int octave, Pitch pitch, int instrument, int volume) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration must be at least 0");
        }
        if (start < 0) {
            throw new IllegalArgumentException("Starting beat must be positive");
        }   
        if (instrument < 0) {
            throw new IllegalArgumentException("Instrument cannot be negative");
        }
        if (volume < 0) {
            throw new IllegalArgumentException("Volume cannot be negative");
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
        if (!(obj instanceof OurNote)) return false;
        OurNote n = (OurNote)obj;

        return (this.start == n.getStart()) && (this.duration == n.getDuration()) &&
                (this.octave == n.getOctave()) && (this.pitch.equals(n.getPitch())) &&
                (this.volume == n.getVolume()) && (this.instrument == n.getInstrument());
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, duration, octave, pitch, instrument, volume);
    }
}
