package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class GreatTemple extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static GreatTemple greatTemple;

	private GreatTemple() {
		cost.put(GlobalDef.Resources.FAVOR, 4);
		cost.put(GlobalDef.Resources.GOLD, 4);
		cost.put(GlobalDef.Resources.WOOD, 4);
		cost.put(GlobalDef.Resources.FOOD, 4);
	}

	public static GreatTemple GetInstance() {
		if (greatTemple == null) {
			greatTemple = new GreatTemple();
			return greatTemple;
		} else {
			return greatTemple;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(GreatTemple.GetInstance(), true);
		c.setB_build(table);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(GreatTemple.GetInstance());
		numOfBuilding--;
		bTable.put(GreatTemple.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), GreatTemple
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), GreatTemple
				.GetInstance().getCost());
	}

	public void UnBehavior(Culture c) {
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(GreatTemple.GetInstance(), false);
		c.setB_build(table);
	}
}
