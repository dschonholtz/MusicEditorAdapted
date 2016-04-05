package cs3500.music.view;

import cs3500.music.model.NoteRep;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface GuiView extends IMusicView {
    /**
     * Scroll the view left
     */
    void scrollLeft();

    /**
     * Scroll the view right
     */
    void scrollRight();

    /**
     * Scroll the view up
     */
    void scrollUp();

    /**
     * Scroll the view down
     */
    void scrollDown();

    /**
     * @return - This is the note at the given location regardless of whether there is one
     */
    NoteRep getNoteAtMouseLocation(Point location);

    /**
     * @return true if there is a note at the given location
     */
    boolean noteAtLocation(Point location);

    /**
     * Flips the play state from paused to playing or vice-versa
     */
    void changePlayState();

    /**
     * Adds a {@link KeyListener} with functionality to the view
     * @param keyListener the {@link KeyListener} to add to the view
     */
    void addKeyListener(KeyListener keyListener);

    /**
     * Adds a {@link MouseListener} with functionality to the view
     * @param mouseListener {@link MouseListener} to add to the view
     */
    void addMouseListener(MouseListener mouseListener);

    /**
     * Jumps the view to a specific beat in the song
     * @param beat the beat to jump to
     */
    void jumpTo(int beat);

    /**
     * @return the location of the mouse as a {@link Point}
     */
    Point getMousePosition();
}
