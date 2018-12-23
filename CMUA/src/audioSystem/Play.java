package audioSystem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class Play {

	Player player;
	File music;

	public Play(File file) {
		this.music = file;
	}

	public void playAudio() throws FileNotFoundException, JavaLayerException {

		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
		player = new Player(buffer);
		player.play();

	}
}
