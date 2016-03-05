package cs3500.music.model;

/** A representation of a musical note */
public interface NoteRep {
    /** @return the beat of the song that this note starts on */
    int getStart();
    /** @return the number of beats this note lasts */
    int getDuration();
    /** @return the beat of the song that this note ends on */
    int getEnd();
    /** @return the pitch of this note */
    Pitch getPitch();
    /** @return the octave this note is in */
    int getOctave();
    /**
     * Change the start, octave, pitch of this note to the give values
     *
     * @param start the new starting beat for this note
     * @param octave the new octave for this note
     * @param pitch the new pitch for this note
     */
    void changeNote(int start, int octave, Pitch pitch);
    /** @return the string representation of this note */
    String toString();
}
