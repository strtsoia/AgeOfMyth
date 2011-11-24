package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Toxotes extends BattleCard {

	private static Toxotes toxotes;

	private Toxotes() {
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

	public static Toxotes getInstance() {
		if (toxotes == null) {
			toxotes = new Toxotes();
			return toxotes;
		}

		return toxotes;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Flyer.contains(opponent))
			bonus = 4;
		else if (Warrior.contains(opponent))
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
