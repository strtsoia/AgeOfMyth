package component;

import building.*;
import global.GlobalDef;

import java.util.Hashtable;

import actioncard.AttackCard;
import actioncard.*;

public class Culture {
	
	boolean isAI;
	/**
	 */
	private Board gameBoard;
	
	private Hashtable<Card, Integer> permanentcardPool = new Hashtable<Card, Integer>();
	private Hashtable<Card, Integer> randomcardPool = new Hashtable<Card, Integer>();
	private Hashtable<Card, Integer> cardHold = new Hashtable<Card, Integer>();
	
	// keep track of whether building has been constructed
	private Hashtable<Building, Boolean> b_build = new Hashtable<Building, Boolean>();
	private GlobalDef.Age currentAge;
	private GlobalDef.Races race;

	private int playerID;
	
	public Hashtable<Card, Integer> getCardHold() {
		return cardHold;
	}
	
	
	public boolean isAI() {
		return isAI;
	}


	public Hashtable<Card, Integer> getPermanentcardPool() {
		return permanentcardPool;
	}


	public Hashtable<Card, Integer> getRandomcardPool() {
		return randomcardPool;
	}


	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public GlobalDef.Age getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(GlobalDef.Age currentAge) {
		this.currentAge = currentAge;
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public Hashtable<Building, Boolean> getB_build() {
		return b_build;
	}

	public void setB_build(Hashtable<Building, Boolean> b_build) {
		this.b_build = b_build;
	}

	public Culture(GlobalDef.Races r, int id) {
		b_build.put(Armory.GetInstance(), false);
		b_build.put(Garnary.GetInstance(), false);
		b_build.put(GoldMint.GetInstance(), false);
		b_build.put(GreatTemple.GetInstance(), false);
		b_build.put(House.GetInstance(), false);
		b_build.put(Market.GetInstance(), false);
		b_build.put(Monument.GetInstance(), false);
		b_build.put(SiegeEngineWorkshop.GetInstance(), false);
		b_build.put(Quarry.GetInstance(), false);
		b_build.put(StoreHouse.GetInstance(), false);
		b_build.put(Tower.GetInstance(), false);
		b_build.put(Wall.GetInstance(), false);
		b_build.put(Wonder.GetInstance(), false);
		b_build.put(WoodWorkshop.GetInstance(), false);

		currentAge = GlobalDef.Age.Ancient;
		gameBoard = new Board(r, this);
		race = r;
		playerID = id;
		
		InitialPermanentPool();
		InitialRandomPool();
		InitialHoldCard();
	}

	public GlobalDef.Races getRace() {
		return race;
	}

	private void InitialPermanentPool() {
		permanentcardPool.put(AttackCard.GetInstance(), 2);
		permanentcardPool.put(BuildingCard.GetInstance(), 2);
		permanentcardPool.put(ExploreCard.GetInstance(), 2);
		permanentcardPool.put(GatherCard.GetInstance(), 2);
		permanentcardPool.put(NextAgeCard.GetInstance(), 2);
		permanentcardPool.put(TradeCard.GetInstance(), 2);
		permanentcardPool.put(RecruitCard.GetInstance(), 2);
	}
	
	private void InitialRandomPool()
	{
		
	}
	
	private void InitialHoldCard()
	{
		if(race == GlobalDef.Races.Egypt){
			
		}else if(race == GlobalDef.Races.Greek){
			
		}else if(race == GlobalDef.Races.Norse){
			this.cardHold.put(AttackCard.GetInstance(), 0);
			this.cardHold.put(BuildingCard.GetInstance(), 0);
			this.cardHold.put(ExploreCard.GetInstance(), 0);
			this.cardHold.put(GatherCard.GetInstance(), 0);
			this.cardHold.put(NextAgeCard.GetInstance(), 0);
			this.cardHold.put(RecruitCard.GetInstance(), 0);
			this.cardHold.put(TradeCard.GetInstance(), 0);
		}
	}
	
	public void DrawCard(Card card) {
		// update user side
		int number = cardHold.get(card);
		number++;
		this.cardHold.put(card, number);
		
		// update pool side
		int cardID = GlobalDef.getActionCardID().get(card);
		if(cardID < 7)	// permanent card, draw from permanent pool
		{
			int pNumber = this.permanentcardPool.get(card);
			pNumber--;
			this.permanentcardPool.put(card, pNumber);
		}
	}

	public void PlayCard() {

	}

	public void PassCard() {

	}

	public void BurnCard() {

	}

	public void DestroyBuilding() {

	}

	public void Retreat() {

	}

	public void RollDice() {

	}

}
