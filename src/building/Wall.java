package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class Wall extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static Wall wall;

	private Wall() {
		cost.put(GlobalDef.Resources.WOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}

	public static Wall GetInstance() {
		if (wall == null) {
			wall = new Wall();
			return wall;
		} else {
			return wall;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Wall.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(Wall.GetInstance());
		numOfBuilding--;
		bTable.put(Wall.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Wall
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Wall
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Wall.GetInstance(), false);
		c.setB_build(table);
	}
}
