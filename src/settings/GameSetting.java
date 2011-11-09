package settings;

import java.util.*;
import global.GlobalDef;
import menuscene.CultureScreen;
import menuscene.PlayerScreen;
import actioncard.*;
import component.*;
public class GameSetting {
	
	private static GameSetting iniSetting;
	private GlobalDef.Races[] playerCulture;
	private Culture[] players;
	private int numOfPlayer;
	
	private GameSetting()
	{
		numOfPlayer = PlayerScreen.getNumber();
		playerCulture = CultureScreen.getPlayerCulture();
		players = new Culture[numOfPlayer];
		
		// Initialize Bank
		Bank.getInstance();
		for(int index = 0; index < numOfPlayer; index++)
			players[index] = new Culture(playerCulture[index]);
		
		/*****************************************************************************/
		/*// print out information for each plaery's initial resource
		for(int index = 0; index < numOfPlayer; index++)
		{
			Hashtable<GlobalDef.Resources, Integer> res = players[index].getGameBoard().getHoldResource();
			
			Set<GlobalDef.Resources> kset = res.keySet();
			Iterator<GlobalDef.Resources> iter = kset.iterator();
			
			while(iter.hasNext())
			{
				GlobalDef.Resources r = iter.next();
				System.out.println(r + "   :" + res.get(r));
			}
		}
	
		// show initial bank information
		Hashtable<GlobalDef.Resources, Integer> res = Bank.getInstance().getResourcePool();
		Set<GlobalDef.Resources> kset = res.keySet();
		Iterator<GlobalDef.Resources> iter = kset.iterator();
		
		while(iter.hasNext())
		{
			GlobalDef.Resources r = iter.next();
			System.out.println(r + "in bank   :" + res.get(r));
		}
		
		// initial stage
		System.out.println("initial stage: " + players[0].getCurrentAge());
		Card c = NextAgeCard.GetInstance();
		c.Action(players[0]);
		System.out.println("After paly nexage card: " + players[0].getCurrentAge());
		System.out.println("After play nextAge, the resource player holds:  ");
		res = players[0].getGameBoard().getHoldResource();
		Iterator<GlobalDef.Resources> it = kset.iterator();
		
		while(it.hasNext())
		{
			GlobalDef.Resources r = it.next();
			System.out.println(r + "   :" + res.get(r));
		}
		
		// show initial bank information
		res = Bank.getInstance().getResourcePool();
		kset = res.keySet();
		Iterator<GlobalDef.Resources> iter1 = kset.iterator();
		
		while(iter1.hasNext())
		{
			GlobalDef.Resources r = iter1.next();
			System.out.println("After next age card, the res in bank   :" + res.get(r));
		}*/
	}
	
	public static GameSetting GetInstance()
	{
		if(iniSetting == null)
		{
			iniSetting = new GameSetting();
			return iniSetting;
		}
		
		return iniSetting;
	}
}
