package battlecard;

import java.util.Hashtable;

import component.Culture;

import global.GlobalDef;

public final class Pharaoh extends BattleCard {

	private static Pharaoh pharaoh;

	private Pharaoh() {
		cost.put(GlobalDef.Resources.FOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
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
	
	public static Pharaoh getInstance() {
		if (pharaoh == null) {
			pharaoh = new Pharaoh();
			return pharaoh;
		}

		return pharaoh;
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
		return GlobalDef.GodPowerTime.During;
	}


}
