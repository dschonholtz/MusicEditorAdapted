package cs3500.music.model;

import java.util.List;

/** A representation for a song.  Songs are defined as a */
public interface SongRep {
    /**
     * The current beat of a song not in progress is 0.  After the first beat, it is 1, then 2, etc.
     * @return the current beat of the song
     */
    int getBeat();
    /** Adds a given note to the song */
    void addNote(NoteRep note);
    /** Removes a given note from the song */
    void removeNote(NoteRep note);
    /** Returns a list of the notes in this song */
    List<NoteRep> getNotes();
    /**
     * Returns the song as a String representation.  It lists the range of notes across the top,
     * and the length of the song from 0 to the last beat.  Notes are represented with "X" at
     * their start and "|" for their duration.  If more than one of the same note at the same time
     * are present, only the first one is displayed.
     */
    String getState();
    /** Adds the given song so that it plays at the same time as this one */
    void combineSimultaneously(SongRep other);
    /** Adds the given song to the end of this one */
    void combineConsecutively(SongRep other);
    /** Sets the current beat to the given */
    void setCurrentBeat(int target);
}
