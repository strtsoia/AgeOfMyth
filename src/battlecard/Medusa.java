package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Medusa extends BattleCard{

	private static Medusa medusa;
	
	private Medusa()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 3);
		 cost.put(GlobalDef.Resources.FOOD, 1);
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

	public static Medusa getInstance()
	{
		if(medusa == null){
			medusa = new Medusa();
			return medusa;
		}
		
		return medusa;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Giant.contains(opponent))
            bonus = 6;
        else
            bonus = 0;
	}
	
}
