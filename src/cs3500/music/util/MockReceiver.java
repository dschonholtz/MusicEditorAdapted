package cs3500.music.util;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * A mock midi receiver for emulating the midi view for testing
 */
public class MockReceiver implements Receiver {
    StringBuilder log;
    int tempo;

    public MockReceiver(StringBuilder log, int tempo) {
        this.tempo = tempo;
        this.log = log;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        ShortMessage m = (ShortMessage) message;
        String onOff = m.getCommand() == 144 ? "ON" : "OFF";
        long time = timeStamp / tempo;
        int note = m.getData1();

//        log.append(timeStamp + " " + m.getData1() + "\n");
        log.append("note " + note + " " + onOff + " at beat " + time + "\n");
    }

    @Override
    public void close() {
        System.out.println(log);
    }
}
