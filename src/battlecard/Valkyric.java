package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Valkyric extends BattleCard{

	private static Valkyric valkyric;
	
	private Valkyric()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 3);
		 cost.put(GlobalDef.Resources.GOLD, 1);
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

	public static Valkyric getInstance()
	{
		if(valkyric == null){
			valkyric = new Valkyric();
			return valkyric;
		}
		
		return valkyric;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Archer.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
