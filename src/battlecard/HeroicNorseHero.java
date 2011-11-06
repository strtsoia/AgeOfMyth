package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class HeroicNorseHero extends BattleCard{

	private static HeroicNorseHero heroicNorseHero;
	
	private HeroicNorseHero()
	{
		 cost.put(GlobalDef.Resources.FOOD, 3);
		 cost.put(GlobalDef.Resources.GOLD, 3);
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

	public static HeroicNorseHero getInstance()
	{
		if(heroicNorseHero == null){
			heroicNorseHero = new HeroicNorseHero();
			return heroicNorseHero;
		}
		
		return heroicNorseHero;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Myth.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
	
}
