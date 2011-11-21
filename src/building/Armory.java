package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;
import component.Culture;

public class Armory extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static Armory armory;

	private Armory() {
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.WOOD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}

	public static Armory GetInstance() {
		if (armory == null) {
			armory = new Armory();
			return armory;
		} else {
			return armory;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Armory.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(Armory.GetInstance());
		numOfBuilding--;
		bTable.put(Armory.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Armory
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Armory
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Armory.GetInstance(), false);
		c.setB_build(table);
	}
}
