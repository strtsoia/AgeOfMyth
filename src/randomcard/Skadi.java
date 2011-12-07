package randomcard;

import actioncard.Card;
import global.GlobalDef;
import menuscene.GatherScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Skadi extends Card{

	private static Skadi gatherCard;
	
	public static Skadi GetInstance()
	{
		if(gatherCard == null)
		{
			gatherCard = new Skadi();
			return gatherCard;
		}
		
		return gatherCard;
	}
	
	public void Action(Culture player)
	{
		int number = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
		if(number > 0){
			number -= 1;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, number);
			number = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			number += 1;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, number);
			GatherScreen gScreen = new GatherScreen();
			gScreen.Init(player);
			GatherScreen.setSkadi(true);
			Stage.replaceScene(gScreen);
		}
	}
}
