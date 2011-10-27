package settings;

import menuscene.PlayerScreen;
import java.io.*;
public class GameSetting {

	private int numOfPlayer;
	
	public GameSetting()
	{
		PlayerScreen p = new PlayerScreen();
		numOfPlayer = p.getNumber();
		System.out.println(numOfPlayer);
	}
}
