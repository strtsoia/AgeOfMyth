package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Mummy extends BattleCard{

	private static Mummy mummy;
	
	private Mummy()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 2);
		 cost.put(GlobalDef.Resources.GOLD, 3);
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

	public static Mummy getInstance()
	{
		if(mummy == null){
			mummy = new Mummy();
			return mummy;
		}
		
		return mummy;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		bonus = 0;
	}
	
}
