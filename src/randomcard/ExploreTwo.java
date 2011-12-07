package randomcard;

import actioncard.Card;
import menuscene.ExploreScreen;
import menuscene.GameScreen;
import pulpcore.Stage;

import component.Culture;

public class ExploreTwo extends Card{

	private static ExploreTwo exploreCard;

	private int tilesNum;

	private ExploreTwo() {
		tilesNum = GameScreen.getNumOfPlayers() + 2;
	}

	public static ExploreTwo GetInstance() {
		if (exploreCard == null) {
			exploreCard = new ExploreTwo();
			return exploreCard;
		}

		return exploreCard;
	}

	public void Action(Culture player) {
		ExploreScreen eScreen = new ExploreScreen();
		eScreen.Init(tilesNum, player);
		Stage.replaceScene(eScreen);
		/*InitExploreScreen eScreen = new InitExploreScreen();
		eScreen.GenerateRomdomTiles(GameScreen.getNumOfPlayers() + 1);
		
		for(int i = 0; i < GameScreen.getNumOfPlayers(); i++){
			eScreen.Init(player);
			Stage.replaceScene(eScreen);
			int index = GameScreen.getIndex();
			index++;
			index = index % GameScreen.getNumOfPlayers();
			GameScreen.setIndex(index);
			index++;
		}*/
	}
}
