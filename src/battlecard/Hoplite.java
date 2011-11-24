package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Hoplite extends BattleCard {

	private static Hoplite hoplite;

	private Hoplite() {
		cost.put(GlobalDef.Resources.FOOD, 1);
		cost.put(GlobalDef.Resources.WOOD, 1);
		cost.put(GlobalDef.Resources.GOLD, 0);
		cost.put(GlobalDef.Resources.FAVOR, 0);
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
	
	public static Hoplite getInstance() {
		if (hoplite == null) {
			hoplite = new Hoplite();
			return hoplite;
		}

		return hoplite;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Mortal.contains(opponent))
			bonus = 1;
		else if (Cavalry.contains(opponent))
			bonus = 3;
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
