package cs3500.music.view;

import java.awt.event.KeyListener;

/**
 * A interface that makes sure every view can be run
 * Represents some sort of view for the music editor
 */
public interface IMusicView {
    /**
     * Creates the view-- auditory, visual or otherwise
     */
    void run();

    void addKeyListener(KeyListener keyListener);
}
