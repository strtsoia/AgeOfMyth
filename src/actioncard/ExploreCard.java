package actioncard;

import global.GlobalDef;

import pulpcore.Stage;
import component.Culture;
import menuscene.ExploreScreen;

public class ExploreCard extends Card{

	private static ExploreCard exploreCard;
	
	private int tilesNum;
	
	private ExploreCard()
	{
		tilesNum = GlobalDef.getNumOfPlayer();
	}
	
	public static ExploreCard GetInstance()
	{
		if(exploreCard == null)
		{
			exploreCard = new ExploreCard();
			return exploreCard;
		}
		
		return exploreCard;
	}
	
	public boolean isRandom()
	{
		return false;
	}
	
	public void Action(Culture player)
	{
		ExploreScreen eScreen = new ExploreScreen();
		eScreen.Init(5, player);
		Stage.pushScene(eScreen);
	}
	
}
