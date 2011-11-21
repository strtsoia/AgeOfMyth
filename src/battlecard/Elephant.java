package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Elephant extends BattleCard {

	private static Elephant elephant;

	private Elephant() {
		cost.put(GlobalDef.Resources.FOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 1);
		cost.put(GlobalDef.Resources.WOOD, 0);
		cost.put(GlobalDef.Resources.FAVOR, 0);
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

	public static Elephant getInstance() {
		if (elephant == null) {
			elephant = new Elephant();
			return elephant;
		}

		return elephant;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Mortal.contains(opponent))
			bonus = 2;
		else
			bonus = 0;
	}

}
