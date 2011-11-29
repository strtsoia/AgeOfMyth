package actioncard;

import pulpcore.Stage;
import component.Culture;
import menuscene.InitExploreScreen;
import menuscene.GameScreen;

public class ExploreCard extends Card {

	private static ExploreCard exploreCard;

	private int tilesNum;

	private ExploreCard() {
		tilesNum = GameScreen.getNumOfPlayers();
	}

	public static ExploreCard GetInstance() {
		if (exploreCard == null) {
			exploreCard = new ExploreCard();
			return exploreCard;
		}

		return exploreCard;
	}

	public void Action(Culture player) {
		InitExploreScreen eScreen = new InitExploreScreen();
		eScreen.GenerateRomdomTiles(GameScreen.getNumOfPlayers() + 1);
		
		for(int i = 0; i < GameScreen.getNumOfPlayers(); i++){
			eScreen.Init(player);
			Stage.pushScene(eScreen);
			int index = GameScreen.getIndex();
			index++;
			index = index % GameScreen.getNumOfPlayers();
			GameScreen.setIndex(index);
			index++;
		}
	}

}
