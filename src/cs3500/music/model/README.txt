MODEL:
GenericSong implements SongRep
    Contains the model of our design. Uses a list of notes to contain the song

Note implements NoteRep
    This contains all of the information for a single note



Differences from previous designs:

VIEW:
IMusicView
    This is the interface IMusicView it only has one method: run()
The views that implement this interface are:
    ConsoleView is our textView
    GuiViewFrame is our GUI view
    MidiViewImpl is our midi view

ConcreteGuiView contains the majority of the implementation for the GuiViewFrame however, it must remain separate due
to limitations created by the design of swing itself.

Differences between this and our previous designs:
    Ari had a design very similar to this one. Meanwhile, Doug had a list of lists where the first list's index
    represented the index of a given beat. Unfortunately, my note representation was such that it wasn't possible to
    differentiate between a sustained note and multiple consecutive beats. This also seriously damaged the models
    design as a whole.

    With efficiency in mind we starting writing code for a model similar to Ari's but with a hashMap to HashSets to
    notes. Where the hashMap had key values of the start of the note and then each hashSet was a group of notes all
    starting at that beat.

    However, we quickly discovered that we did not understand the implementation of sets and hashMaps as well as lists.

    Because of this, and the vastly larger amount of time it would take to create a new model from scratch with a
    new data structure we reverted to roughly Ari's model.

    From there we added in some implementation that were recommended to add in class & some from Doug's.

    Tempo was added, this way the tempo of the song could be tracked and accessed as needed.

    The following methods were also added:
        List<NoteRep> getNotesStartingAtT(int t)
        List<NoteRep> getNotesPlayingAtT(int t)
        int getTempo()

    Meanwhile in our note we changed the following fields and methods:
        Instrument
        Volume
        public Note()
        public int getVolume();
        public int getInstrument();
        public boolean equals(Object obj);

For the console view, we simply called the getState method from the model.

For the GUI view, we have the ConcreteGuiViewPanel that stores the song, its range of notes, its length, and constants
    for the size of notes.

For the MidiView, we iterated through every note exactly once and sent them to midi after calculating their start,
    stop, pitch, and their volume.
