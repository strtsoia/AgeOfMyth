package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Phoenix extends BattleCard{

	private static Phoenix phoenix;
	
	private Phoenix()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 3);
		 cost.put(GlobalDef.Resources.FOOD, 2);
		 cost.put(GlobalDef.Resources.GOLD, 0);
		 cost.put(GlobalDef.Resources.WOOD, 0);
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

	public static Phoenix getInstance()
	{
		if(phoenix == null){
			phoenix = new Phoenix();
			return phoenix;
		}
		
		return phoenix;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (GiantKiller.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
