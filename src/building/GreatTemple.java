package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class GreatTemple extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static GreatTemple greatTemple;
	
	private GreatTemple()
	{
		cost.put(GlobalDef.Resources.FAVOR, 4);
		cost.put(GlobalDef.Resources.GOLD, 4);
		cost.put(GlobalDef.Resources.WOOD, 4);
		cost.put(GlobalDef.Resources.FOOD, 4);
	}
	
	public static GreatTemple GetInstance()
	{
		if(greatTemple == null){
			greatTemple = new GreatTemple();
			return greatTemple;
		}else{
			return greatTemple;
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

