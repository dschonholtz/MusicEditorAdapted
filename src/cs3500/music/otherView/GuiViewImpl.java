package cs3500.music.otherView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import cs3500.music.model.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewImpl extends JFrame implements GuiView {
  private ConcreteGuiViewPanel displayPanel;
  private JScrollPane scrollPane;


  /**
   * Creates new GuiView
   */
  public GuiViewImpl() {
    this.displayPanel = new ConcreteGuiViewPanel();
    this.scrollPane = null;
    initialize();
  }



  /**
   * set the Frame tobe invisible
   */
  public void initialize() {
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 500);
  }

  @Override
  public void display(Model model) {
    displayPanel.setModel(model);
    displayPanel.setSize();
    scrollPane = new JScrollPane(this.displayPanel);
    getContentPane().add(scrollPane);
    initialize();
  }

  @Override
  public ConcreteGuiViewPanel getDisplayPanel() {
    return displayPanel;
  }

  @Override
  public void repaint() {
    displayPanel.setSize();
    displayPanel.updateUI();
    scrollWindow();
    displayPanel.repaint();
  }

//  @Override
//  public void addKeyListener(KeyListener listener) {
//    displayPanel.addKeyListener(listener);
//  }

  @Override
  public void addMouseListener(MouseListener listener) {
    displayPanel.addMouseListener(listener);
  }

  @Override
  public void resetFocus() {
    displayPanel.setFocusable(true);
    displayPanel.requestFocus();
  }

  @Override
  public void controlPanel(Model model, String s) {
    JScrollBar barV = scrollPane.getVerticalScrollBar();
    JScrollBar barH = scrollPane.getHorizontalScrollBar();
    switch (s) {
      case "U":
        barV.setValue(barV.getValue() - ConcreteGuiViewPanel.GRID * 4);
        break;
      case "D":
        barV.setValue(barV.getValue() + ConcreteGuiViewPanel.GRID * 4);
        break;
      case "L":
        barH.setValue(barH.getValue() - ConcreteGuiViewPanel.GRID * 4);
        break;
      case "R":
        barH.setValue(barH.getValue() + ConcreteGuiViewPanel.GRID * 4);
        break;
      case "H":
        barH.setValue(0);
        break;
      case "E":
        barH.setValue(model.getMaxBeats() * ConcreteGuiViewPanel.GRID + getSize().width);
        break;
    }
    repaint();
  }

  public void scrollWindow() {
    int n = this.getWidth();
    if (this.displayPanel.getLineX() % n == 0) {
      scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue()
        + n);
    }
  }

  @Override
  public void reset() {
    scrollPane.getVerticalScrollBar().setValue(0);
    scrollPane.getHorizontalScrollBar().setValue(0);
    displayPanel.setLineX(ConcreteGuiViewPanel.GRID * 2);
  }

  @Override
  public void pause() {

  }

  @Override
  public void play(Model model) {
  }

  @Override
  public Note getNote(Point p) {
    return displayPanel.getNote(p);
  }

  @Override
  public JScrollPane getScrollPane() {
    return scrollPane;
  }
}
