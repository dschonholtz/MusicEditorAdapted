package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.List;

/**
 * Represents a model used for otherView
 */
public interface Model {
    /** @return the tempo for the song in microseconds per beat */
    int getTempo();
    /** @return the total length of the song */
    int getMaxBeats();
    /** @return the highest pitch in the song //TODO how is pitch represented as an int? */
    int getHighestPitch();
    /** @return the lowest pitch in the song //TODO how is pitch represented an int? */
    int getLowestPitch();
    /** @return true if any notes are being played at beat b */
    boolean containsKey(int b);
    /** @return the list of notes that start playing at beat b */
    List<Note> getNotesAtBeat(int b);
    /** @return the list of all notes in the model */
    List<Note> getAllNotes();
    /** @return a string representing the model */
    String display();
    /**
     * Adds a note to the song
     * @param n - The note to be added
     */
    void addNote(NoteRep n);

    /** @param n - The note to be removed. */
    void removeNote(NoteRep n);


}
