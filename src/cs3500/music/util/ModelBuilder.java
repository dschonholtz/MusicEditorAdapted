package cs3500.music.util;

import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
import cs3500.music.model.NoteRep;
import cs3500.music.model.Pitch;

import java.util.List;

public class ModelBuilder implements CompositionBuilder<List<NoteRep>> {

    private int tempo;
    private List<NoteRep> notes;

    @Override
    public List<NoteRep> build() {
        GenericSong song = new GenericSong(this.notes, this.tempo);
        return notes;
    }

    @Override
    public CompositionBuilder<List<NoteRep>> setTempo(int tempo) {
        this.tempo = tempo;
        return this;
    }

    @Override
    public CompositionBuilder<List<NoteRep>> addNote(int start, int end, int instrument, int pitch, int volume) {

        int octave = pitch/11;
        Pitch p = Pitch.values()[pitch - octave];

        NoteRep note = new Note(start, end-start, octave, p, instrument, volume);
        notes.add(note);
        return this;
    }
}
