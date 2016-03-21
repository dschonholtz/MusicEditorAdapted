package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.Note;
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
        synth = tempS;
        receiver = tempR;
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
                start = new ShortMessage(ShortMessage.NOTE_ON, 0, calcMidiValue(n), n.getVolume());
                stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, calcMidiValue(n), n.getVolume());
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }

            this.receiver.send(start, n.getStart() * song.getTempo());
            this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
        } catch (NullPointerException c) {
            c.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < song.getLength(); i++) {
            List<NoteRep> notes = song.getNotesStartingAtT(i);
            for (NoteRep n : notes) {
                playNote(n);
            }
        }
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.receiver.close(); // Only call this once you're done playing *all* notes
    }

    private int calcMidiValue(NoteRep n) {
        int value = n.getOctave() * 12;
        value += n.getPitch().ordinal();
        return value;
    }

}
