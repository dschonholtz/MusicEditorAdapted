package cs3500.music.util;

import cs3500.music.model.GenericSong;
import cs3500.music.model.SongRep;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Ari on 4/2/2016.
 */
public class SongFactory {
    public SongRep buildSong(String fileName) throws FileNotFoundException {
        MusicReader mr = new MusicReader();
        CompositionBuilder<GenericSong> cb = new GenericSong.Builder();
        SongRep song = mr.parseFile(new FileReader(fileName), cb);

        return song;
    }
}
