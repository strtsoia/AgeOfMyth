package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Manticore extends BattleCard{

	private static Manticore manticore;
	
	private Manticore()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 2);
		 cost.put(GlobalDef.Resources.FOOD, 2);
	}
	
	private final static int rolls = 5;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public static Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Manticore getInstance()
	{
		if(manticore == null){
			manticore = new Manticore();
			return manticore;
		}
		
		return manticore;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (GiantKiller.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
	
}
