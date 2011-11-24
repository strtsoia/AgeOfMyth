package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Valkyric extends BattleCard {

	private static Valkyric valkyric;

	private Valkyric() {
		cost.put(GlobalDef.Resources.FAVOR, 3);
		cost.put(GlobalDef.Resources.GOLD, 1);
		cost.put(GlobalDef.Resources.FOOD, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

 
	private final int rolls = 5;
 
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
	
	public static Valkyric getInstance() {
		if (valkyric == null) {
			valkyric = new Valkyric();
			return valkyric;
		}

		return valkyric;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Archer.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}
	
	public void GodPower(Culture attacker, Culture Defender, boolean win)
	{
		
	}
	
	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.After;
	}

}
