package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

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
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Monument.GetInstance(), true);
		c.setB_build(table);
		
		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance().getBuildingPool();
		int numOfBuilding = bTable.get(Monument.GetInstance());
		numOfBuilding--;
		bTable.put(Monument.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);
		
		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Monument.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Monument.GetInstance().getCost());
	}
	
	public void UnBehavior(Culture c){
		System.out.println("mmlj");
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Monument.GetInstance(), false);
		c.setB_build(table);
	}
}

