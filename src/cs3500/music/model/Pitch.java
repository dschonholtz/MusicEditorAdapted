package cs3500.music.model;

/** Represents the pitch of a musical note */
public enum Pitch {
    C("C"), CS("C#"), D("D"), DS("D#"), E("E"), F("F"), FS("F#"), G("G"), GS("G#"), A("A"), AS("A#"), B("B");

    private String pitchString;

    Pitch(String pitchString) {
        this.pitchString = pitchString;
    }

    String getString() { return pitchString; }
}
