package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Pharaoh extends BattleCard{

	private static Pharaoh pharaoh;
	
	private Pharaoh()
	{
		 cost.put(GlobalDef.Resources.FOOD, 3);
		 cost.put(GlobalDef.Resources.GOLD, 3);
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

	public static Pharaoh getInstance()
	{
		if(pharaoh == null){
			pharaoh = new Pharaoh();
			return pharaoh;
		}
		
		return pharaoh;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Myth.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
	
}
