package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ViewFactory {
    public IMusicView buildView(String path, String viewName) throws FileNotFoundException {
        MusicReader mr = new MusicReader();
        CompositionBuilder<GenericSong> cb = new GenericSong.Builder();

        switch (viewName) {
            case "visual":
                return new GuiViewFrame(mr.parseFile(new FileReader(path), cb));
            case "midi":
                return new MidiViewImpl(mr.parseFile(new FileReader(path), cb));
            case "console":
                return new ConsoleView(mr.parseFile(new FileReader(path), cb));
            default:
                return new GuiViewFrame(mr.parseFile(new FileReader(path), cb));
        }
    }
}
