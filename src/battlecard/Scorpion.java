package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Scorpion extends BattleCard{

	private static Scorpion scorpion;
	
	private Scorpion()
	{
		 cost.put(GlobalDef.Resources.FOOD, 4);
		 cost.put(GlobalDef.Resources.GOLD, 2);
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

	public static Scorpion getInstance()
	{
		if(scorpion == null){
			scorpion = new Scorpion();
			return scorpion;
		}
		
		return scorpion;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Mortal.contains(opponent))
            bonus = 3;
        else
            bonus = 0;
	}
	
}
