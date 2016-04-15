package cs3500.music.otherView;


import java.util.List;
import javax.sound.midi.*;

import cs3500.music.model.*;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements View {
  private final Synthesizer synth;
  private final Receiver receiver; // in use
  private int currentBeat = 0; // in unit beat

  /**
   * Constructor for MidiViewImpl
   */
  public MidiViewImpl() {
    Synthesizer tempS = null;
    Receiver tempR = null;
    try {
      tempS = MidiSystem.getSynthesizer();
      tempR = tempS.getReceiver();
      tempS.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tempS;
    this.receiver = tempR;
  }

  /**
   * Constructor used for testing purpose
   *
   * @param synth a MockMidiDevice used for testing
   */
  public MidiViewImpl(Synthesizer synth) {
    Synthesizer tempS = null;
    Receiver tempR = null;
    try {
      tempS = synth;
      tempR = tempS.getReceiver();
      tempS.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tempS;
    this.receiver = tempR;
  }

  /**
   * Play a note with call receiver.send(MidiMessage(NOTE_ON))
   *
   * @param note the note we play
   */
  void playNote(Note note) {
    int instrument = note.getInstrument();
    int pitch = note.pitchInInt();
    int volume = note.getVolume();
    ShortMessage start1 = new ShortMessage();
    try {
      start1.setMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.receiver.send(start1, note.getStartFrom()); //todo what exactly does this return? start in microseconds? what about tempo?

  }

  /**
   * stop play a note with call receiver.send(MidiMessage(NOTE_OFF))
   *
   * @param note the note we stop
   */
  void stopNote(Note note) {
    int instrument = note.getInstrument();
    int pitch = note.pitchInInt();
    int volume = note.getVolume();
    ShortMessage stop1 = new ShortMessage();
    try {
      stop1.setMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.receiver.send(stop1, note.getStartFrom() + note.getDuration()); //in microseconds
  }

  // channels[int] means an instrument
  // thread.sleep still have problem but I don't know how to fix
  // in later beats, some notes last too long
  @Override
  public void display(Model model) {
    while (currentBeat <= model.getMaxBeats()) {
      List<Note> sorted = model.getAllNotes();
      for (Note note : sorted) {
        if (currentBeat == note.getStartFrom()) {
          this.playNote(note);
        }
        if (currentBeat == note.getStartFrom() + note.getDuration() - 1) {
          this.stopNote(note);
        }
      }
      this.currentBeat++;

      try {
        Thread.sleep(model.getTempo() / 1000); // microsecond/beat /1000, means millisecond/beat
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.receiver.close();

  }

  /**
   * display note for one beat and add beat to next to make continuously
   *
   * @param model
   */
  public void displayOneBeat(Model model) {
    if (currentBeat <= model.getMaxBeats() + 1) {
      //  System.out.print("Current Beat: " + currentBeat + "\n");
      java.util.List<Note> sorted = model.getAllNotes(); //todo they don't even use their map to make this more efficient?
      for (Note note : sorted) {
        if (currentBeat == note.getStartFrom()) {
          this.playNote(note);
        }
        if (currentBeat == (note.getStartFrom() + note.getDuration() - 1)) {
          this.stopNote(note);
        }
      }
      this.currentBeat++;
    }
  }

  public int getCurrentBeat() {
    return currentBeat;
  }

  /**
   * initialize the currentBeat to 0
   */
  public void resetCurrentBeat() {
    this.currentBeat = 0;
  }
}
