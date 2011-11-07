package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Wadjet extends BattleCard{

	private static Wadjet wadjet;
	
	private Wadjet()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 2);
		 cost.put(GlobalDef.Resources.FOOD, 2);
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

	public static Wadjet getInstance()
	{
		if(wadjet == null){
			wadjet = new Wadjet();
			return wadjet;
		}
		
		return wadjet;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Cavalry.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
	
}
