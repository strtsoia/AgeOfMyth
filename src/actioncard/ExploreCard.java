package actioncard;

import pulpcore.Stage;
import component.Culture;
import menuscene.ExploreScreen;
import menuscene.GameScreen;

public class ExploreCard extends Card{

	private static ExploreCard exploreCard;
	
	private int tilesNum;
	
	private ExploreCard()
	{
		tilesNum = GameScreen.getNumOfPlayers();
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
		eScreen.Init(tilesNum, player);
		Stage.pushScene(eScreen);
	}
	
}
