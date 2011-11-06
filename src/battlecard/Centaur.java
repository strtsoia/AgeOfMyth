package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Centaur extends BattleCard{

	private static Centaur centaur;
	
	private Centaur()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 1);
		 cost.put(GlobalDef.Resources.WOOD, 3);
	}
	
	private final static int rolls = 5;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = 
				new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public static Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Centaur getInstance()
	{
		if(centaur == null){
			centaur = new Centaur();
			return centaur;
		}
		
		return centaur;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Archer.contains(opponent))
            bonus = 3;
		else if(Flyer.contains(opponent))
			bonus = 3;
        else
            bonus = 0;
	}
	
}
