package actioncard;

import global.GlobalDef;

import java.util.Hashtable;

import pulpcore.Stage;
import component.Culture;
import menuscene.BuildingScreen;

public class BuildingCard extends Card {

	private static BuildingCard buildingCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxBuildNumber;

	private BuildingCard() {
		maxBuildNumber = 2;
	}

	public static BuildingCard GetInstance() {
		if (buildingCard == null) {
			buildingCard = new BuildingCard();
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
