package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class ForestGiant extends BattleCard {

	private static ForestGiant frostGiant;

	private ForestGiant() {
		cost.put(GlobalDef.Resources.FAVOR, 2);
		cost.put(GlobalDef.Resources.FOOD, 4);
		cost.put(GlobalDef.Resources.WOOD, 0);
		cost.put(GlobalDef.Resources.GOLD, 0);
	}

 
	private final int rolls = 7;
	 
	private int bonus = 0;
 
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	public int getRolls() {
		return rolls + bonus;
	}
	
	public void setBonus(int b)
	{
		bonus = b;
	}
	
	public int getBonus()
	{
		return bonus;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public static ForestGiant getInstance() {
		if (frostGiant == null) {
			frostGiant = new ForestGiant();
			return frostGiant;
		}

		return frostGiant;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Warrior.contains(opponent))
			bonus = 2;
		else if (Mortal.contains(opponent))
			bonus = 3;
		else
			bonus = 0;
	}
	
	public void GodPower(Culture attacker, Culture Defender)
	{
		
	}
	
	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.None;
	}

}
