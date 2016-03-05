package cs3500.music.model;

import java.util.Comparator;

/**
 * Created by Ari on 3/2/2016.
 */
public class NoteComparator implements Comparator<Note> {

    @Override
    public int compare(Note o1, Note o2) {
        if (o1.getOctave() == o2.getOctave()) return o1.getPitch().compareTo(o2.getPitch());
        return o1.getOctave() - o2.getOctave();
    }
}
