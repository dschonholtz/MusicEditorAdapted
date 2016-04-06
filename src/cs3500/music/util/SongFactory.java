package cs3500.music.util;

import cs3500.music.model.GenericSong;
import cs3500.music.model.SongRep;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Builds a song given the name of the song assuming the text file that represents the song is in the same directory
 */
public class SongFactory {
    public SongRep buildSong(String fileName) throws FileNotFoundException {
        MusicReader mr = new MusicReader();
        CompositionBuilder<GenericSong> cb = new GenericSong.Builder();
        SongRep song = mr.parseFile(new FileReader(fileName), cb);

        return song;
    }
}
