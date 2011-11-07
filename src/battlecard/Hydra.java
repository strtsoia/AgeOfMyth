package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Hydra extends BattleCard{

	private static Hydra hydra;
	
	private Hydra()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 2);
		 cost.put(GlobalDef.Resources.GOLD, 2);
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

	public static Hydra getInstance()
	{
		if(hydra == null){
			hydra = new Hydra();
			return hydra;
		}
		
		return hydra;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Warrior.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
	
}
