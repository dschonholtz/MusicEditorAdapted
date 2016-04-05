package cs3500.music.view;

import cs3500.music.model.NoteRep;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface GuiView extends IMusicView {
    /**
     * Scroll left
     */
    void scrollLeft();

    /**
     * Scroll right
     */
    void scrollRight();

    /**
     * Scroll up
     */
    void scrollUp();

    /**
     * Scroll down
     */
    void scrollDown();

    /**
     * @return - This is the note at the given location regardless of whether or not there is actually a note there
     * now
     */
    NoteRep getNoteAtMouseLocation(Point location);

    /**
     * THis returns true if there is a note at the given location
     */
    boolean noteAtLocation(Point location);

    void changePlayState();

    void addKeyListener(KeyListener keyListener);
    void addMouseListener(MouseListener mouseListener);

    void jumpTo(int beat);
}
