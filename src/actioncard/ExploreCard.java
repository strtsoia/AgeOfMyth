package actioncard;

import global.GlobalDef;

import java.util.Hashtable;

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
	
	public void Action()
	{
		
	}
	
}
