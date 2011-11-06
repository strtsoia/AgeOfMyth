package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Toxotes extends BattleCard{

	private static Toxotes toxotes;
	
	private Toxotes()
	{
		 cost.put(GlobalDef.Resources.FOOD, 1);
		 cost.put(GlobalDef.Resources.WOOD, 1);
	}
	
	private final static int rolls = 3;
	private static int bonus = 0;
	private static Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public static int getRolls() {
		return rolls + bonus;
	}

	public static Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static Toxotes getInstance()
	{
		if(toxotes == null){
			toxotes = new Toxotes();
			return toxotes;
		}
		
		return toxotes;
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
