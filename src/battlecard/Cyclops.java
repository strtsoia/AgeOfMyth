package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Cyclops extends BattleCard{

	private static Cyclops cyclops;
	
	private Cyclops()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 3);
		 cost.put(GlobalDef.Resources.FOOD, 3);
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

	public static Cyclops getInstance()
	{
		if(cyclops == null){
			cyclops = new Cyclops();
			return cyclops;
		}
		
		return cyclops;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Mortal.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
