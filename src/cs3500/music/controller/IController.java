package cs3500.music.controller;

/**
 * Created by dschonholtz on 4/1/2016.
 */
public interface IController {

    /**
     * This increments the beat, and calls run in the combined view at the interval desginated by the tempo
     */
     void incrementBeat();

    /**
     * Called by key listener
     * Changes the play state from paused to playing or vice versa
     */
    void changePlayState();

    /**
     * When the end key is pressed, the selection jumps to the end of the song
     */
     void jumpTo(int beat);

    /**
     * Start the program
     */
    void run();
}
