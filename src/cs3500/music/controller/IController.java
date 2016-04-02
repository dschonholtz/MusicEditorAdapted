package cs3500.music.controller;

/**
 * Created by dschonholtz on 4/1/2016.
 */
public interface IController {

    /**
     * This increments the beat, and calls run in the combined view at the interval desginated by the tempo
     */
    public void incrementBeat();

    /**
     * Called by key listener
     * Changes the play state from paused to playing or vice versa
     */
    public void changePlayState();

    /**
     * Called by mouse listener
     * Moves the note from the initial clicked location to the ending location
     */
    public void moveNote();

    /**
     * When the end key is pressed, the selection jumps to the end of the song
     */
    public void jumpToEnd();

    /**
     * When the home key is pressed, the selection jumps to the begining of the song
     */
    public void jumpToBeginning();

    /**
     * Start the program
     */
    public void run();
}
