package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.RecruitScreen;
import pulpcore.Stage;
import actioncard.Card;
import component.Culture;

public class RecruitFour extends Card{

	private static RecruitFour recruitCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxRecruitNum;

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private RecruitFour() {
		maxRecruitNum = 4;
	}

	public static RecruitFour GetInstance() {
		if (recruitCard == null) {
			recruitCard = new RecruitFour();
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
