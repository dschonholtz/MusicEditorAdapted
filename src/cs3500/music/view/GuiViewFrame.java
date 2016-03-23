package cs3500.music.view;

import cs3500.music.model.SongRep;
import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements IMusicView {
    private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

    /**
     * Creates new GuiView
     */
    public GuiViewFrame() {
        this.displayPanel = new ConcreteGuiViewPanel();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
    }

    public GuiViewFrame(SongRep model) {
        this.displayPanel = new ConcreteGuiViewPanel(model);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
    }

    public void initialize(){
        this.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize(){
        return displayPanel.getPreferredSize();
    }

    @Override
    public void run() {
        initialize();
    }
}
