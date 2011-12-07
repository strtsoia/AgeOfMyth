package randomcard;

import actioncard.Card;
import global.GlobalDef;
import menuscene.PreBattleScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Ares extends Card{

	private static Ares attackCard;

	private int numOfUnits;

	private Ares() {
		numOfUnits = 6;
	}

	public static Ares GetInstance() {
		if (attackCard == null) {
			attackCard = new Ares();
			return attackCard;
		}

		return attackCard;
	}

	public void Action(Culture player) {
		PreBattleScreen pBattle = new PreBattleScreen();
		pBattle.Init(player, numOfUnits);
		if(player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR) > 2){
			
			int n = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
			n = n - 3;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, n);
			n = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			n = n + 3;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, n);

			PreBattleScreen.setAttAddUnits(-2);
			Stage.replaceScene(pBattle);
		}
	}
}
