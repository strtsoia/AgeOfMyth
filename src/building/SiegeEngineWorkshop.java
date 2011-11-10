package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class SiegeEngineWorkshop extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static SiegeEngineWorkshop se;
	
	private SiegeEngineWorkshop()
	{
		cost.put(GlobalDef.Resources.WOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}
	
	public static SiegeEngineWorkshop GetInstance()
	{
		if(se == null){
			se = new SiegeEngineWorkshop();
			return se;
		}else{
			return se;
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


