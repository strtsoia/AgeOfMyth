package actioncard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.BuildingScreen;
import pulpcore.Stage;
import component.Culture;
import menuscene.TradeScreen;

public class TradeCard extends Card{

	private static TradeCard tradeCard;
	
	private Hashtable<GlobalDef.Resources, Integer> cost =
			new Hashtable<GlobalDef.Resources, Integer>();
	
	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void setCost(Hashtable<GlobalDef.Resources, Integer> cost) {
		this.cost = cost;
	}
	
	private TradeCard()
	{
		
	}
	
	public static TradeCard GetInstance()
	{
		if(tradeCard == null)
		{
			tradeCard = new TradeCard();
			return tradeCard;
		}
		
		return tradeCard;
	}
	
	public boolean isRandom()
	{
		return false;
	}
	
	public void Action(Culture player)
	{
		TradeScreen tScreen = new TradeScreen();
		tScreen.Init(player);
		Stage.pushScene(tScreen);
	}
	
}
