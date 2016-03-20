package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.*;

/**
 * Represents a piece of music
 */
public class GenericSong implements SongRep {
    public static final class Builder implements CompositionBuilder<GenericSong> {
        private int tempo;
        private List<NoteRep> notes = new ArrayList<>();

        @Override
        public GenericSong build() {
            return new GenericSong(this.notes, this.tempo);
        }

        @Override
        public CompositionBuilder<GenericSong> setTempo(int tempo) {
            this.tempo = tempo;
            return this;
        }

        @Override
        public CompositionBuilder<GenericSong> addNote(int start, int end, int instrument, int pitch, int volume) {

            int octave = pitch / 11;
            Pitch p = Pitch.values()[pitch - octave * 11];

            NoteRep note = new Note(start, end-start, octave, p, instrument, volume);
            notes.add(note);
            return this;
        }
    }

    private int tempo; // in microseconds per beat
    private int currentBeat;
    private List<NoteRep> notes;

    /** Public default constructor */
    public GenericSong() {
        this.currentBeat = 0;
        this.notes = new ArrayList<>();
    }

    /** Constructor for a pre-made song */
    public GenericSong(List<NoteRep> notes, int tempo) {
        Objects.requireNonNull(notes);
        if (tempo < 0) {
            throw new IllegalArgumentException("Tempo must be positive");
        }
        this.tempo = tempo;
        this.currentBeat = 0;
        this.notes = notes;
    }

    @Override
    public int getBeat() {
        return currentBeat;
    }

    @Override
    /** Adds a note to the song only if it doesn't collide with any current notes */
    public void addNote(NoteRep n) {
        Objects.requireNonNull(n);
        boolean shouldAdd = true;

        for (NoteRep note : notes) {
            boolean sameNote = note.getPitch().equals(n.getPitch());
            int nLo = n.getStart();
            int nHi = n.getEnd();
            boolean hasOverlap = false;

            for (int i = nLo; i <= nHi; i++) {
                if (i >= note.getStart() && i <= note.getEnd()) {
                    hasOverlap = true;
                }
            }

            if (sameNote && hasOverlap) {
                shouldAdd = false;
                break;
            }
        }

        if (shouldAdd) {
            notes.add(n);
        }
    }

    @Override
    public void removeNote(NoteRep n) {
        Objects.requireNonNull(n);
        this.notes.remove(n);
    }

    @Override
    public List<NoteRep> getAllNotes() {
        return Collections.unmodifiableList(notes);
    }

    @Override
    public List<NoteRep> getNotesStartingAtT(int t) {
        ArrayList<NoteRep> out = new ArrayList<>();
        for (NoteRep n : getAllNotes()) {
            if (n.getStart() == t) out.add(n);
        }
        return out;
    }

    @Override
    public List<NoteRep> getNotesPlayingAtT(int t) {
        ArrayList<NoteRep> started = new ArrayList<>();
        ArrayList<NoteRep> out = new ArrayList<>();

        for (int i = 0; i <= t; i++) {
            started.addAll(getNotesStartingAtT(i));
        }

        for (NoteRep n : started) {
            if (n.getEnd() >= t) out.add(n);
        }

        return out;
    }

    @Override
    public String getState() {
        int len = getLength();                                                         // LINEAR
        ArrayList<String> range = getRange();

        if (notes.isEmpty()) return "This song is empty";

        // space for the beats column
        String out = String.format("%" + Integer.toString(len).length() + "s", "");

        // add each pitch in the range
        for (String s : range) {                                                       // LINEAR
            out += String.format("%5s", String.format("%-2s", s));
        }

        // print line by line for the entire length of the song
        for (int i = 0; i <= len; i++) {
            out += "\n" + printLine(i, range);
        }

        return out;
    }

    @Override
    public void combineSimultaneously(SongRep other) {
        Objects.requireNonNull(other);

        for (NoteRep n : other.getAllNotes()) {
            addNote(n);
        }
    }

    @Override
    public void combineConsecutively(SongRep other) {
        Objects.requireNonNull(other);
        int thisSongLength = getLength();
        for (NoteRep n : other.getAllNotes()) {
            Note n2 = new Note(n.getStart() + thisSongLength, n.getDuration(), n.getOctave(),
                    n.getPitch(), n.getInstrument(), n.getVolume());
            notes.add(n2);
        }
    }

    @Override
    public void setCurrentBeat(int set) {
        if (set < 0) {
            throw new IllegalArgumentException("Current beat must be positive");
        }
        this.currentBeat = set;
    }

    /** @return a String representing the range of notes in this song. */
    private ArrayList<String> getRange() {
        NoteRep lowestNote = getLowestNote();
        int lowestOctave = lowestNote.getOctave();
        NoteRep highestNote = getHighestNote();
        int highestOctave = highestNote.getOctave();
        ArrayList<String> out = new ArrayList<>();

        for (int i = lowestOctave; i <= highestOctave; i++) {
            for (Pitch p : Pitch.values()) {
                boolean pitchInRange;
                boolean pitchAboveLowest = p.compareTo(lowestNote.getPitch()) >= 0;
                boolean pitchBelowHighest = p.compareTo(highestNote.getPitch()) <= 0;

                if (i == lowestOctave && i == highestOctave) {
                    pitchInRange = pitchAboveLowest && pitchBelowHighest;
                } else if (i == lowestOctave) {
                    pitchInRange = pitchAboveLowest;
                } else
                    pitchInRange = i != highestOctave || pitchBelowHighest;

                if (pitchInRange) out.add(p.getString() + i);
            }
        }

        return out;
    }

    /**
     * Returns a string representation for any notes on the given line of the song.  If there are
     * more than one of the same note at the same time, only the first one will be shown.
     *
     *  @return a String representation for any notes on the given line of the song
     */
    private String printLine(int line, ArrayList<String> noteRange) {
        String out = String.format("%" + (Integer.toString(getLength()).length() + 1) + "s", line);

        for (String s : noteRange) {
            String temp = "     ";
            for (NoteRep n : notes) {
                boolean noteStarting = n.toString().equals(s) && n.getStart() == line;
                boolean noteInProgress = n.toString().equals(s) && (n.getStart() < line) &&
                        (n.getEnd() >= line);
                if (noteStarting) {
                    temp = "  X  ";
                    break;
                } else if (noteInProgress) {
                    temp = "  |  ";
                    break;
                }
            }

            out += temp;
        }

        return out;
    }

    /** @return the lowest note in this song (as on a piano) */
    private NoteRep getLowestNote() {
        if (notes.isEmpty()) throw new IllegalArgumentException("There are no notes");

        NoteRep lowestNote = notes.get(0);
        for (NoteRep n : notes) {
            if (new NoteRepComparator().compare(n, lowestNote) < 0) lowestNote = n;
        }

        return lowestNote;
    }

    /** @return the highest note in this song (as on a piano) */
    private NoteRep getHighestNote() {
        if (notes.isEmpty()) throw new IllegalArgumentException("There are no notes");

        NoteRep highestNote = notes.get(0);
        for (NoteRep n : notes) {
            if (new NoteRepComparator().compare(n, highestNote) > 0) highestNote = n;
        }

        return highestNote;
    }

    @Override
    public int getLength() {
        int out = 0;

        for (NoteRep n : notes) {
            int end = n.getEnd();
            out = end > out ? end : out;
        }
        return out;
    }
}
