package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class ForestGiant extends BattleCard{

	private static ForestGiant frostGiant;
	
	private ForestGiant()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 2);
		 cost.put(GlobalDef.Resources.FOOD, 4);
	}
	
	private final int rolls = 7;
	private int bonus = 0;
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static ForestGiant getInstance()
	{
		if(frostGiant == null){
			frostGiant = new ForestGiant();
			return frostGiant;
		}
		
		return frostGiant;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Warrior.contains(opponent))
            bonus = 2;
		else if(Mortal.contains(opponent))
			bonus = 3;
        else
            bonus = 0;
	}
}
