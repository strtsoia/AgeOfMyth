package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

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
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(SiegeEngineWorkshop.GetInstance(), true);
		c.setB_build(table);
		
		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance().getBuildingPool();
		int numOfBuilding = bTable.get(SiegeEngineWorkshop.GetInstance());
		numOfBuilding--;
		bTable.put(SiegeEngineWorkshop.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);
		
		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), SiegeEngineWorkshop.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), SiegeEngineWorkshop.GetInstance().getCost());
	}
	
	public void UnBehavior(Culture c){
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(SiegeEngineWorkshop.GetInstance(), false);
		c.setB_build(table);
	}
}


