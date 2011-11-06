package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Elephant extends BattleCard{

	private static Elephant elephant;
	
	private Elephant()
	{
		 cost.put(GlobalDef.Resources.FOOD, 2);
		 cost.put(GlobalDef.Resources.GOLD, 1);
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

	public static Elephant getInstance()
	{
		if(elephant == null){
			elephant = new Elephant();
			return elephant;
		}
		
		return elephant;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Mortal.contains(opponent))
            bonus = 2;
        else
            bonus = 0;
	}
	
}
