package actioncard;

import component.Culture;

public class AttackCard extends Card{

	private static AttackCard attackCard;
	
	private int numOfUnits;
	
	private AttackCard()
	{
		numOfUnits = 4;
	}
	
	public static AttackCard GetInstance()
	{
		if(attackCard == null)
		{
			attackCard = new AttackCard();
			return attackCard;
		}
		
		return attackCard;
	}
	
	public boolean isRandom()
	{
		return false;
	}
	
	public void Action(Culture player)
	{
		
	}
}
