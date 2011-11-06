package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Hoplite extends BattleCard{

	private static Hoplite hoplite;
	
	private Hoplite()
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

	public static Hoplite getInstance()
	{
		if(hoplite == null){
			hoplite = new Hoplite();
			return hoplite;
		}
		
		return hoplite;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Mortal.contains(opponent))
            bonus = 1;
		else if(Cavalry.contains(opponent))
			bonus = 3;
        else
            bonus = 0;
	}
}
