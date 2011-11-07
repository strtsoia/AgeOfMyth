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
	
	private final int rolls = 5;
	private int bonus = 0;
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
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
	
	public void CheckBonus(BattleCard opponent)
	{
		bonus = 0;
	}
	
}
