package randomcard;

import actioncard.Card;
import global.GlobalDef;
import menuscene.PreBattleScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Brag extends Card{

	private static Brag attackCard;

	private int numOfUnits;

	private Brag() {
		numOfUnits = 6;
	}

	public static Brag GetInstance() {
		if (attackCard == null) {
			attackCard = new Brag();
			return attackCard;
		}

		return attackCard;
	}

	public void Action(Culture player) {
		PreBattleScreen pBattle = new PreBattleScreen();
		pBattle.Init(player, numOfUnits);
		if(player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR) > 1){
			
			int n = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
			n = n - 2;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, n);
			n = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			n = n + 2;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, n);

			PreBattleScreen.setBrag(true);
			Stage.replaceScene(pBattle);
		}
		
		
	}
}
