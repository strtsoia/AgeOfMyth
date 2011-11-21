package building;

import global.GlobalDef;
import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

import component.Culture;

public class House extends Building {

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	private static House house;

	private House() {
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.FOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 0);
		cost.put(GlobalDef.Resources.FAVOR, 0);
	}

	public static House GetInstance() {
		if (house == null) {
			house = new House();
			return house;
		} else {
			return house;
		}
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Behavior(Culture c) {
		// update b_build table of player
		Hashtable<Building, Boolean> table = c.getB_build();
		table.put(House.GetInstance(), true);
		c.setB_build(table);

		// update number of villager
		int number = c.getGameBoard().getNumOfVillager();
		number++;
		c.getGameBoard().setNumOfVillager(number);

		// update building pools
		Hashtable<Building, Integer> bTable = Bank.getInstance()
				.getBuildingPool();
		int numOfBuilding = bTable.get(House.GetInstance());
		numOfBuilding--;
		bTable.put(House.GetInstance(), numOfBuilding);
		Bank.getInstance().setBuildingPool(bTable);

		// doing resource parts
		ResourceHandler.Delete(c.getGameBoard().getHoldResource(), House
				.GetInstance().getCost());
		ResourceHandler.Add(Bank.getInstance().getResourcePool(), House
				.GetInstance().getCost());

	}

	public void UnBehavior(Culture c) {
		int number = c.getGameBoard().getNumOfVillager();
		number--;
		c.getGameBoard().setNumOfVillager(number);

		if (number == 0) {
			Hashtable<Building, Boolean> table = c.getB_build();
			table.put(House.GetInstance(), false);
			c.setB_build(table);
		}
	}
}
