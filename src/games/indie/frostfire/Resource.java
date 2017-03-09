package games.indie.frostfire;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Loads all audio and images into the game
 * @author Wesley Barlow
 *
 */
public class Resource {
	
	private static HashMap<String, Image> images;
	private static HashMap<String, Audio> sounds;
	
	public static Image getImage(String imageName) {
		return images.get(imageName + ".png");
	}
	
	public static Audio getSound(String soundName) {
		return sounds.get(soundName + ".wav");
	}
	
	public static void play(String soundName) {
		getSound(soundName).playAsSoundEffect(1, 1, false);
	}
	
	public static void load() {
		images = new HashMap<>();
		loadImages();
		sounds = new HashMap<>();
		loadAudio();
	}
	
	public static void loadImages() {
		File[] startLocation = new File("res/images").listFiles();
		discoverImages(startLocation);
	}
	
	public static void discoverImages(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            discoverImages(file.listFiles());
	        } else {
	        	images.put(file.getName(), loadImage(file.getPath()));
	        }
	    }
	}
	
	public static void loadAudio() {
		File[] startLocation = new File("res/audio").listFiles();
		discoverAudio(startLocation);
	}
	
	public static void discoverAudio(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            discoverImages(file.listFiles());
	        } else {
	        	sounds.put(file.getName(), loadSound(file.getPath()));
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
	
	public static Audio loadSound(String path) {
		try {
			return AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static AngelCodeFont loadFont(String fntFile, Image image) {
		try  {
			return new AngelCodeFont(fntFile, image);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
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


	public static Animation flip(Animation animation) {
		Animation ani = new Animation();
		for (int i = 0; i < animation.getFrameCount(); i++) {
			ani.addFrame(animation.getImage(i).getFlippedCopy(true, false), 100);
		}
		return ani;
	}
}
