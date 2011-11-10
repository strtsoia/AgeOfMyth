package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

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
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(WoodWorkshop.GetInstance(), true);
		c.setB_build(table);
		
		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance().getBuildingPool();
		int numOfBuilding = bTable.get(WoodWorkshop.GetInstance());
		numOfBuilding--;
		bTable.put(WoodWorkshop.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);
		
		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), WoodWorkshop.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), WoodWorkshop.GetInstance().getCost());
	}
	
	public void UnBehavior(Culture c){
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(WoodWorkshop.GetInstance(), false);
		c.setB_build(table);
	}
}


