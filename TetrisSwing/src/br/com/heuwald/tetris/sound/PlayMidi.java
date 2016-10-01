package br.com.heuwald.tetris.sound;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class PlayMidi {

    public PlayMidi() {
	new Thread(new Runnable() {

	    @Override
	    public synchronized void run() {
		while (true) {
		    try {
			Sequencer midi = MidiSystem.getSequencer();

			midi.open();
			midi.setSequence(MidiSystem.getSequence(getClass().getResourceAsStream("/br/com/heuwald/resources/musics/" + EnmMusicas.values()[((int) (Math.random() * EnmMusicas.values().length - 1))].getNome())));
			midi.start();

			while (true) {
			    try {
				wait(500);
			    } catch (Exception e) {
				e.printStackTrace();
			    }
			    if (!midi.isRunning()) {
				try {
				    midi.stop();
				    midi.close();
				} catch (Exception e) {
				    e.printStackTrace();
				}

				continue;
			    }
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		    try {
			wait(500);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    }
	}).start();

    }

    public static void main(String[] args) {
	new PlayMidi();
    }
}
