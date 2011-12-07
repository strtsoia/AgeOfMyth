package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import actioncard.Card;

import menuscene.RecruitScreen;
import pulpcore.Stage;

import component.Culture;

public class RecruitFive extends Card{

	private static RecruitFive recruitCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxRecruitNum;

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private RecruitFive() {
		maxRecruitNum = 5;
	}

	public static RecruitFive GetInstance() {
		if (recruitCard == null) {
			recruitCard = new RecruitFive();
			return recruitCard;
		}

		return recruitCard;
	}

	public void Action(Culture player) {
		RecruitScreen rScreen = new RecruitScreen();
		rScreen.Init(player, maxRecruitNum, player.getRace());
		Stage.replaceScene(rScreen);
	}
}
