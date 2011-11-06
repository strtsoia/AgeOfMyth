package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class ForestGiant extends BattleCard{

	private static ForestGiant frostGiant;
	
	private ForestGiant()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 2);
		 cost.put(GlobalDef.Resources.FOOD, 4);
	}
	
	private final static int rolls = 7;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public static Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static ForestGiant getInstance()
	{
		if(frostGiant == null){
			frostGiant = new ForestGiant();
			return frostGiant;
		}
		
		return frostGiant;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Warrior.contains(opponent))
            bonus = 2;
		else if(Mortal.contains(opponent))
			bonus = 3;
        else
            bonus = 0;
	}
}
