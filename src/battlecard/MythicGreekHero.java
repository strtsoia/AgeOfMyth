package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class MythicGreekHero extends BattleCard {

	private static MythicGreekHero mythicGreekHero;

	private MythicGreekHero() {
		cost.put(GlobalDef.Resources.FAVOR, 4);
		cost.put(GlobalDef.Resources.GOLD, 4);
		cost.put(GlobalDef.Resources.WOOD, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}

 
	private final int rolls = 5;
	 
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

	public static MythicGreekHero getInstance() {
		if (mythicGreekHero == null) {
			mythicGreekHero = new MythicGreekHero();
			return mythicGreekHero;
		}

		return mythicGreekHero;
	}

	public void CheckBonus(BattleCard opponent) {
		bonus = 0;
	}
	
	public void GodPower(Culture attacker, Culture Defender, boolean win)
	{
		
	}

	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.Before;
	}
}
