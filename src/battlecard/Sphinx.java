package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Sphinx extends BattleCard{

	private static Sphinx sphinx;
	
	private Sphinx()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 1);
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

	public static Sphinx getInstance()
	{
		if(sphinx == null){
			sphinx = new Sphinx();
			return sphinx;
		}
		
		return sphinx;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Giant.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
	
}
