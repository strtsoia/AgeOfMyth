package battlecard;

import java.util.Hashtable;

import global.GlobalDef;

public final class MythicNorseHero extends BattleCard{

	private static MythicNorseHero mythicNorseHero;
	
	private MythicNorseHero()
	{
		 cost.put(GlobalDef.Resources.FAVOR, 4);
		 cost.put(GlobalDef.Resources.FOOD, 4);
		 cost.put(GlobalDef.Resources.GOLD, 0);
		 cost.put(GlobalDef.Resources.WOOD, 0);
	}
	
	private final int rolls = 8;
	private int bonus = 0;
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();
	
	public int getRolls() {
		return rolls + bonus;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static MythicNorseHero getInstance()
	{
		if(mythicNorseHero == null){
			mythicNorseHero = new MythicNorseHero();
			return mythicNorseHero;
		}
		
		return mythicNorseHero;
	}
	
	public void CheckBonus(BattleCard opponent)
	{
		if (Myth.contains(opponent))
            bonus = 4;
        else
            bonus = 0;
	}
}
