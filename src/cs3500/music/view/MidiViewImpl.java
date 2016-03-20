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
     *  <li>{@link MidiSystem#getSynthesizer()}</li>
     *  <li>{@link Synthesizer}
     *    <ul>
     *      <li>{@link Synthesizer#open()}</li>
     *      <li>{@link Synthesizer#getReceiver()}</li>
     *      <li>{@link Synthesizer#getChannels()}</li>
     *    </ul>
     *  </li>
     *  <li>{@link Receiver}
     *    <ul>
     *      <li>{@link Receiver#send(MidiMessage, long)}</li>
     *      <li>{@link Receiver#close()}</li>
     *    </ul>
     *  </li>
     *  <li>{@link MidiMessage}</li>
     *  <li>{@link ShortMessage}</li>
     *  <li>{@link MidiChannel}
     *    <ul>
     *      <li>{@link MidiChannel#getProgram()}</li>
     *      <li>{@link MidiChannel#programChange(int)}</li>
     *    </ul>
     *  </li>
     * </ul>
     * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
     *   https://en.wikipedia.org/wiki/General_MIDI
     *   </a>
     */

    public void playNote(int strt, int stp, int volume) throws InvalidMidiDataException {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
        this.receiver.send(start, -1);
        this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
        /*MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
        this.receiver.send(start, -1);
        this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
        //this.receiver.close(); // Only call this once you're done playing *all* notes
        */
    }

    @Override
    public void run() {
        for(int i = 0; i < song.getLength(); i++) {
            List notes = song.getNotesStartingAtT(i);
            for(Object o : notes) {
                Note n = (Note)o;

            }
        }
        this.receiver.close(); // Only call this once you're done playing *all* notes
    }

    private int calcMidiValue(NoteRep n) {
        int value = n.getOctave()*11;
        value += n.getPitch().ordinal();
        return value;
    }

}
