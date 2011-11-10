package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class Quarry extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Quarry quarry;
	
	private Quarry()
	{
		cost.put(GlobalDef.Resources.FOOD, 4);
		cost.put(GlobalDef.Resources.GOLD, 1);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}
	
	public static Quarry GetInstance()
	{
		if(quarry == null){
			quarry = new Quarry();
			return quarry;
		}else{
			return quarry;
		}
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}
	
	public void Behavior(Culture c)
	{
		
	}
	
	public void UnBehavior(Culture c){
		
	}
}


