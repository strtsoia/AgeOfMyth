package randomcard;

import global.GlobalDef;
import actioncard.Card;
import menuscene.GatherScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Dionysus extends Card{

	private static Dionysus gatherCard;
	
	public static Dionysus GetInstance()
	{
		if(gatherCard == null)
		{
			gatherCard = new Dionysus();
			return gatherCard;
		}
		
		return gatherCard;
	}
	
	public void Action(Culture player)
	{
		int number = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
		if(number > 1){
			number -= 2;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, number);
			number = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			number += 2;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, number);
			GatherScreen gScreen = new GatherScreen();
			gScreen.Init(player);
			GatherScreen.setDionysus(true);
			Stage.replaceScene(gScreen);
		}
	}
}
