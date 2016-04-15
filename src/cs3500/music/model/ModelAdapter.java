package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapts GenericSong to Model
 */
public class ModelAdapter extends GenericSong implements Model {
    /** Public default constructor */
    public ModelAdapter() {
        super();
    }

    @Override
    public int getMaxBeats() {
        return super.getLength();
    }

    @Override
    public int getHighestPitch() {
        ArrayList<String> range = super.getRange();
        String highest = range.get(range.size() - 1);
        return 0;
    }

    @Override
    public int getLowestPitch() {
        return 0;
    }

    @Override
    public boolean containsKey(int b) {
        return false;
    }

    @Override
    public List<Note> getNotesAtBeat(int b) {
        return null;
    }

    @Override
    public String display() {
        return null;
    }

    @Override
    public List<Note> getAllNotes() {
        ArrayList<Note> out = new ArrayList<>();
        List<NoteRep> in = super.getAllNoteReps();

        for (NoteRep n : in) {
            out.add(new Note())
        }
        return out;
    }
}
