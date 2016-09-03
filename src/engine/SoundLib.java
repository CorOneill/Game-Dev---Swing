package engine;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;

public class SoundLib {
	private HashMap<String, AudioClip> sounds;
	
	public SoundLib(){
		this.sounds = new HashMap<String, AudioClip>();
	}
	
	public void loadSound(String pName, String pPath){
		URL url = getClass().getClassLoader().getResource(pPath);
		
		AudioClip ac = Applet.newAudioClip(url);
		
		sounds.put(pName, ac);
	}
	
	public void playSound(String pName){
		sounds.get(pName).play();
	}
}
