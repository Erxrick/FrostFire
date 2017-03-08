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
	public ProceduralGeneration(World w){
		this.w = w;
		this.p = new PerlinNoiseGenerator(w.getWorldSeed());
	}
	public void generateWorld(int seed){
		Random rand = new Random(seed);
		for(float x=0;x<1;x+=0.01486){
			for(float y=0;y<1;y+=0.012){
				double noise = Math.abs(p.noise2(x, y));
				System.out.println(noise);
				int posNeg = rand.nextInt(2);
				if(rand.nextDouble()>0.4){
					if(noise<0.0000002){
						generatePattern(x, y);
					}else if(noise>0.8 && noise<0.89) {//
						w.place(new Crystal(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.5 && noise<0.59) {//
						w.place(new Stone(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.6 && noise<0.69 || noise>0.9 && noise<1) {
						w.place(new CocoPlant(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.0019 && noise<0.0019 || noise>0.001 && noise<0.0095) {
						w.place(new Bush(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0 && noise<0.09 || noise>0.1 && noise<0.19) {
						w.place(new Tree(), findXorY(x, posNeg), findXorY(y, posNeg));
					}  else if(noise>0.4 && noise<0.49) {
						w.place(new Mushroom(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>1){
						generatePattern(x, y);
					}
					
				}
			}
		}
	}
	public float findXorY(float x, int rand){
		float result = 0;
		if(rand == 0){
			result = x*1600;
		} else {
			result = (0-x)*1600;
		}
		return result;
	}
	public void generatePattern(float x, float y){
		w.place(new Tree(), x*1600,y*1600);
		w.place(new Mushroom(), (x*1600)+8, (y*1600)+14);
		w.place(new Mushroom(), (x*1600)+40, y*1600);
		w.place(new Mushroom(), (x*1600)+40, (y*1600)-15);
		w.place(new Mushroom(), (x*1600)+35, (y*1600)-30);
		w.place(new Mushroom(), (x*1600)-24, y*1600);
		w.place(new Mushroom(), (x*1600)-24, (y*1600)-15);
		w.place(new Mushroom(), (x*1600)-19, (y*1600)-30);
		w.place(new Mushroom(), (x*1600)+8, (y*1600)-30);
	}
}
