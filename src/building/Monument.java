package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class Monument extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Monument monument;
	
	private Monument()
	{
		cost.put(GlobalDef.Resources.FOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}
	
	public static Monument GetInstance()
	{
		if(monument == null){
			monument = new Monument();
			return monument;
		}else{
			return monument;
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

