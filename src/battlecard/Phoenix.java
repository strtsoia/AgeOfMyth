package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Phoenix extends BattleCard {

	private static Phoenix phoenix;

	private Phoenix() {
		cost.put(GlobalDef.Resources.FAVOR, 3);
		cost.put(GlobalDef.Resources.FOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
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
	
	public void setBonus(int b)
	{
		bonus = b;
	}
	
	public int getBonus()
	{
		return bonus;
	}
	
	public static Phoenix getInstance() {
		if (phoenix == null) {
			phoenix = new Phoenix();
			return phoenix;
		}

		return phoenix;
	}

	public void CheckBonus(BattleCard opponent) {
		if (GiantKiller.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}
	
	public void GodPower(Culture attacker, Culture Defender)
	{
		
	}
	
	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.After;
	}

}
