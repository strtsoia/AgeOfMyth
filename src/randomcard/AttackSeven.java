package randomcard;

import actioncard.Card;
import menuscene.PreBattleScreen;
import pulpcore.Stage;

import component.Culture;

public class AttackSeven extends Card{

	private static AttackSeven attackCard;

	private int numOfUnits;

	private AttackSeven() {
		numOfUnits = 7;
	}

	public static AttackSeven GetInstance() {
		if (attackCard == null) {
			attackCard = new AttackSeven();
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
