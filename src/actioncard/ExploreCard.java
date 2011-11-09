package actioncard;

import global.GlobalDef;

import java.util.Hashtable;
import component.Culture;
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
		
	}
	
}
