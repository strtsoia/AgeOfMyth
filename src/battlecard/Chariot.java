package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class Chariot extends BattleCard{

	private static Chariot chariot;
	
	private Chariot()
	{
		 cost.put(GlobalDef.Resources.GOLD, 1);
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

	public static Chariot getInstance()
	{
		if(chariot == null){
			chariot = new Chariot();
			return chariot;
		}
		
		return chariot;
	}
	
	public static void CheckBonus(BattleCard opponent)
	{
		if (Flyer.contains(opponent))
            bonus = 3;
		else if(Warrior.contains(opponent))
			bonus = 3;
        else
            bonus = 0;
	}
	
}
