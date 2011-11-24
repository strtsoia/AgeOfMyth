package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Osiris extends BattleCard {

	private static Osiris osiris;

	private Osiris() {
		cost.put(GlobalDef.Resources.FAVOR, 4);
		cost.put(GlobalDef.Resources.GOLD, 4);
		cost.put(GlobalDef.Resources.FOOD, 0);
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
	
	public void setBonus(int b)
	{
		bonus = b;
	}
	
	public int getBonus()
	{
		return bonus;
	}
	
	public static Osiris getInstance() {
		if (osiris == null) {
			osiris = new Osiris();
			return osiris;
		}

		return osiris;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Myth.contains(opponent))
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
