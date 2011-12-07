package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import battlecard.Toxotes;

import actioncard.Card;

import menuscene.RecruitScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Apollo extends Card{

	private static Apollo recruitCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxRecruitNum;

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private Apollo() {
		maxRecruitNum = 4;
	}

	public static Apollo GetInstance() {
		if (recruitCard == null) {
			recruitCard = new Apollo();
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
			// gain 2 free Toxotes
			player.getGameBoard().PlaceUnit(Toxotes.getInstance());
			RecruitScreen rScreen = new RecruitScreen();
			rScreen.Init(player, maxRecruitNum, player.getRace());
			Stage.replaceScene(rScreen);
		}
	}
}
