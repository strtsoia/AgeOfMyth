package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.RecruitScreen;
import pulpcore.Stage;
import actioncard.*;

import component.Culture;

public class RecruitThree extends Card{

	private static RecruitThree recruitCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxRecruitNum;

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private RecruitThree() {
		maxRecruitNum = 3;
	}

	public static RecruitThree GetInstance() {
		if (recruitCard == null) {
			recruitCard = new RecruitThree();
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
