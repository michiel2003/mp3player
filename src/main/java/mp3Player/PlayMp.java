package mp3Player;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayMp {
	MediaPlayer mediaplayer;
	
	public PlayMp() {
		com.sun.javafx.application.PlatformImpl.startup(()->{});
	}
	
	public void play(String path) {
		try {
		String file = new File(path).toURI().toString();
		Media track = new Media(file);
		mediaplayer = new MediaPlayer(track);
		mediaplayer.play();
		}catch(Exception e) {
			
		}
	}
	
	public void stop() {
		mediaplayer.stop();
		mediaplayer.dispose();
	}
	
	public void pause() {
		mediaplayer.pause();
	}
	
	public void unpause() {
		mediaplayer.play();
	}
}
