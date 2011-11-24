package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Hydra extends BattleCard {

	private static Hydra hydra;

	private Hydra() {
		cost.put(GlobalDef.Resources.FAVOR, 2);
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.FOOD, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

 
	private final int rolls = 6;
	 
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

	public static Hydra getInstance() {
		if (hydra == null) {
			hydra = new Hydra();
			return hydra;
		}

		return hydra;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Warrior.contains(opponent))
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
