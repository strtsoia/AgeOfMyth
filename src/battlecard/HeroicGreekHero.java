package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class HeroicGreekHero extends BattleCard{

	private static HeroicGreekHero heroicGreekHero;
	
	private HeroicGreekHero()
	{
		 cost.put(GlobalDef.Resources.FOOD, 3);
		 cost.put(GlobalDef.Resources.GOLD, 4);
	}
	
	private final static int rolls = 6;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public static Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static HeroicGreekHero getInstance()
	{
		if(heroicGreekHero == null){
			heroicGreekHero = new HeroicGreekHero();
			return heroicGreekHero;
		}
		
		return heroicGreekHero;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Myth.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
