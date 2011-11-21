package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class Market extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static Market market;

	private Market() {
		cost.put(GlobalDef.Resources.FAVOR, 2);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.WOOD, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}

	public static Market GetInstance() {
		if (market == null) {
			market = new Market();
			return market;
		} else {
			return market;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Market.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(Market.GetInstance());
		numOfBuilding--;
		bTable.put(Market.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), Market
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), Market
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(Market.GetInstance(), false);
		c.setB_build(table);
	}
}
