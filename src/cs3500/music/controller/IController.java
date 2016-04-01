package cs3500.music.controller;

/**
 * Created by dschonholtz on 4/1/2016.
 */
public interface IController {

    /**
     * This increments the beat, and calls run in the combined view at the interval desgnated by the tempo
     */
    public void incrementBeat();

    /**
     * Listens for keys... And acts on them as necessary
     * I'm not going to pretend to have read documentation here yet.
     */
    public void keyListener();

    /**
     * Called by key listener
     * Changes the play state from paused to playing or vice versa
     */
    public void changePlayState();

    /**
     * Listens for the mouse... And acts on them as necessary
     * I'm not going to pretend to have read documentation here yet.
     */
    public void mouseListener();

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
    public void jumpToBegining();


}
