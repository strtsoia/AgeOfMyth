package randomcard;

import global.GlobalDef;
import menuscene.GatherScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

import actioncard.Card;

public class RAGather extends Card{

	private static RAGather gatherCard;
	
	public static RAGather GetInstance()
	{
		if(gatherCard == null)
		{
			gatherCard = new RAGather();
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
			GatherScreen.setRA(true);
			Stage.replaceScene(gScreen);
		}
	}
}
