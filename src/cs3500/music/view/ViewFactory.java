package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ViewFactory {
    public IMusicView buildView(String fileName, String viewName) throws FileNotFoundException {
        MusicReader mr = new MusicReader();
        CompositionBuilder<GenericSong> cb = new GenericSong.Builder();
        String path = "C:\\Users\\Ari\\Dropbox\\Code\\Java\\MusicEditor\\src\\";             //TODO how to import this?

        switch (viewName) {
            case "visual":
                return new GuiViewFrame(mr.parseFile(new FileReader(path + fileName), cb));
            case "midi":
                return new MidiViewImpl(mr.parseFile(new FileReader(path + fileName), cb));
            case "console":
                return new ConsoleView(mr.parseFile(new FileReader(path + fileName), cb));
            default:
                return new GuiViewFrame(mr.parseFile(new FileReader(path + fileName), cb));
        }
    }
}
