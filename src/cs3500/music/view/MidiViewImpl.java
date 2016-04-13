package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.NoteRep;
import cs3500.music.model.SongRep;

import javax.sound.midi.*;
import java.util.List;
import java.util.Objects;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements IMusicView {
    private final Synthesizer synth;
    private final Receiver receiver;
    private SongRep song;

    /**
     *  Public default constructor
     */
    public MidiViewImpl() {
        this.song = new GenericSong();
        Synthesizer tempS;
        Receiver tempR;
        try {
            tempS = MidiSystem.getSynthesizer();
            tempR = tempS.getReceiver();
            tempS.open();
        } catch (MidiUnavailableException e) {
            tempS = null;
            tempR = null;
            e.printStackTrace();
        }

        synth = tempS;
        receiver = tempR;
    }

    /**
     * Constructor for custom, pre-made songs
     * @param song the song to be played
     */
    public MidiViewImpl(SongRep song) {
        Objects.requireNonNull(song);
        this.song = song;
        Synthesizer tempS;
        Receiver tempR;
        try {
            tempS = MidiSystem.getSynthesizer();
            tempR = tempS.getReceiver();
            tempS.open();
        } catch (MidiUnavailableException e) {
            tempS = null;
            tempR = null;
            e.printStackTrace();
        }
        this.synth = tempS;
        this.receiver = tempR;
    }

    /**
     * Constructor for custom synthesizers and receivers made for mock testing
     *
     * @param song premade song to be played
     * @param synth synthesizer this midi view should use
     */
    public MidiViewImpl(SongRep song, Synthesizer synth) {
        Objects.requireNonNull(song);
        Objects.requireNonNull(synth);
        Synthesizer tempS;
        Receiver tempR;
        this.song = song;

        try {
            tempS = synth;
            tempR = tempS.getReceiver();
        } catch (MidiUnavailableException e) {
            tempS = null;
            tempR = null;
            e.printStackTrace();
        }
        this.synth = tempS;
        this.receiver = tempR;
    }

    /**
     * Relevant classes and methods from the javax.sound.midi library:
     * <ul>
     * <li>{@link MidiSystem#getSynthesizer()}</li>
     * <li>{@link Synthesizer}
     * <ul>
     * <li>{@link Synthesizer#open()}</li>
     * <li>{@link Synthesizer#getReceiver()}</li>
     * <li>{@link Synthesizer#getChannels()}</li>
     * </ul>
     * </li>
     * <li>{@link Receiver}
     * <ul>
     * <li>{@link Receiver#send(MidiMessage, long)}</li>
     * <li>{@link Receiver#close()}</li>
     * </ul>
     * </li>
     * <li>{@link MidiMessage}</li>
     * <li>{@link ShortMessage}</li>
     * <li>{@link MidiChannel}
     * <ul>
     * <li>{@link MidiChannel#getProgram()}</li>
     * <li>{@link MidiChannel#programChange(int)}</li>
     * </ul>
     * </li>
     * </ul>
     *
     * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
     * https://en.wikipedia.org/wiki/General_MIDI
     * </a>
     */

    public void playNote(NoteRep n) {
        try {
            MidiMessage start = null;
            MidiMessage stop = null;
            try {
                start = new ShortMessage(ShortMessage.NOTE_ON,
                        n.getInstrument() - 1, calcMidiValue(n),
                        n.getVolume());
                stop = new ShortMessage(ShortMessage.NOTE_OFF,
                        n.getInstrument() - 1, calcMidiValue(n),
                        n.getVolume());
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }

            this.receiver.send(start, n.getStart() * song.getTempo());
            this.receiver.send(stop, (n.getEnd() + 1) * song.getTempo());
        } catch (NullPointerException c) {
            c.printStackTrace();
        }
    }

    @Override
    public void run() {
            List<NoteRep> notes = song.getNotesStartingAtT(song.getBeat());
            for (NoteRep n : notes) {
                playNote(n);
            }
    }

    /**
     *
     * @param n - The note to be used to calculate the midi value
     * @return returns the midi value the note has for its octave and pitch
     */
    private int calcMidiValue(NoteRep n) {
        int value = (n.getOctave() + 1) * 12;
        value += n.getPitch().ordinal();
        return value;
    }

}
