package cs3500.music.view;

import cs3500.music.model.NoteRep;
import cs3500.music.model.SongRep;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {
    private final ConcreteGuiViewPanel displayPanel;

    /**
     * Default constructor creates new GuiView
     */
    public GuiViewFrame() {
        this.displayPanel = new ConcreteGuiViewPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
    }

    /**
     *
     * @param model - the model that the view takes in
     */
    public GuiViewFrame(SongRep model) {
        Objects.requireNonNull(model);
        this.displayPanel = new ConcreteGuiViewPanel(model);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
    }

    /**
     * Makes the view visible
     */
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
        Objects.requireNonNull(loc);
        return displayPanel.getNoteAtLocation(loc);
    }

    @Override
    public boolean noteAtLocation(Point loc) {
        Objects.requireNonNull(loc);
        return displayPanel.noteAtLocation(loc);
    }

    /**
     * Scrolls the view with the current beat so the pretty red line is always on the page
     */
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
