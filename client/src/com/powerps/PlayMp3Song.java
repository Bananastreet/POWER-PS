package com.powerps;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sign.signlink;

public class PlayMp3Song extends Application {
	
	private static MediaPlayer mediaPlayer;
	
	void playMedia() {
		String mp3 = new File(signlink.findcachedir() + "16 Astronaut - Rain (The Eden Project Remix).mp3").toURI().toString();
		Media media = new Media(mp3);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.09);
		mediaPlayer.play();
	}

	public static void main(String args[]) {
		new PlayMp3Song().playMedia();
	}

	@Override
	public void start(Stage stage) throws Exception {
	}
}
