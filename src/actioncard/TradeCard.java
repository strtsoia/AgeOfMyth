package actioncard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.BuildingScreen;
import pulpcore.Stage;
import component.Culture;
import menuscene.TradeScreens;

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
		TradeScreens tScreen = new TradeScreens();
		tScreen.Init(player);
		Stage.pushScene(tScreen);
	}
	
}
