package cs3500.music.otherView;

import cs3500.music.model.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {
  private Model model;
  private int lineX;
  public final static int GRID = 20;

  /**
   * Empty default constructor
   */
  public ConcreteGuiViewPanel() {
    setLineX(GRID * 2);
  }

  public void setModel(Model model) {
    this.model = model;
  }

  public void setSize() {
    int width = (model.getMaxBeats() + 7) * 20;
    int height = (model.getHighestPitch() - model.getLowestPitch() + 3) * 20;
    setPreferredSize(new Dimension(width, height));
  }

  public int getLineX() {
    return lineX;
  }

  public void setLineX(int x) {
    lineX = x;
  }

  public Note getNote(Point p) {
    if (model.containsKey(p.x)) {
      List<Note> notes = model.getNotesAtBeat(p.x);
      for (Note note : notes) {
        if (note.toInt() == model.getHighestPitch() - p.y) {
          return note;
        }
      }
    }
    return null;
  }

  @Override
  public void paint(Graphics g) {

    Graphics2D g2 = (Graphics2D) g;
    super.paint(g2);
    // draw notes
    for (int i = 0; i < model.getMaxBeats() + 1; i++) {
      if (model.containsKey(i)) {
        List<Note> notes = model.getNotesAtBeat(i);
        Collections.reverse(notes);
        for (Note n : notes) {
          int x = i * GRID + GRID * 2;
          int y = (model.getHighestPitch() - n.toInt()) * GRID + GRID;
          g2.setColor(Color.BLACK);
          g2.fillRect(x, y, GRID, GRID);
          g2.setColor(Color.GREEN);
          g2.fillRect(x + GRID, y, (n.getDuration() - 1) * GRID, GRID);
        }
      }
    }

    // draw the row lines and pitches
    g2.setColor(Color.BLACK);
    int totalPitches = model.getHighestPitch() - model.getLowestPitch() + 2;
    int totalBeats = (int) Math.ceil((double) (model.getMaxBeats() + 1) / 4) + 1;
    if (model.getAllNotes().isEmpty()) {
      totalPitches = 0;
      totalBeats = 0;
    }
    for (int i = 0; i < totalPitches; i++) {
      g2.drawLine(GRID * 2, i * GRID + GRID, totalBeats * GRID * 4 - GRID * 2, i * GRID + GRID);
      if (i != totalPitches - 1) {
        g2.drawString(Note.convertToNote(model.getLowestPitch() + i).toString(), 0,
          (totalPitches - i) * GRID - 5);
      }
    }

    // draw the column lines and beats
    for (int i = 0; i < totalBeats; i++) {
      g2.drawLine(i * GRID * 4 + GRID * 2, GRID, i * GRID * 4 + GRID * 2, totalPitches * GRID);
      if (i % 4 == 0) {
        g2.drawString(Integer.toString(4 * i), GRID * 2 + i * GRID * 4, 15);
      }
    }

    // draw the red line
    Stroke temp = g2.getStroke();
    g2.setStroke(new BasicStroke(2.0f));
    g2.setColor(Color.RED);
    g2.drawLine(lineX, GRID + 1, lineX,
      (model.getHighestPitch() - model.getLowestPitch() + 2) * GRID);
    g2.setStroke(temp);
    g2.setColor(Color.BLACK);
  }
}
