package mariosweeper;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	//open audio file
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		
		setup(0, "Title_test");
		setup(1, "bepis_beat");
		setup(2, "bepis_beat");
		setup(3, "bepis_beat");
		setup(4, "bepis_beat");
		setup(5, "coin");
		setup(6, "jump");
		setup(7, "explosion");
		
	}
	
	public void setup(int index, String soundName) {
		
		try {
			soundURL[index] = getClass().getResource("/soundfile/"+soundName+".wav");
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		
		clip.stop();
	}
}
