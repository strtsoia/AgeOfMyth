package randomcard;

import menuscene.PreBattleScreen;
import pulpcore.Stage;
import actioncard.Card;

import component.Culture;

public class AttackFive extends Card{

	private static AttackFive attackCard;

	private int numOfUnits;

	private AttackFive() {
		numOfUnits = 5;
	}

	public static AttackFive GetInstance() {
		if (attackCard == null) {
			attackCard = new AttackFive();
			return attackCard;
		}

		return attackCard;
	}

	public void Action(Culture player) {
		PreBattleScreen pBattle = new PreBattleScreen();
		pBattle.Init(player, numOfUnits);
		Stage.replaceScene(pBattle);
	}
}
