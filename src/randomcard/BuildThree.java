package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.BuildingScreen;
import pulpcore.Stage;

import component.Culture;
import actioncard.Card;

public class BuildThree extends Card{

	private static BuildThree buildingCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxBuildNumber;

	private BuildThree() {
		maxBuildNumber = 3;
	}

	public static BuildThree GetInstance() {
		if (buildingCard == null) {
			buildingCard = new BuildThree();
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
