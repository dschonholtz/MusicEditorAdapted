package cs3500.music.view;

import cs3500.music.model.NoteRep;

public interface GuiView extends IMusicView {
    /**
     * Scroll left
     */
    public void scrollLeft();

    /**
     * Scroll right
     */
    public void scrollRight();

    /**
     * Scroll up
     */
    public void scrollUp();

    /**
     * Scroll down
     */
    public void scrollDown();

    /**
     * @return - This is the note at the given location regardless of whether or not there is actually a note there
     * now
     */
    public NoteRep getNoteAtMouseLocation(); //TODO this needs to take in a mouse location!!!!

    /**
     * THis returns true if there is a note at the given location
     */
     boolean noteAtLocation();  //TODO this needs to take in a mouse location!!!!
}
