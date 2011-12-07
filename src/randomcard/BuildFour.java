package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.BuildingScreen;
import pulpcore.Stage;
import actioncard.Card;
import component.Culture;

public class BuildFour extends Card{
	
	private static BuildFour buildingCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxBuildNumber;

	private BuildFour() {
		maxBuildNumber = 4;
	}

	public static BuildFour GetInstance() {
		if (buildingCard == null) {
			buildingCard = new BuildFour();
			return buildingCard;
		}

		return buildingCard;
	}

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void Action(Culture player) {
		BuildingScreen bScreen = new BuildingScreen();
		bScreen.Init(player, maxBuildNumber);
		Stage.replaceScene(bScreen);
	}
}
