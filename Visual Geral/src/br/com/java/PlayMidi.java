package br.com.java;

import java.io.File;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class PlayMidi
{
	
	public PlayMidi()
	{
		try
		{
			Sequencer midi = MidiSystem.getSequencer();
			
			midi.open();
			midi.setSequence(MidiSystem.getSequence(new File(getClass().getResource("/img/teste.mid").toURI())));
			midi.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new PlayMidi();
	}
}
