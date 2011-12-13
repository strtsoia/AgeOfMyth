package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class Wonder extends Building {

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static Wonder wonder;

	private Wonder() {
		cost.put(GlobalDef.Resources.FAVOR, 10);
		cost.put(GlobalDef.Resources.GOLD, 10);
		cost.put(GlobalDef.Resources.WOOD, 10);
		cost.put(GlobalDef.Resources.FOOD, 10);
	}

	public static Wonder GetInstance() {
		if (wonder == null) {
			wonder = new Wonder();
			return wonder;
		} else {
			return wonder;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Wonder.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(Wonder.GetInstance());
		numOfBuilding--;
		bTable.put(Wonder.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Wonder
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Wonder
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Wonder.GetInstance(), false);
		c.setB_build(table);
	}
}
