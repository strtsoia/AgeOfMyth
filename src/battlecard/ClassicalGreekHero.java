package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class ClassicalGreekHero extends BattleCard {

	private static ClassicalGreekHero classicalGreekHero;

	private ClassicalGreekHero() {
		cost.put(GlobalDef.Resources.FOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
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

	public static ClassicalGreekHero getInstance() {
		if (classicalGreekHero == null) {
			classicalGreekHero = new ClassicalGreekHero();
			return classicalGreekHero;
		}

		return classicalGreekHero;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Myth.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}

}
