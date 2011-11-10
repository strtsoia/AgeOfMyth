package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class GoldMint extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static GoldMint goldMint;
	
	private GoldMint()
	{
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.FOOD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.GOLD, 0);
	}
	
	public static GoldMint GetInstance()
	{
		if(goldMint == null){
			goldMint = new GoldMint();
			return goldMint;
		}else{
			return goldMint;
		}
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}
	
	public void Behavior(Culture c)
	{
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(GoldMint.GetInstance(), true);
		c.setB_build(table);
		
		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance().getBuildingPool();
		int numOfBuilding = bTable.get(GoldMint.GetInstance());
		numOfBuilding--;
		bTable.put(GoldMint.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);
		
		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), GoldMint.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), GoldMint.GetInstance().getCost());
	}
	
	public void UnBehavior(Culture c){
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(GoldMint.GetInstance(), false);
		c.setB_build(table);
	}
}

