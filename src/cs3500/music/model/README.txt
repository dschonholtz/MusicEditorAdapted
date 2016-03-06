This model for a Music Editor relies on the following:

Interfaces: NoteRep, SongRep
Classes: Note, GenericSong, NoteRepComparator
Enum: Pitch

NoteRep is a representation of a musical note.  It ensures that, regardless of implementation,
we will always be able to get certain details about a note.  The information deemed necessary for
this was:
    -The starting beat of the note, so we know when to play it
    -The duration, so we know how long to play it
    -The end of the note, for convenience
    -The pitch and octave, so we know what note it is
It was also deemed necessary to be able to change the note's start, octave, and pitch
while still keeping the constraints of a note (for example, non-negative beats for start time).

The Note class is this model's implementation of NoteRep.  Building off the interface, it simply
stores the start, duration, octave, and pitch so they are easily accessible.  It requires that
start be positive and that octave be between 0 and 99 (which easily encompasses human hearing).

SongRep is a representation of a song, or a collection of musical notes.  To eventually be able
to play this song, we need to be able to keep track of the beat we're on, add and remove notes.
Other added functionality is the ability to combine two songs, either so they play simultaneously
or one after the other.

The GenericSong class is this model's implementation of SongRep.  It stores its notes as a list,
and the current beat as an integer.  Lists were chosen to represent notes because they are easy to
iterate through to find the proper notes.  Also, while this is not currently the case, the list
could be sorted for more ease or efficiency-- such as by time-- if necessary.

Overlapping or duplicate notes are handled in the following way:
    -Duplicate notes will not be added by addNote
    -Overlapping notes will not be added by addNote
    -If there are two same notes playing at the same time, getState will print the first one
        it finds in the list

Pitch and octave were split up because pitches are a very limited range of notes, and many octaves
of those specific notes can be played.  This makes it easy to have pitch as an enum and octaves
simply modifying that pitch.