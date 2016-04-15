package cs3500.music.otherView;

import cs3500.music.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represent a sub-interface of the view
 */
public interface GuiView extends View {

  /**
   * @return the display panel
   */
  ConcreteGuiViewPanel getDisplayPanel();

  JScrollPane getScrollPane();

  /**
   * Repaint the model on the panel
   */
  void repaint();

  /**
   * Force the view to have a method to set up the keyboard
   */
  void addKeyListener(KeyListener listener);

  /**
   * Force the view to have a method to set up the mouse
   */
  void addMouseListener(MouseListener listener);

  /**
   * Reset the focus on the appropriate part of the view
   * that has the keyboard listener and mouse listener
   * attached to it, so that the events will still flow through
   */
  void resetFocus();

  /**
   * Control the panel
   */
  void controlPanel(Model model, String s);

  /**
   * Reset the editor
   */
  void reset();

  /**
   * Pause or resume the music
   */
  void pause();

  /**
   * Start the music
   */
  void play(Model model);

  /**
   * Get the note at the position
   */
  Note getNote(Point p);
}
