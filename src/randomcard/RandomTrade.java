package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import pulpcore.Stage;
import actioncard.Card;
import menuscene.TradeScreen;
import component.Culture;

public class RandomTrade extends Card{

	private static RandomTrade tradeCard;

	
	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private RandomTrade() {

	}

	public static RandomTrade GetInstance() {
		if (tradeCard == null) {
			tradeCard = new RandomTrade();
			return tradeCard;
		}

		return tradeCard;
	}

	public void Action(Culture player) {
		TradeScreen tScreen = new TradeScreen();
		tScreen.Init(player, 1);
		Stage.replaceScene(tScreen);
	}
}
