package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Throwing extends BattleCard{

	private static Throwing throwing;
	
	private Throwing()
	{
		 cost.put(GlobalDef.Resources.WOOD, 1);
		 cost.put(GlobalDef.Resources.FOOD, 1);
	}
	
	private final static int rolls = 3;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Throwing getInstance()
	{
		if(throwing == null){
			throwing = new Throwing();
			return throwing;
		}
		
		return throwing;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Flyer.contains(opponent))
            bonus = 4;
		else if(Warrior.contains(opponent))
			bonus = 3;
        else
            bonus = 0;
	}
	
}
