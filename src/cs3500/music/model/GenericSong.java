package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a piece of music
 */
public class GenericSong implements SongRep {
    private int currentBeat;
    private List<Note> notes;
    public static final int BPM = 120;

    /** Public default constructor */
    public GenericSong() {
        this.currentBeat = 0;
        this.notes = new ArrayList<Note>();
    }

    /** Constructor for a pre-made song */
    public GenericSong(List<Note> notes) {
        this.currentBeat = 0;
        this.notes = notes;
    }

    @Override
    public int getBeat() {
        return currentBeat;
    }

    @Override
    public void addNote(Note n) {
        if (!notes.contains(n)) notes.add(n);
    }

    @Override
    public void removeNote(Note n) { this.notes.remove(n); }

    @Override
    public int getLength() {
        int out = 0;

        for (Note n : notes) {
            int end = n.getEnd();
            out = end > out ? end : out;
        }

        return out;
    }

    @Override
    public String getState() {
        if (notes.isEmpty()) return "This song is empty";

        // space for the beats column
        String out = String.format("%" + Integer.toString(getLength()).length() + "s", "");

        // add each pitch in the range
        for (String s : getRange()) {
            out += String.format("%5s", String.format("%-2s", s));
        }

        // print line by line for the entire length of the song
        for (int i = 0; i <= getLength(); i++) {
            out += "\n" + printLine(i);
        }

        return out;
    }

    @Override
    public void combineSimultaneously(SongRep other) {

    }

    @Override
    public void combineConsecutively(SongRep other) {

    }

    /** @return a String representing the range of notes in this song. */
    private ArrayList<String> getRange() {
        Note lowestNote = getLowestNote();
        int lowestOctave = lowestNote.getOctave();
        Note highestNote = getHighestNote();
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
                } else if (i == highestOctave) {
                    pitchInRange = pitchBelowHighest;
                } else {
                    pitchInRange = true;
                }

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
    private String printLine(int line) {
        ArrayList<String> noteRange = getRange();
        String out = String.format("%" + (Integer.toString(getLength()).length() + 1) + "s", line);

        for (String s : noteRange) {
            String temp = "     ";
            for (Note n : notes) {
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
    private Note getLowestNote() {
        if (notes.isEmpty()) throw new IllegalArgumentException("There are no notes");

        Note lowestNote = notes.get(0);
        for (Note n : notes) {
            if (n.compareTo(lowestNote) < 0) lowestNote = n;
        }

        return lowestNote;
    }

    /** @return the highest note in this song (as on a piano) */
    private Note getHighestNote() {
        if (notes.isEmpty()) throw new IllegalArgumentException("There are no notes");

        Note highestNote = notes.get(0);
        for (Note n : notes) {
            if (n.compareTo(highestNote) > 0) highestNote = n;
        }

        return highestNote;
    }
}
