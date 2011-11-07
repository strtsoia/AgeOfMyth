package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Priest extends BattleCard{

	private static Priest priest;
	
	private Priest()
	{
		 cost.put(GlobalDef.Resources.FOOD, 2);
		 cost.put(GlobalDef.Resources.GOLD, 4);
	}
	
	private final int rolls = 4;
	private int bonus = 0;
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Priest getInstance()
	{
		if(priest == null){
			priest = new Priest();
			return priest;
		}
		
		return priest;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Myth.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
	
}
