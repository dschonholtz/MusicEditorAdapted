package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapts GenericSong to Model
 */
public class ModelAdapter implements Model {
    private SongRep song;

    /** Public default constructor */
    public ModelAdapter(SongRep song) {
        this.song = song;
    }

    @Override
    public int getTempo() {
        return song.getTempo();
    }

    @Override
    public int getMaxBeats() {
        return song.getLength();
    }

    @Override
    public int getHighestPitch() {
        return new Note(song.getHighestNote()).pitchInInt();
    }

    @Override
    public int getLowestPitch() {
        return new Note(song.getLowestNote()).pitchInInt();
    }

    @Override
    public boolean containsKey(int b) {
        return song.getNotesStartingAtT(b).size() > 0;
    }

    @Override
    public List<Note> getNotesAtBeat(int b) {
        List<NoteRep> noteIn = song.getNotesStartingAtT(b);
        List<Note> noteOut = new ArrayList<>();

        for (NoteRep n : noteIn) {
            noteOut.add(new Note(n));
        }

        return noteOut;
    }

    @Override
    public List<Note> getAllNotes() {
        List<NoteRep> noteIn = song.getAllNoteReps();
        List<Note> noteOut = new ArrayList<>();

        for (NoteRep n : noteIn) {
            noteOut.add(new Note(n));
        }

        return noteOut;
    }

    @Override
    public String display() {
        return song.getState();
    }
}
