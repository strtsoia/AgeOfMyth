package settings;

import menuscene.CultureScreen;
import menuscene.PlayerScreen;

import global.GlobalDef;
import component.*;
public class GameSetting {


	private GlobalDef.Races[] playerCulture;
	private Culture[] players;
	private int numOfPlayer;
	
	public GameSetting()
	{
		numOfPlayer = PlayerScreen.getNumber();
		playerCulture = CultureScreen.getPlayerCulture();
		players = new Culture[numOfPlayer];
		
		// Initialize Bank
		Bank.getInstance();
		for(int index = 0; index < numOfPlayer; index++)
			players[index] = new Culture(playerCulture[index]);
	}
}
