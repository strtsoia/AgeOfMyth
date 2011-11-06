package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Huskarl extends BattleCard{

	private static Huskarl huskarl;
	
	private Huskarl()
	{
		 cost.put(GlobalDef.Resources.FOOD, 1);
		 cost.put(GlobalDef.Resources.GOLD, 2);
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

	public static Huskarl getInstance()
	{
		if(huskarl == null){
			huskarl = new Huskarl();
			return huskarl;
		}
		
		return huskarl;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Cavalry.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
