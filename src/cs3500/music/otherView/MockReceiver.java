package cs3500.music.otherView;


import java.util.*;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by liangmanman1 on 3/21/16.
 */
public class MockReceiver implements Receiver{

  private StringBuilder result;
//  private int startFrom;
  private Map<Integer, List<Integer>> inform = new HashMap<>();
  /**
   * Constructor for MockReceiver
   *
   * @param result the all information when we play the music
   */
  public MockReceiver(StringBuilder result) {
    this.result = result;
  }

  // is already in Note txt type, but in unSorted way :(
  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage m1 = (ShortMessage) message;
    if (m1.getCommand() == ShortMessage.NOTE_ON) {
      int startFrom = (int)timeStamp;
      int instrument = m1.getChannel();
      int pitch = m1.getData1() + 12;
      int volume =  m1.getData2();
      if (inform.containsKey(pitch)) {
        List<Integer> infromInPitch = inform.get(pitch);
        infromInPitch.add(infromInPitch.size(), instrument);
        infromInPitch.add(infromInPitch.size(), volume);
        infromInPitch.add(infromInPitch.size(), startFrom);
        inform.put(pitch, infromInPitch);
      }
      else {
        inform.put(pitch, new ArrayList<>(Arrays.asList(instrument,volume, startFrom)));
      }
    }
    if (m1.getCommand()  == ShortMessage.NOTE_OFF) {
      int endWhen = (int)timeStamp;
      int instrument = m1.getChannel();
      int pitch = m1.getData1() + 12;
      int volume =  m1.getData2();
      int startFrom = -1;

      List<Integer> informs = inform.get(pitch);
      for (int i = 0; i < informs.size(); i= i + 3) {
        if (informs.get(i)==instrument&&informs.get(i+1)==volume){
          startFrom = informs.get(i+2);
        }
      }
      String instrumentS = String.format("%02d", instrument);
      String volumeS = String.format("%02d", volume);
      String pitchS = String.format("%02d", pitch);
      String StartFromS = String.format("%02d", startFrom);
      String endS = String.format("%02d", endWhen);
      result.append("Note " + StartFromS + " " + endS + " " + instrumentS
      + " " + pitchS+ " " + volumeS + "\n");
    }
  }

  /**
   *
   * @return String showing what the receiver send
   */
  public StringBuilder getResult() {
    return result;
  }

  @Override
  public void close() {

  }
}
