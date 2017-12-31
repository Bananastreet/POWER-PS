package com.powerps;


import java.io.*;
import javax.sound.midi.*;

public class SoundProvider
{

    protected SoundProvider()
    {
        try
        {
            midiSequencer = MidiSystem.getSequencer();
        }
        catch(MidiUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public static SoundProvider getSingleton()
    {
        return instance = instance != null ? instance : new SoundProvider();
    }

    public void playMIDI(byte data[])
    {
        if(!midiSequencer.isOpen())
            try
            {
                midiSequencer.open();
            }
            catch(MidiUnavailableException e1) { }
        if(midiSequencer.isRunning())
            midiSequencer.stop();
        try
        {
            midiSequence = MidiSystem.getSequence(new BufferedInputStream(new ByteArrayInputStream(data)));
            midiSequencer.setLoopCount(-1);
            midiSequencer.setSequence(midiSequence);
        }
        catch(InvalidMidiDataException e) { }
        catch(IOException e) { }
        midiSequencer.start();
    }

    public void stopMIDI()
    {
        midiSequencer.stop();
    }

    public Sequencer midiSequencer;
    private Sequence midiSequence;
    private static SoundProvider instance = null;

}
