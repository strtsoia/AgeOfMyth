package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Spearman extends BattleCard{

	private static Spearman spearman;
	
	private Spearman()
	{
		 cost.put(GlobalDef.Resources.FOOD, 1);
		 cost.put(GlobalDef.Resources.WOOD, 1);
	}
	
	private final static int rolls = 3;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public static Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Spearman getInstance()
	{
		if(spearman == null){
			spearman = new Spearman();
			return spearman;
		}
		
		return spearman;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Cavalry.contains(opponent))
            bonus = 3;
		else if(Hero.contains(opponent))
			bonus = 4;
        else
            bonus = 0;
	}
	
}
