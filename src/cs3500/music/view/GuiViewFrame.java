package cs3500.music.view;

import cs3500.music.model.NoteRep;
import cs3500.music.model.SongRep;

import java.awt.*;
import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {
    private final ConcreteGuiViewPanel displayPanel;

    /**
     * Creates new GuiView
     */
    public GuiViewFrame() {
        this.displayPanel = new ConcreteGuiViewPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
    }

    public GuiViewFrame(SongRep model) {
        this.displayPanel = new ConcreteGuiViewPanel(model);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
    }

    public void initialize() {
        this.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize(){
        return displayPanel.getPreferredSize();
    }

    @Override
    public void run() {
        initialize();
        autoScroll();
        displayPanel.repaint();
    }

    @Override
    public void changePlayState() {

    }

    @Override
    public void scrollLeft() {
        displayPanel.scrollLeft();
            repaint();
    }

    @Override
    public void scrollRight() {
        displayPanel.scrollRight();
        repaint();
    }

    @Override
    public void scrollUp() {
        displayPanel.shiftRangeUp();
        displayPanel.repaint();
    }

    @Override
    public void scrollDown() {
        displayPanel.shiftRangeDown();
        displayPanel.repaint();
    }

    @Override
    public NoteRep getNoteAtMouseLocation(Point loc) {
        return displayPanel.getNoteAtLocation(loc);
    }

    @Override
    public boolean noteAtLocation(Point loc) {
        return displayPanel.noteAtLocation(loc);
    }

    private void autoScroll() {
        //compare xWinState and current beat
        if (displayPanel.currentBeatX() >= getPreferredSize().width / 2) {
            scrollRight();
        }
    }

    @Override
    public void jumpTo(int beat) {
        displayPanel.setXWinStart(beat / 4);
    }
}
