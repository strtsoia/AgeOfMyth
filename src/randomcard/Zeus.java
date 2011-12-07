package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import battlecard.ClassicalGreekHero;
import battlecard.HeroicGreekHero;
import menuscene.NextAgeScreen;
import pulpcore.Stage;
import settings.Bank;
import utility.ResourceHandler;
import actioncard.Card;

import component.Culture;

public class Zeus extends Card{

	private static Zeus nextAgeCard;

	private Hashtable<GlobalDef.Resources, Integer> classic = new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> heroic = new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> mythic = new Hashtable<GlobalDef.Resources, Integer>();

	private static boolean upgradeSucess;

	private Zeus() {
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

	public static Zeus GetInstance() {
		if (nextAgeCard == null) {
			nextAgeCard = new Zeus();
			return nextAgeCard;
		}

		upgradeSucess = false;
		return nextAgeCard;
	}

	public void Action(Culture player) {
		int number = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
		if(number > 1){
			number -= 2;
			player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, number);
			number = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
			number += 2;
			Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, number);
			
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
					player.getGameBoard().PlaceUnit(ClassicalGreekHero.getInstance());
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
					player.getGameBoard().PlaceUnit(HeroicGreekHero.getInstance());
				}
			}

			NextAgeScreen nScreen = new NextAgeScreen();
			nScreen.Init(player, upgradeSucess);
			Stage.replaceScene(nScreen);
		}
	}
}
