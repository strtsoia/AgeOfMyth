package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Troll extends BattleCard{

	private static Troll troll;
	
	private Troll()
	{
		 cost.put(GlobalDef.Resources.FOOD, 3);
		 cost.put(GlobalDef.Resources.WOOD, 2);
	}
	
	private final int rolls = 6;
	private int bonus = 0;
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Troll getInstance()
	{
		if(troll == null){
			troll = new Troll();
			return troll;
		}
		
		return troll;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Cavalry.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
