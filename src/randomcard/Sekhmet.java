package randomcard;

import global.GlobalDef;
import actioncard.Card;
import menuscene.PreBattleScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

public class Sekhmet extends Card{

	private static Sekhmet attackCard;

	private int numOfUnits;

	private Sekhmet() {
		numOfUnits = 6;
	}

	public static Sekhmet GetInstance() {
		if (attackCard == null) {
			attackCard = new Sekhmet();
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
