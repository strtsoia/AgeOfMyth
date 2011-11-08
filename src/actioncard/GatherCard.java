package actioncard;

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
	
	public boolean isRandom()
	{
		return false;
	}
	
	public void Action()
	{
		
	}
}
