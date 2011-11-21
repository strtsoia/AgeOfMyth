package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class Quarry extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static Quarry quarry;

	private Quarry() {
		cost.put(GlobalDef.Resources.FOOD, 4);
		cost.put(GlobalDef.Resources.GOLD, 1);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

	public static Quarry GetInstance() {
		if (quarry == null) {
			quarry = new Quarry();
			return quarry;
		} else {
			return quarry;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Quarry.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(Quarry.GetInstance());
		numOfBuilding--;
		bTable.put(Quarry.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Quarry
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Quarry
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Quarry.GetInstance(), false);
		c.setB_build(table);
	}
}
