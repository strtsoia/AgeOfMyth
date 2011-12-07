package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import menuscene.GameScreen;
import menuscene.NextAgeScreen;
import pulpcore.Stage;
import settings.Bank;
import utility.ResourceHandler;
import menuscene.HathorScreen;
import component.Culture;

import actioncard.Card;

public class Hathor extends Card{

	private static Hathor nextAgeCard;

	private Hashtable<GlobalDef.Resources, Integer> classic = new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> heroic = new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> mythic = new Hashtable<GlobalDef.Resources, Integer>();

	private static boolean upgradeSucess;

	private Hathor() {
		classic.put(GlobalDef.Resources.WOOD, 3);
		classic.put(GlobalDef.Resources.FOOD, 3);
		classic.put(GlobalDef.Resources.GOLD, 3);
		classic.put(GlobalDef.Resources.FAVOR, 3);

		heroic.put(GlobalDef.Resources.WOOD, 4);
		heroic.put(GlobalDef.Resources.FOOD, 4);
		heroic.put(GlobalDef.Resources.GOLD, 4);
		heroic.put(GlobalDef.Resources.FAVOR, 4);

		mythic.put(GlobalDef.Resources.WOOD, 5);
		mythic.put(GlobalDef.Resources.FOOD, 5);
		mythic.put(GlobalDef.Resources.GOLD, 5);
		mythic.put(GlobalDef.Resources.FAVOR, 5);

		upgradeSucess = false;
	}

	public static Hathor GetInstance() {
		if (nextAgeCard == null) {
			nextAgeCard = new Hathor();
			return nextAgeCard;
		}

		upgradeSucess = false;
		return nextAgeCard;
	}

	public void Action(Culture player) {
		if (player.getCurrentAge() == GlobalDef.Age.Ancient) {
			if (ResourceHandler.isResEnough(player.getGameBoard()
					.getHoldResource(), classic)) {
				ResourceHandler.Delete(player.getGameBoard().getHoldResource(),
						classic);
				ResourceHandler.Add(Bank.getInstance().getResourcePool(),
						classic);
				player.setCurrentAge(GlobalDef.Age.Classical);
				upgradeSucess = true;
			}
		} else if (player.getCurrentAge() == GlobalDef.Age.Classical) {
			if (ResourceHandler.isResEnough(player.getGameBoard()
					.getHoldResource(), heroic)) {
				ResourceHandler.Delete(player.getGameBoard().getHoldResource(),
						heroic);
				ResourceHandler.Add(Bank.getInstance().getResourcePool(),
						heroic);
				player.setCurrentAge(GlobalDef.Age.Heroic);
				upgradeSucess = true;
			}
		} else if (player.getCurrentAge() == GlobalDef.Age.Heroic) {
			if (ResourceHandler.isResEnough(player.getGameBoard()
					.getHoldResource(), mythic)) {
				ResourceHandler.Delete(player.getGameBoard().getHoldResource(),
						mythic);
				ResourceHandler.Add(Bank.getInstance().getResourcePool(),
						mythic);
				player.setCurrentAge(GlobalDef.Age.Mythic);
				upgradeSucess = true;
			}
		}
		
		// god power
		if(player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR) > 0)
		{
			int n = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
			n--;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, n);
			n = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			n++;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, n);
			
			HathorScreen hScreen = new HathorScreen();
			Culture[] players = GameScreen.getPlayer();
			hScreen.Init(player, players);
			Stage.pushScene(hScreen);
		}
		else{
			NextAgeScreen nScreen = new NextAgeScreen();
			nScreen.Init(player, upgradeSucess);
			Stage.replaceScene(nScreen);
		}
	}
}
