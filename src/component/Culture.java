package component;

import building.*;
import global.GlobalDef;

import java.util.Hashtable;
import java.util.ArrayList;

import actioncard.AttackCard;
import actioncard.*;
import battlecard.*;

public class Culture {
	
	boolean isAI;
	int status; // 0 for nothing, 1 for attack, 2 for defend
	private Board gameBoard;
	
	private Hashtable<Card, Integer> permanentcardPool = new Hashtable<Card, Integer>();
	private Hashtable<Card, Integer> randomcardPool = new Hashtable<Card, Integer>();
	private Hashtable<Card, Integer> cardHold = new Hashtable<Card, Integer>();
	
	private ArrayList<Integer> unitIDInBattle = new ArrayList<Integer>();
	
	// keep track of whether building has been constructed
	private Hashtable<Building, Boolean> b_build = new Hashtable<Building, Boolean>();
	private GlobalDef.Age currentAge;
	private GlobalDef.Races race;
	private BattleCard inRoundUnit;
	
	private int playerID;
	
	public BattleCard getInRoundUnit() {
		return inRoundUnit;
	}


	public void setInRoundUnit(BattleCard inRoundUnit) {
		this.inRoundUnit = inRoundUnit;
	}


	public Hashtable<Card, Integer> getCardHold() {
		return cardHold;
	}
	
	
	public boolean isAI() {
		return isAI;
	}

	
	public ArrayList<Integer> getUnitIDInBattle() {
		return unitIDInBattle;
	}


	public void setUnitIDInBattle(ArrayList<Integer> unitIDInBattle) {
		this.unitIDInBattle = unitIDInBattle;
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
	
	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
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
		status = 0;
		
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
		this.cardHold.put(AttackCard.GetInstance(), 0);
		this.cardHold.put(BuildingCard.GetInstance(), 0);
		this.cardHold.put(ExploreCard.GetInstance(), 0);
		this.cardHold.put(GatherCard.GetInstance(), 0);
		this.cardHold.put(NextAgeCard.GetInstance(), 0);
		this.cardHold.put(RecruitCard.GetInstance(), 0);
		this.cardHold.put(TradeCard.GetInstance(), 0);
		
	}
	
	public void DrawCard(Card card) {
		// update user side
		int number = cardHold.get(card);
		number++;
		cardHold.put(card, number);
		
		// update pool side
		int cardID = GlobalDef.getActionCardID().get(card);
		if(cardID < 7)	// permanent card, draw from permanent pool
		{
			int pNumber = this.permanentcardPool.get(card);
			pNumber--;
			permanentcardPool.put(card, pNumber);
		}
	}

	public void PlayCard(Card card) {
		int number = cardHold.get(card);
		number--;
		cardHold.put(card, number);
		
		int cardID = GlobalDef.getActionCardID().get(card);
		if(cardID < 7)	// permanent card, add to permanent pool
		{
			int pNumber = permanentcardPool.get(card);
			pNumber++;
			permanentcardPool.put(card, pNumber);
		}
		card.Action(this);
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
