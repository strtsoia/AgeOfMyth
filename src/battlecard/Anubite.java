package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Anubite extends BattleCard{

	private static Anubite anubite;
	
	private Anubite()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 1);
		 cost.put(GlobalDef.Resources.GOLD, 3);
		 cost.put(GlobalDef.Resources.FOOD, 0);
		 cost.put(GlobalDef.Resources.WOOD, 0);
	}
	
	private final int rolls = 5;
	private int bonus = 0;
	private Hashtable<GlobalDef.Resources, Integer> cost = 
				new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Anubite getInstance()
	{
		if(anubite == null){
			anubite = new Anubite();
			return anubite;
		}
		
		return anubite;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Archer.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
