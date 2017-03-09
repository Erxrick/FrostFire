package games.indie.frostfire.world;

import java.util.Random;

import games.indie.frostfire.entities.Bush;
import games.indie.frostfire.entities.CocoPlant;
import games.indie.frostfire.entities.Crystal;
import games.indie.frostfire.entities.Mushroom;
import games.indie.frostfire.entities.Stone;
import games.indie.frostfire.entities.Tree;

public class ProceduralGeneration {
	private World w;
	private PerlinNoiseGenerator p;
	private final int CONSTANT = 1600;
	private Random rand2;
	private int seed;
	public ProceduralGeneration(World w){
		this.w = w;
		this.seed = w.getWorldSeed();
		this.p = new PerlinNoiseGenerator(seed);
		this.rand2 = new Random(seed);
	}
	public void generateWorld(int seed){
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
						w.place(new Bush(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0 && noise<0.09 || noise>0.1 && noise<0.19) {
						w.place(new Tree(), findXorY(x, posNeg), findXorY(y, posNeg));
					}  else if(noise>0.4 && noise<0.49) {
						w.place(new Mushroom(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>1){
						generatePattern(x, y);
					} else if(noise>0.5 && noise<0.59) {
						w.place(new CocoPlant(), findXorY(x, posNeg), findXorY(y, posNeg));
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
			result = x*CONSTANT;
		} else {
			result = (0-x)*CONSTANT;
		}
		return result;
	}
	private void generatePattern(float x, float y){
		w.place(new Tree(), x*CONSTANT,y*CONSTANT);
		w.place(new Mushroom(), (x*CONSTANT)+8, (y*CONSTANT)+14);
		w.place(new Mushroom(), (x*CONSTANT)+40, y*CONSTANT);
		w.place(new Mushroom(), (x*CONSTANT)+40, (y*CONSTANT)-15);
		w.place(new Mushroom(), (x*CONSTANT)+35, (y*CONSTANT)-30);
		w.place(new Mushroom(), (x*CONSTANT)-24, y*CONSTANT);
		w.place(new Mushroom(), (x*CONSTANT)-24, (y*CONSTANT)-15);
		w.place(new Mushroom(), (x*CONSTANT)-19, (y*CONSTANT)-30);
		w.place(new Mushroom(), (x*CONSTANT)+8, (y*CONSTANT)-30);
	}
	private void generateStonesAndCrystals(float x, float y, int posNeg){
		double misc = rand2.nextDouble();
		if(misc>0 && misc<0.2){
			w.place(new Stone(), findXorY(x, posNeg), findXorY(y,posNeg));
		} else if(misc>=0.3 && misc<0.5){
			w.place(new Crystal(), findXorY(x, posNeg), findXorY(y,posNeg));
		}
	}
	public static int seedValidation(String seedString){
		int seed2 = 0;
		try{
			seed2 = Integer.parseInt(seedString); 
		} catch(Exception e) {
			for (int i=0;i<seedString.length();i++) {
				char c = seedString.charAt(i);
				seed2 += (int)c;
			}
		}
		return seed2;
	}
}
