package battlecard;

import java.util.Hashtable;

import pulpcore.Stage;

import component.Culture;
import menuscene.PharaohWadjetScreen;
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
	
	public void GodPower(Culture player, Culture opponent, boolean win)
	{
		// if has wadjet in army
		if(player.getUnitIDInBattle().contains(new Integer(11))){
			PharaohWadjetScreen pwScreen = new PharaohWadjetScreen(); 
			if(win)
			{
				pwScreen.Init(player, true);
			}else if(!win){
				pwScreen.Init(player, false);
			}
			
			Stage.pushScene(pwScreen);
		}
	}
	
	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.During;
	}


}
