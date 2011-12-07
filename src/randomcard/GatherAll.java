package randomcard;

import menuscene.GatherAllScreen;
import pulpcore.Stage;
import actioncard.GatherCard;

import component.Culture;
import actioncard.Card;

public class GatherAll extends Card{

	private static GatherCard gatherCard;
	
	public static GatherCard GetInstance()
	{
		if(gatherCard == null)
		{
			gatherCard = new GatherCard();
			return gatherCard;
		}
		
		return gatherCard;
	}
	
	public void Action(Culture player)
	{
		GatherAllScreen gScreen = new GatherAllScreen();
		gScreen.Init(player);
		Stage.replaceScene(gScreen);
	}
}
