package cs3500.music.util;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * Created by Ari on 3/21/2016.
 */
public class MockReceiver implements Receiver {
    StringBuilder log;

    public MockReceiver(StringBuilder log) {
        this.log = log;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        log.append(timeStamp + " " + message + "\n");
    }

    @Override
    public void close() {
        System.out.println(log);
    }
}
