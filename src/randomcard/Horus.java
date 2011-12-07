package randomcard;

import global.GlobalDef;
import menuscene.DestroyBuildingScreen;
import menuscene.GameScreen;
import pulpcore.Stage;
import settings.Bank;
import actioncard.Card;
import component.Culture;

public class Horus extends Card{
	
	private static Horus buildingCard;

	private Horus() {
		
	}

	public static Horus GetInstance() {
		if (buildingCard == null) {
			buildingCard = new Horus();
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
