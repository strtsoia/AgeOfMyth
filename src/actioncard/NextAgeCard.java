package actioncard;

import global.GlobalDef;

import java.util.Hashtable;

public class NextAgeCard extends Card{

	private static NextAgeCard nextAgeCard;
	
	private Hashtable<GlobalDef.Resources, Integer> classic =
			new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> heroic =
			new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> mythic =
			new Hashtable<GlobalDef.Resources, Integer>();
	
	public static NextAgeCard GetInstance()
	{
		if(nextAgeCard == null)
		{
			nextAgeCard = new NextAgeCard();
			return nextAgeCard;
		}
		
		return nextAgeCard;
	}
	
	public boolean isRandom()
	{
		return false;
	}
	
	public void Action()
	{
		
	}
}
