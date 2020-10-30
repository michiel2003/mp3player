package mp3Player;

import java.io.File;

public class MpPlayer {
	
	PlayMp player = new PlayMp();
	
	public static void main(String[] args) {
		MpPlayer exec = new MpPlayer();
		
		String path = "src/main/resources/David_Davis_-_Ocean.mp3";
		String file = new File(path).toURI().toString();
		exec.player.play(file);
	}

}
