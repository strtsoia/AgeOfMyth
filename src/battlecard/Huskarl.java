package battlecard;

import java.util.Hashtable;

import component.Culture;
import java.util.*;

import pulpcore.Stage;

import menuscene.BattleRoundScreen;
import global.GlobalDef;
import menuscene.BerserkScreen;

public final class Huskarl extends BattleCard {

	private static Huskarl huskarl;

	private Huskarl() {
		cost.put(GlobalDef.Resources.FOOD, 1);
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.WOOD, 0);
	}

 
	private final int rolls = 3;
	 
	private static int bonus = 0;
 
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
	
	public static Huskarl getInstance() {
		bonus = 0;
		if (huskarl == null) {
			huskarl = new Huskarl();
			return huskarl;
		}

		return huskarl;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Cavalry.contains(opponent))
			bonus = 4;
		else
			bonus = 0;
	}
	
	public void GodPower(Culture player, Culture opponent, boolean win)
	{
		boolean berserk;
		Random r = new Random();
		berserk = r.nextBoolean();
		
		if(berserk){
			BattleRoundScreen.setBerserk(true);
			// add two dice
			if(win){// attacker
				int number = BattleRoundScreen.getAttackerRolls();
				number += 2;
				BattleRoundScreen.setAttackerRolls(number);
			}else{
				int number = BattleRoundScreen.getDefenderRolls();
				number += 2;
				BattleRoundScreen.setDefenderRolls(number);
			}
			BerserkScreen bScreen = new BerserkScreen();
			Stage.pushScene(bScreen);
		}
	}
	
	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.During;
	}
}
