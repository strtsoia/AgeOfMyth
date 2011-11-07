package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Dwarf extends BattleCard{

	private static Dwarf dwarf;
	
	private Dwarf()
	{
		 cost.put(GlobalDef.Resources.FOOD, 2);
		 cost.put(GlobalDef.Resources.GOLD, 2);
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

	public static Dwarf getInstance()
	{
		if(dwarf == null){
			dwarf = new Dwarf();
			return dwarf;
		}
		
		return dwarf;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Giant.contains(opponent))
            bonus = 7;
        else
            bonus = 0;
	}
	
}
