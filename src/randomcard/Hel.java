package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.HelScreen;
import pulpcore.Stage;
import settings.Bank;
import actioncard.Card;
import component.Culture;

public class Hel extends Card{

	private static Hel recruitCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxRecruitNum;

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private Hel() {
		maxRecruitNum = 3;
	}

	public static Hel GetInstance() {
		if (recruitCard == null) {
			recruitCard = new Hel();
			return recruitCard;
		}

		return recruitCard;
	}

	public void Action(Culture player) {
		int number = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
		if(number > 0){
			number -= 1;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, number);
			number = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			number += 1;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, number);
			HelScreen hScreen = new HelScreen();
			hScreen.Init(player, maxRecruitNum);
			Stage.replaceScene(hScreen);
		}
	}
}
