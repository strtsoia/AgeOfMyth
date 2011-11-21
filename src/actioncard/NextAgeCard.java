package actioncard;

import global.GlobalDef;
import component.Culture;
import settings.Bank;
import utility.ResourceHandler;
import menuscene.NextAgeScreen;

import java.util.Hashtable;

import pulpcore.Stage;

public class NextAgeCard extends Card {

	private static NextAgeCard nextAgeCard;

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> classic = new Hashtable<GlobalDef.Resources, Integer>();
	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> heroic = new Hashtable<GlobalDef.Resources, Integer>();
	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> mythic = new Hashtable<GlobalDef.Resources, Integer>();

	private static boolean upgradeSucess;

	private NextAgeCard() {
		classic.put(GlobalDef.Resources.WOOD, 4);
		classic.put(GlobalDef.Resources.FOOD, 4);
		classic.put(GlobalDef.Resources.GOLD, 4);
		classic.put(GlobalDef.Resources.FAVOR, 4);

		heroic.put(GlobalDef.Resources.WOOD, 5);
		heroic.put(GlobalDef.Resources.FOOD, 5);
		heroic.put(GlobalDef.Resources.GOLD, 5);
		heroic.put(GlobalDef.Resources.FAVOR, 5);

		mythic.put(GlobalDef.Resources.WOOD, 6);
		mythic.put(GlobalDef.Resources.FOOD, 6);
		mythic.put(GlobalDef.Resources.GOLD, 6);
		mythic.put(GlobalDef.Resources.FAVOR, 6);

		upgradeSucess = false;
	}

	public static NextAgeCard GetInstance() {
		if (nextAgeCard == null) {
			nextAgeCard = new NextAgeCard();
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

		NextAgeScreen nScreen = new NextAgeScreen();
		nScreen.Init(player, upgradeSucess);
		Stage.pushScene(nScreen);
	}
}
