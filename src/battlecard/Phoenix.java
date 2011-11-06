package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Phoenix extends BattleCard{

	private static Phoenix phoenix;
	
	private Phoenix()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 3);
		 cost.put(GlobalDef.Resources.FOOD, 2);
	}
	
	private final static int rolls = 6;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Phoenix getInstance()
	{
		if(phoenix == null){
			phoenix = new Phoenix();
			return phoenix;
		}
		
		return phoenix;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (GiantKiller.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
