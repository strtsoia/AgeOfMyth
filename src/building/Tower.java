package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class Tower extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Tower tower;
	
	private Tower()
	{
		cost.put(GlobalDef.Resources.WOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}
	
	public static Tower GetInstance()
	{
		if(tower == null){
			tower = new Tower();
			return tower;
		}else{
			return tower;
		}
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}
	
	public void Behavior(Culture c)
	{
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Tower.GetInstance(), true);
		c.setB_build(table);
		
		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance().getBuildingPool();
		int numOfBuilding = bTable.get(Tower.GetInstance());
		numOfBuilding--;
		bTable.put(Tower.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);
		
		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Tower.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Tower.GetInstance().getCost());
	}
	
	public void UnBehavior(Culture c){
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Tower.GetInstance(), false);
		c.setB_build(table);
	}
}

