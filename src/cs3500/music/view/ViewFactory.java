package cs3500.music.view;

import cs3500.music.model.GenericSong;
import cs3500.music.model.SongRep;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MockReceiver;
import cs3500.music.util.MockSynth;
import cs3500.music.util.MusicReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ViewFactory {
    public IMusicView buildView(String fileName, String viewName) throws FileNotFoundException {
        MusicReader mr = new MusicReader();
        CompositionBuilder<GenericSong> cb = new GenericSong.Builder();
        SongRep song = mr.parseFile(new FileReader(fileName), cb);

        return buildView(song, viewName);
    }

    public IMusicView buildView(SongRep song, String viewName) {
        switch (viewName) {
            case "visual":
                return new GuiViewFrame(song);
            case "midi":
                return new MidiViewImpl(song);
            case "console":
                return new ConsoleView(song);
            case "test":
                StringBuilder sb = new StringBuilder();
                return new MidiViewImpl(song, new MockSynth(sb, song.getTempo()), sb);
            default:
                return new GuiViewFrame(song);
        }
    }
}
