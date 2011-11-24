package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class HeroicNorseHero extends BattleCard {

	private static HeroicNorseHero heroicNorseHero;

	private HeroicNorseHero() {
		cost.put(GlobalDef.Resources.FOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

	private final static int rolls = 6;
	 
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
	
	public static HeroicNorseHero getInstance() {
		if (heroicNorseHero == null) {
			heroicNorseHero = new HeroicNorseHero();
			return heroicNorseHero;
		}

		return heroicNorseHero;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Myth.contains(opponent))
			bonus = 4;
		else
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
