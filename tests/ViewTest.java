import cs3500.music.model.Note;
import cs3500.music.view.ConcreteGuiViewPanel;

import javax.swing.*;

/**
 * Created by dschonholtz on 3/22/2016.
 */
public class ViewTest {
    //Do we want to build everything through the factory for testing or no?
    JPanel guiView = new ConcreteGuiViewPanel();
    Note n = new Note(); // c4
}
