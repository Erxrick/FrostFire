package games.indie.frostfire.entities;

public enum StaticEntities {
	Bush(0.0001f, 0.0003f),
	CocoPLant(0.31f,0.49f),
	Crystal(0.5f,0.59f),
	Mushroom(0.6f,0.69f),
	Stone(0.7f,0.79f),
	Tree(0.8f, 0.89f),
	TreeStump(0.9f,0.95f);
	
	private float minValue;
	private float maxValue;
	
	private StaticEntities(float minValue, float maxValue){
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public float getMinvalue(){
		return minValue;
	}
	
	public float getMaxValue(){
		return maxValue;
	}
	
}
