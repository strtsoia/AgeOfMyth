package randomcard;

import global.GlobalDef;
import menuscene.GameScreen;
import menuscene.LokiScreen;
import pulpcore.Stage;
import settings.Bank;
import actioncard.Card;

import component.Culture;

public class Loki extends Card{

	private static Loki tradeCard;
	
	public static Loki GetInstance()
	{
		if(tradeCard == null)
		{
			tradeCard = new Loki();
			return tradeCard;
		}
		
		return tradeCard;
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
			LokiScreen lScreen = new LokiScreen();
			lScreen.Init(player, GameScreen.getPlayer());
			Stage.pushScene(lScreen);
		}
	}
}
