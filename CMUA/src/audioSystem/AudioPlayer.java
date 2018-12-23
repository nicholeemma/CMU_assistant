package audioSystem;

import java.io.File;


public class AudioPlayer {

	public static void main(String[] args) throws Exception {
	    
		TextToMp3 mp3 = new TextToMp3();
     	mp3.textToMp3("Welcome to CMUA portal! How can I help you?");
		File file = new File("Howcanihelpyou.mp3");
		Play p = new Play(file);
     	p.playAudio();
		
		Voice v = new Voice();
		v.voice();
		String result = Voice.results;
		System.out.print(result);
		Voice.results = "";
	}

}
