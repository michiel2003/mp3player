package mp3Player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayMp {
	MediaPlayer mediaplayer;
	
	public PlayMp() {
		com.sun.javafx.application.PlatformImpl.startup(()->{});
	}
	
	public void play(String file) {
		Media track = new Media(file);
		mediaplayer = new MediaPlayer(track);
		mediaplayer.play();
	}
}
