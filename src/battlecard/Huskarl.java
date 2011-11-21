package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Huskarl extends BattleCard {

	private static Huskarl huskarl;

	private Huskarl() {
		cost.put(GlobalDef.Resources.FOOD, 1);
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

	/**
	 */
	private final int rolls = 3;
	/**
	 */
	private int bonus = 0;
	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Huskarl getInstance() {
		if (huskarl == null) {
			huskarl = new Huskarl();
			return huskarl;
		}

		return huskarl;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Cavalry.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}
}
