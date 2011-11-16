package actioncard;

import pulpcore.Stage;
import component.Culture;
import menuscene.GatherScreen;

public class GatherCard extends Card{

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
		GatherScreen gScreen = new GatherScreen();
		gScreen.Init(player);
		Stage.pushScene(gScreen);
	}
}
