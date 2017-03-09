package games.indie.frostfire.world;

import java.util.Random;

import games.indie.frostfire.entities.Bush;
import games.indie.frostfire.entities.CocoPlant;
import games.indie.frostfire.entities.Crystal;
import games.indie.frostfire.entities.Mushroom;
import games.indie.frostfire.entities.Stone;
import games.indie.frostfire.entities.Tree;

public class ProceduralGeneration {
	
	private World world;
	private PerlinNoise p;
	
	private final int TILE_SIZE = 16;
	private final int SPACING = TILE_SIZE * 100;
	
	private Random rand2;
	
	public ProceduralGeneration(World w){
		this.world = w;
	}
	
	public void generate(String seed) {
		generate(seedValidation(seed));
	}
	public void generate(int seed){
		this.p = new PerlinNoise(seed);
		this.rand2 = new Random(seed);
		Random rand = new Random(seed);
		for(float x=0;x<1;x+=0.01486){
			for(float y=0;y<1;y+=0.012){
				double noise = Math.abs(p.noise2(x, y));
				int posNeg = rand.nextInt(2);
				double blankSpace = rand.nextDouble();
				if(blankSpace>0.4 && blankSpace<0.8){
					if(noise<0.0002){
						generatePattern(x, y);
					}else if(noise>0.0019 && noise<0.0019 || noise>0.001 && noise<0.0095) {
						world.place(new Bush(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0 && noise<0.09 || noise>0.1 && noise<0.19) {
						world.place(new Tree(), findXorY(x, posNeg), findXorY(y, posNeg));
					}  else if(noise>0.4 && noise<0.49) {
						world.place(new Mushroom(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>1){
						generatePattern(x, y);
					} else if(noise>0.5 && noise<0.59) {
						world.place(new CocoPlant(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.59 && noise<0.69 || noise>0.79 && noise<0.89) {
						generateStonesAndCrystals(x, y, posNeg);
					}
				}
			}
		}
	}
	public float findXorY(float x, int rand){
		float result = 0;
		if(rand == 0){
			result = x*SPACING;
		} else {
			result = (0-x)*SPACING;
		}
		return result;
	}
	private void generatePattern(float x, float y){
		world.place(new Mushroom(), (x*SPACING)+8, (y*SPACING)+14);
		world.place(new Mushroom(), (x*SPACING)+40, y*SPACING);
		world.place(new Mushroom(), (x*SPACING)+40, (y*SPACING)-15);
		world.place(new Mushroom(), (x*SPACING)+35, (y*SPACING)-30);
		world.place(new Mushroom(), (x*SPACING)-24, y*SPACING);
		world.place(new Mushroom(), (x*SPACING)-24, (y*SPACING)-15);
		world.place(new Mushroom(), (x*SPACING)-19, (y*SPACING)-30);
		world.place(new Mushroom(), (x*SPACING)+8, (y*SPACING)-30);
	}
	private void generateStonesAndCrystals(float x, float y, int posNeg){
		double misc = rand2.nextDouble();
		if(misc>0 && misc<0.2){
			world.place(new Stone(), findXorY(x, posNeg), findXorY(y,posNeg));
		} else if(misc>=0.3 && misc<0.5){
			world.place(new Crystal(), findXorY(x, posNeg), findXorY(y,posNeg));
		}
	}
	
	private static int seedValidation(String seedString){
		int seed = 0;
		try{
			seed = Integer.parseInt(seedString); 
		} catch(Exception e) {
			for (int i= 0; i < seedString.length();i++) {
				char c = seedString.charAt(i);
				seed += (int)c;
			}
		}
		return seed;
	}
}
