package building;

import global.GlobalDef;

import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class StoreHouse extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static StoreHouse storeHouse;

	private StoreHouse() {
		cost.put(GlobalDef.Resources.FAVOR, 2);
		cost.put(GlobalDef.Resources.FOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.WOOD, 2);
	}

	public static StoreHouse GetInstance() {
		if (storeHouse == null) {
			storeHouse = new StoreHouse();
			return storeHouse;
		} else {
			return storeHouse;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(StoreHouse.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(StoreHouse.GetInstance());
		numOfBuilding--;
		bTable.put(StoreHouse.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), StoreHouse
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), StoreHouse
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(StoreHouse.GetInstance(), false);
		c.setB_build(table);
	}
}
