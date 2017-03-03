package games.indie.frostfire.entities;

public enum StaticEntities {
	Bush(),
	CocoPLant(),
	Crystal(),
	Mushroom(),
	Stone(),
	Tree(),
	TreeStump();
	
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
