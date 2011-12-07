package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import building.House;

import actioncard.Card;

import menuscene.BuildingScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Hera extends Card{

	private static Hera heraCard;

	
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private Hera() {

	}

	public static Hera GetInstance() {
		if (heraCard == null) {
			heraCard = new Hera();
			return heraCard;
		}

		return heraCard;
	}

	public void Action(Culture player) {
		int number = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
		if(number > 0){
			number -= 1;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, number);
			number = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			number += 1;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, number);
			BuildingScreen tScreen = new BuildingScreen();
			tScreen.Init(player, 3);
			player.getGameBoard().PlaceBuilding(House.GetInstance(), 0);
			Stage.replaceScene(tScreen);
		}
	}
}
