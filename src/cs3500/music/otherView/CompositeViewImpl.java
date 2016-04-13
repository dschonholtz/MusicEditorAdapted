package cs3500.music.otherView;

import cs3500.music.model.Model;
import cs3500.music.model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Represent a composite view, which includes both gui view and midi vies
 */
public class CompositeViewImpl implements GuiView {
  private GuiViewImpl guiView;
  private MidiViewImpl midiView;
  private Timer timer;
  private int count;

  public CompositeViewImpl() {
    this.guiView = new GuiViewImpl();
    this.midiView = new MidiViewImpl();
    this.count = 0;
  }

  @Override
  public void display(Model model) {
    guiView.display(model);
  }

  @Override
  public void pause() {
    if (timer != null)
      if (timer.isRunning()) {
        timer.stop();
      }
      else {
        timer.start();
      }
  }

  /**
   * Play the music
   */
  public void play(Model model) {
    guiView.reset();
    midiView.resetCurrentBeat();
    if (timer == null) {
      this.timer = new Timer(model.getTempo() / 20000, new ActionHandler(model));
    }
    timer.restart();
  }

  @Override
  public ConcreteGuiViewPanel getDisplayPanel() {
    return guiView.getDisplayPanel();
  }

  @Override
  public void repaint() {
    guiView.repaint();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    guiView.addKeyListener(listener);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    guiView.addMouseListener(listener);
  }

  @Override
  public void resetFocus() {
    guiView.resetFocus();
  }

  @Override
  public void controlPanel(Model model, String s) {
    guiView.controlPanel(model, s);
  }

  @Override
  public void reset() {
    guiView.reset();
  }

  @Override
  public JScrollPane getScrollPane() {
    return guiView.getScrollPane();
  }

  @Override
  public Note getNote(Point p) {
    return guiView.getNote(p);
  }

  private class ActionHandler implements ActionListener {
    private Model model;

    public ActionHandler(Model model) {
      this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      count++;

      if (timer.isRunning() && count >= 40) {
        // gui part
        guiView.getDisplayPanel().setLineX(guiView.getDisplayPanel().getLineX() + 1);
        guiView.repaint();

        // midi part
        if (count % 20 == 0) {
          midiView.displayOneBeat(model);
        }
      }
      if (midiView.getCurrentBeat() == model.getMaxBeats() + 2) {
        timer.stop();
      }
    }
  }
}
