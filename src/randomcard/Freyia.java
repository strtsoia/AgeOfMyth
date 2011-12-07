package randomcard;

import actioncard.Card;
import global.GlobalDef;
import menuscene.GatherScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Freyia extends Card{

	private static Freyia gatherCard;
	
	public static Freyia GetInstance()
	{
		if(gatherCard == null)
		{
			gatherCard = new Freyia();
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
			GatherScreen.setFreyia(true);
			Stage.replaceScene(gScreen);
		}
	}
}
