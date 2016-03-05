package cs3500.music.model;

import java.util.Comparator;

/** Compares {@link NoteRep}s.  Will order from lowest to highest note */
public class NoteRepComparator implements Comparator<NoteRep> {
    @Override
    public int compare(NoteRep o1, NoteRep o2) {
        if (o1.getOctave() == o2.getOctave()) return o1.getPitch().compareTo(o2.getPitch());
        return o1.getOctave() - o2.getOctave();
    }
}
