package randomcard;

import actioncard.Card;
import menuscene.PreBattleScreen;
import pulpcore.Stage;

import component.Culture;

public class AttackSix extends Card{

	private static AttackSix attackCard;

	private int numOfUnits;

	private AttackSix() {
		numOfUnits = 6;
	}

	public static AttackSix GetInstance() {
		if (attackCard == null) {
			attackCard = new AttackSix();
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
