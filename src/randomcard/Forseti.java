package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.TradeScreen;
import pulpcore.Stage;
import settings.Bank;
import actioncard.Card;
import component.Culture;

public class Forseti extends Card{

private static Forseti tradeCard;

	
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private Forseti() {

	}

	public static Forseti GetInstance() {
		if (tradeCard == null) {
			tradeCard = new Forseti();
			return tradeCard;
		}

		return tradeCard;
	}

	public void Action(Culture player) {
		int number = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
		if(number > 0){
			number -= 1;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, number);
			number = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			number += 1;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, number);
			TradeScreen tScreen = new TradeScreen();
			tScreen.Init(player, 0);
			TradeScreen.setHermes(true);
			Stage.replaceScene(tScreen);
		}
	}
}
