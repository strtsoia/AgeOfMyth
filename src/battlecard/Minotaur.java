package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Minotaur extends BattleCard {

	private static Minotaur minotaur;

	private Minotaur() {
		cost.put(GlobalDef.Resources.FAVOR, 2);
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}

	/**
	 */
	private final int rolls = 5;
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

	public static Minotaur getInstance() {
		if (minotaur == null) {
			minotaur = new Minotaur();
			return minotaur;
		}

		return minotaur;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Cavalry.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}

}
