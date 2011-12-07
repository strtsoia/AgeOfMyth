package randomcard;

import global.GlobalDef;

import java.util.Hashtable;

import battlecard.*;

import menuscene.RecruitScreen;
import pulpcore.Stage;
import settings.Bank;

import component.Culture;

import actioncard.Card;
public class RecruitOsiris extends Card{

	private static RecruitOsiris recruitCard;

	private Hashtable<GlobalDef.Resources, Integer> cost = new Hashtable<GlobalDef.Resources, Integer>();

	private int maxRecruitNum;

	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	private RecruitOsiris() {
		maxRecruitNum = 4;
	}

	public static RecruitOsiris GetInstance() {
		if (recruitCard == null) {
			recruitCard = new RecruitOsiris();
			return recruitCard;
		}

		return recruitCard;
	}

	public void Action(Culture player) {
		// decide whether can do godPower
		if(player.getCurrentAge() != GlobalDef.Age.Ancient && 
				player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR) >= 2)
		{
			if(player.getCurrentAge() == GlobalDef.Age.Classical && player.getGameBoard().getUnitsPool().get(Priest.getInstance()) > 0)
			{
				int n = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
				n = n - 2;
				player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, n);
				n = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
				n = n + 2;
				Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, n);
				player.getGameBoard().PlaceUnit(Priest.getInstance());
			}else if(player.getCurrentAge() == GlobalDef.Age.Heroic && player.getGameBoard().getUnitsPool().get(Pharaoh.getInstance()) > 0)
			{
				int n = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
				n = n - 2;
				player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, n);
				n = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
				n = n + 2;
				Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, n);
				player.getGameBoard().PlaceUnit(Pharaoh.getInstance());
			}else if(player.getCurrentAge() == GlobalDef.Age.Mythic && player.getGameBoard().getUnitsPool().get(Osiris.getInstance()) > 0)
			{
				int n = player.getGameBoard().getHoldResource().get(GlobalDef.Resources.FAVOR);
				n = n - 2;
				player.getGameBoard().getHoldResource().put(GlobalDef.Resources.FAVOR, n);
				n = Bank.getInstance().getResourcePool().get(GlobalDef.Resources.FAVOR);
				n = n + 2;
				Bank.getInstance().getResourcePool().put(GlobalDef.Resources.FAVOR, n);
				player.getGameBoard().PlaceUnit(Osiris.getInstance());
			}
		}
			
		RecruitScreen rScreen = new RecruitScreen();
		rScreen.Init(player, maxRecruitNum, player.getRace());
		Stage.replaceScene(rScreen);
	}
}
