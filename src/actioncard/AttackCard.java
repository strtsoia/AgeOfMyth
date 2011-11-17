package actioncard;

import pulpcore.Stage;
import component.Culture;
import menuscene.PreBattleScreen;

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
	
	public void Action(Culture player)
	{
		PreBattleScreen pBattle = new PreBattleScreen();
		pBattle.Init(player, numOfUnits);
		Stage.pushScene(pBattle);
	}
}
