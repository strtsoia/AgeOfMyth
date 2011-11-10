package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class Wonder extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Wonder wonder;
	
	private Wonder()
	{
		cost.put(GlobalDef.Resources.FAVOR, 10);
		cost.put(GlobalDef.Resources.GOLD, 10);
		cost.put(GlobalDef.Resources.WOOD, 10);
		cost.put(GlobalDef.Resources.FOOD, 10);
	}
	
	public static Wonder GetInstance()
	{
		if(wonder == null){
			wonder = new Wonder();
			return wonder;
		}else{
			return wonder;
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

