package battlecard;

import java.util.Hashtable;

import pulpcore.Stage;

import component.Culture;

import global.GlobalDef;
import menuscene.BattleRoundScreen;
import menuscene.BattleScreen;
import menuscene.TiesDecisionScreen;

public final class Medusa extends BattleCard {

	private static Medusa medusa;

	private Medusa() {
		cost.put(GlobalDef.Resources.FAVOR, 3);
		cost.put(GlobalDef.Resources.FOOD, 1);
		cost.put(GlobalDef.Resources.GOLD, 0);
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
	
	public static Medusa getInstance() {
		if (medusa == null) {
			medusa = new Medusa();
			return medusa;
		}

		return medusa;
	}

	public void CheckBonus(BattleCard opponent) {
		if (Giant.contains(opponent))
			bonus = 6;
		else
			bonus = 0;
	}
	
	public void GodPower(Culture attacker, Culture Defender, boolean win)
	{
		if(win){// means attacker
			BattleScreen.removeFromDefenderGroup(BattleRoundScreen.getDefenderID());
		}else{
			BattleScreen.removeFromAttackerGroup(BattleRoundScreen.getAttackerID());
		}
		
		TiesDecisionScreen tdScreen = new TiesDecisionScreen();
		Stage.replaceScene(tdScreen);
		
		// setting UI controller
		BattleRoundScreen.setRolling(false);
		BattleRoundScreen.setFinishRound(true);
		BattleScreen.setFinish(false);
		BattleScreen.setAttackRound(true);
	}

	public GlobalDef.GodPowerTime getGodPowerTime()
	{
		return GlobalDef.GodPowerTime.Decidion;
	}
}
