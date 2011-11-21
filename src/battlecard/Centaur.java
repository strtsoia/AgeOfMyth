package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Centaur extends BattleCard {

	private static Centaur centaur;

	private Centaur() {
		cost.put(GlobalDef.Resources.FAVOR, 1);
		cost.put(GlobalDef.Resources.WOOD, 3);
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

	public static Centaur getInstance() {
		if (centaur == null) {
			centaur = new Centaur();
			return centaur;
		}

		return centaur;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Archer.contains(opponent))
			bonus = 3;
		else if (Flyer.contains(opponent))
			bonus = 3;
		else
			bonus = 0;
	}

}
