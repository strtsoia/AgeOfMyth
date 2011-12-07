package randomcard;

import actioncard.Card;
import global.GlobalDef;
import menuscene.DestroyBuildingScreen;
import pulpcore.Stage;
import settings.Bank;
import menuscene.GameScreen;

import component.Culture;

public class Njord extends Card{

	private static Njord buildingCard;

	private Njord() {
		
	}

	public static Njord GetInstance() {
		if (buildingCard == null) {
			buildingCard = new Njord();
			return buildingCard;
		}

		return buildingCard;
	}

	public void Action(Culture player) {
		int number = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
		if(number > 0){
			number--;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, number);
			number = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			number++;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, number);
			DestroyBuildingScreen bScreen = new DestroyBuildingScreen();
			bScreen.Init(player, GameScreen.getPlayer());
			Stage.replaceScene(bScreen);
		}
	}
}
