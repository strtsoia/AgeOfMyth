package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Jarl extends BattleCard {

	private static Jarl jarl;

	private Jarl() {
		cost.put(GlobalDef.Resources.FOOD, 1);
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

	public static Jarl getInstance() {
		if (jarl == null) {
			jarl = new Jarl();
			return jarl;
		}

		return jarl;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Archer.contains(opponent))
			bonus = 4;
		else if (Hero.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}

}
