package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Osiris extends BattleCard{

	private static Osiris osiris;
	
	private Osiris()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 4);
		 cost.put(GlobalDef.Resources.GOLD, 4);
	}
	
	private final static int rolls = 8;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public static Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Osiris getInstance()
	{
		if(osiris == null){
			osiris = new Osiris();
			return osiris;
		}
		
		return osiris;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Myth.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
