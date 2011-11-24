package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Hippokon extends BattleCard {

	private static Hippokon hippokon;

	private Hippokon() {
		cost.put(GlobalDef.Resources.FOOD, 1);
		cost.put(GlobalDef.Resources.GOLD, 1);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

 
	private final int rolls = 3;
	 
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
	
	public static Hippokon getInstance() {
		if (hippokon == null) {
			hippokon = new Hippokon();
			return hippokon;
		}

		return hippokon;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Archer.contains(opponent))
			bonus = 4;
		else if (Hero.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}
	
	public void GodPower(Culture attacker, Culture Defender, boolean win)
	{
		
	}
	
	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.None;
	}

}
