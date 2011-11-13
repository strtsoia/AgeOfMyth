package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class HeroicGreekHero extends BattleCard{

	private static HeroicGreekHero heroicGreekHero;
	
	private HeroicGreekHero()
	{
		 cost.put(GlobalDef.Resources.FOOD, 3);
		 cost.put(GlobalDef.Resources.GOLD, 4);
		 cost.put(GlobalDef.Resources.FAVOR, 0);
		 cost.put(GlobalDef.Resources.WOOD, 0);
	}
	
	private final int rolls = 6;
	private int bonus = 0;
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
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
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Myth.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
