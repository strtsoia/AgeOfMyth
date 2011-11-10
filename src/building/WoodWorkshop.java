package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class WoodWorkshop extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static WoodWorkshop woodWorkshop;
	
	private WoodWorkshop()
	{
		cost.put(GlobalDef.Resources.FOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}
	
	public static WoodWorkshop GetInstance()
	{
		if(woodWorkshop == null){
			woodWorkshop = new WoodWorkshop();
			return woodWorkshop;
		}else{
			return woodWorkshop;
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


