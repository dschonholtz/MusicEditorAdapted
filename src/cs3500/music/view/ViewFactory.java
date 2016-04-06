package cs3500.music.view;

import cs3500.music.model.SongRep;

public class ViewFactory {
    public IMusicView buildView(SongRep song, String viewName) {
        switch (viewName) {
            case "visual":
                return new GuiViewFrame(song);
            case "midi":
                return new MidiViewImpl(song);
            case "console":
                return new ConsoleView(song);
            default:
                return new GuiViewFrame(song);
        }
    }
}
