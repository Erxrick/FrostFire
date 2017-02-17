package games.indie.frostfire;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Resource {
	
	private static HashMap<String, Image> images;
	private static HashMap<String, Audio> sounds;
	
	public static Image get(String resource) {
		return images.get(resource + ".png");
	}
	
	public static void load() {
		System.out.println("Loading Images...");
		images = new HashMap<>();
		loadImages();
		System.out.println("Finished Loading Resources");
	}
	
	public static void loadImages() {
		File[] startLocation = new File("res/images").listFiles();
		discoverFiles(startLocation);
	}
	
	public static void discoverFiles(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            discoverFiles(file.listFiles());
	        } else {
	        	images.put(file.getName(), loadImage(file.getPath()));
	        }
	    }
	}
		
	public static Image loadImage(String path) {
		try {
			return new Image(path, false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO load placeholder image
			return null;
		}
	}
	
	public static Audio loadSound(String string) {
		try {
			return AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/audio/step.wav"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Animation build(String path, int sprite_size, String...frames) {
		SpriteSheet sprites = new SpriteSheet(loadImage(path), sprite_size, sprite_size);
		return build(sprites, sprite_size, frames);
	}
	
	public static Animation build(SpriteSheet sprites, int sprite_size, String...frames) {
		Animation animation = new Animation();
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].charAt(frames[i].length() - 1) == 'f')
				animation.addFrame(sprites.getSprite(
						Integer.parseInt(frames[i].substring(0, frames[i].length() - 1)), 0)
						.getFlippedCopy(true, false), 100);
			else
				animation.addFrame(sprites.getSprite(Integer.parseInt(frames[i]), 0), 100);
		}
		return animation;
	}


}
