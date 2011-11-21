package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class Garnary extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static Garnary garnary;

	private Garnary() {
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}

	public static Garnary GetInstance() {
		if (garnary == null) {
			garnary = new Garnary();
			return garnary;
		} else {
			return garnary;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Garnary.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(Garnary.GetInstance());
		numOfBuilding--;
		bTable.put(Garnary.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Garnary
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Garnary
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Garnary.GetInstance(), false);
		c.setB_build(table);
	}
}
