package cs3500.music.model;

import java.util.List;

/** A representation for a song. */
public interface SongRep {
    /**
     * Beats start at 0.
     *
     * @return the current beat of the song
     */
    int getBeat();

    /**
     * Adds a note to the song.
     * Duplicate or overlapping, same pitch notes will not be added to the song.
     *
     * @param note to be added
     */
    void addNote(NoteRep note);

    /**
     * Removes a note from the song.
     * Since there are no duplicates, there will only ever be at most one of each note in the
     * song.
     * If the note is not in the song, nothing will happen.
     *
     * @param note to be removed
     */
    void removeNote(NoteRep note);

    /**
     * @return an unmodifiable list of all the notes in this song
     */
    List<NoteRep> getAllNotes();

    /**
     * Gets a list of all the notes in this song that have a starting time of t
     *
     * @param t the starting beat of all returned notes
     * @return a list of all notes that start at t
     */
    List<NoteRep> getNotesStartingAtT(int t);

    /**
     * Gets a list of all the notes in this song that are either starting or continuing
     * to play at time t
     *
     * @param t the beat at which to get playing notes
     * @return a list of all notes playing at t
     */
    List<NoteRep> getNotesPlayingAtT(int t);

    /**
     * Lists the range of notes across the top, and the length of the song from 0 to the last beat
     * down the left column.  Notes are represented with an "X" at their start and "|" for their
     * duration.
     *
     * @return a string representing all the notes in this song
     */
    String getState();

    /**
     * Adds the given song's notes to this one so that it plays at the same time
     *
     * @param other the song to be added on top of this one
     */
    void combineSimultaneously(SongRep other);

    /**
     * Adds the given song's notes to the end of this one so that it plays directly afterward
     *
     * @param other the song to be added after this one
     */
    void combineConsecutively(SongRep other);

    /**
     * Sets the song's current beat to the given
     *
     * @param target the beat to set this song to
     */
    void setCurrentBeat(int target);

    /**
     * Gets the length of the song in beats, which is also the int of the last beat in the song
     *
     * @return the number of beats this song spans
     */
    int getLength();

    /**
     * Gets a list of the string representations of the range of pitches in this song from
     * low to high
     *
     * @return a list of every pitch in this song
     */
    List<String> getRange();

    /**
     * Gets the tempo of this song in microseconds per beat
     *
     * @return the tempo of this song
     */
    int getTempo();
}
