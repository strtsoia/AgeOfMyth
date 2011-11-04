package building;

import global.GlobalDef;
import java.util.Hashtable;

public class Wonder extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Wonder wonder;
	
	private Wonder()
	{
		cost.put(GlobalDef.Resources.FAVOR, 4);
		cost.put(GlobalDef.Resources.GOLD, 4);
		cost.put(GlobalDef.Resources.WOOD, 4);
		cost.put(GlobalDef.Resources.FOOD, 4);
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
	
	public void Behavior()
	{
		
	}
	
	public void UnBehavior(){
		
	}
}

