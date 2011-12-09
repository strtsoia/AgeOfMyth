package actioncard;

import pulpcore.Stage;
import component.Culture;
import menuscene.ExploreScreen;
import menuscene.GameScreen;

public class ExploreCard extends Card {

	private static ExploreCard exploreCard;

	private int tilesNum;

	private ExploreCard() {
		tilesNum = GameScreen.getNumOfPlayers() + 1;
	}

	public static ExploreCard GetInstance() {
		if (exploreCard == null) {
			exploreCard = new ExploreCard();
			return exploreCard;
		}

		return exploreCard;
	}

	public void Action(Culture player) {
		
		GameScreen.setStartPTileInit(false);
		GameScreen.setInitPTileOver(false);
		GameScreen.setNumOfTiles(GameScreen.getNumOfPlayers() + 1);
		/*ExploreScreen eScreen = new ExploreScreen();
		eScreen.Init(tilesNum, player);
		Stage.replaceScene(eScreen);*/
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
