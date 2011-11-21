package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Nidhogg extends BattleCard {

	private static Nidhogg nidhogg;

	private Nidhogg() {
		cost.put(GlobalDef.Resources.FAVOR, 1);
		cost.put(GlobalDef.Resources.GOLD, 4);
		cost.put(GlobalDef.Resources.FOOD, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

	/**
	 */
	private final int rolls = 7;
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

	public static Nidhogg getInstance() {
		if (nidhogg == null) {
			nidhogg = new Nidhogg();
			return nidhogg;
		}

		return nidhogg;
	}

	public void CheckBonus(BattleCard opponent) {
		if (GiantKiller.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}

}
