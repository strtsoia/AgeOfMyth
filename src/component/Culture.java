package component;

import building.*;
import global.GlobalDef;

import java.util.Hashtable;
import java.util.ArrayList;

import randomcard.Apollo;
import randomcard.Ares;
import randomcard.AttackFive;
import randomcard.AttackSeven;
import randomcard.AttackSix;
import randomcard.Brag;
import randomcard.BuildFour;
import randomcard.BuildThree;
import randomcard.Dionysus;
import randomcard.ExploreSame;
import randomcard.ExploreTwo;
import randomcard.Forseti;
import randomcard.Freyia;
import randomcard.GatherAll;
import randomcard.Hathor;
import randomcard.Hel;
import randomcard.Hera;
import randomcard.Hermes;
import randomcard.Horus;
import randomcard.Loki;
import randomcard.Njord;
import randomcard.Poseidon;
import randomcard.RAGather;
import randomcard.RandomNextAge;
import randomcard.RandomTrade;
import randomcard.RecruitFive;
import randomcard.RecruitFour;
import randomcard.RecruitOsiris;
import randomcard.RecruitThree;
import randomcard.Sekhmet;
import randomcard.Skadi;
import randomcard.Zeus;

import actioncard.*;
import battlecard.*;

public class Culture {
	
	boolean isAI = false;
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
		isAI = false;
		
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
		if(race == GlobalDef.Races.Egypt){
			randomcardPool.put(AttackFive.GetInstance(), 2);
			randomcardPool.put(AttackSeven.GetInstance(), 2);
			randomcardPool.put(AttackSix.GetInstance(), 2);
			randomcardPool.put(BuildFour.GetInstance(), 2);
			randomcardPool.put(BuildThree.GetInstance(), 2);
			randomcardPool.put(ExploreSame.GetInstance(), 2);
			randomcardPool.put(ExploreTwo.GetInstance(), 2);
			randomcardPool.put(GatherAll.GetInstance(), 4);
			randomcardPool.put(Hathor.GetInstance(), 1);
			randomcardPool.put(Horus.GetInstance(), 1);
			randomcardPool.put(RAGather.GetInstance(), 1);
			randomcardPool.put(RandomNextAge.GetInstance(), 2);
			randomcardPool.put(RandomTrade.GetInstance(), 4);
			randomcardPool.put(RecruitFour.GetInstance(), 2);
			randomcardPool.put(RecruitThree.GetInstance(), 2);
			randomcardPool.put(RecruitFive.GetInstance(), 2);
			randomcardPool.put(RecruitOsiris.GetInstance(), 1);
			randomcardPool.put(Sekhmet.GetInstance(), 1);
		}else if(race == GlobalDef.Races.Greek){
			randomcardPool.put(AttackFive.GetInstance(), 2);
			randomcardPool.put(AttackSix.GetInstance(), 2);
			randomcardPool.put(AttackSeven.GetInstance(), 2);
			randomcardPool.put(BuildThree.GetInstance(), 2);
			randomcardPool.put(BuildFour.GetInstance(), 2);
			randomcardPool.put(ExploreSame.GetInstance(), 2);
			randomcardPool.put(ExploreTwo.GetInstance(), 2);
			randomcardPool.put(GatherAll.GetInstance(), 4);
			randomcardPool.put(RandomNextAge.GetInstance(), 4);
			randomcardPool.put(RandomTrade.GetInstance(), 4);
			randomcardPool.put(RecruitThree.GetInstance(), 2);
			randomcardPool.put(RecruitFour.GetInstance(), 2);
			randomcardPool.put(RecruitFive.GetInstance(), 2);
			randomcardPool.put(Ares.GetInstance(), 1);
			randomcardPool.put(Hera.GetInstance(), 1);
			randomcardPool.put(Dionysus.GetInstance(), 1);
			randomcardPool.put(Poseidon.GetInstance(), 1);
			randomcardPool.put(Zeus.GetInstance(),1);
			randomcardPool.put(Apollo.GetInstance(), 1);
			randomcardPool.put(Hermes.GetInstance(), 1);
		}else if(race == GlobalDef.Races.Norse){
			randomcardPool.put(AttackFive.GetInstance(), 2);
			randomcardPool.put(AttackSix.GetInstance(), 2);
			randomcardPool.put(AttackSeven.GetInstance(), 2);
			randomcardPool.put(BuildThree.GetInstance(), 2);
			randomcardPool.put(BuildFour.GetInstance(), 2);
			randomcardPool.put(ExploreSame.GetInstance(), 2);
			randomcardPool.put(ExploreTwo.GetInstance(), 2);
			randomcardPool.put(GatherAll.GetInstance(), 4);
			randomcardPool.put(RandomTrade.GetInstance(), 4);
			randomcardPool.put(RandomNextAge.GetInstance(), 2);
			randomcardPool.put(RecruitThree.GetInstance(), 2);
			randomcardPool.put(RecruitFour.GetInstance(), 2);
			randomcardPool.put(RecruitFive.GetInstance(), 2);
			randomcardPool.put(Brag.GetInstance(), 1);
			randomcardPool.put(Njord.GetInstance(), 1);
			randomcardPool.put(Loki.GetInstance(), 1);
			randomcardPool.put(Skadi.GetInstance(), 1);
			randomcardPool.put(Forseti.GetInstance(), 1);
			randomcardPool.put(Freyia.GetInstance(), 1);
			randomcardPool.put(Hel.GetInstance(), 1);
		}
	}
	
	private void InitialHoldCard()
	{
		cardHold.put(AttackCard.GetInstance(), 0);
		cardHold.put(BuildingCard.GetInstance(), 0);
		cardHold.put(ExploreCard.GetInstance(), 0);
		cardHold.put(GatherCard.GetInstance(), 0);
		cardHold.put(NextAgeCard.GetInstance(), 0);
		cardHold.put(RecruitCard.GetInstance(), 0);
		cardHold.put(TradeCard.GetInstance(), 0);
		
		if(race == GlobalDef.Races.Egypt){
			cardHold.put(AttackFive.GetInstance(), 0);
			cardHold.put(AttackSeven.GetInstance(), 0);
			cardHold.put(AttackSix.GetInstance(), 0);
			cardHold.put(BuildFour.GetInstance(), 0);
			cardHold.put(BuildThree.GetInstance(), 0);
			cardHold.put(ExploreSame.GetInstance(), 0);
			cardHold.put(ExploreTwo.GetInstance(), 0);
			cardHold.put(GatherAll.GetInstance(), 0);
			cardHold.put(Hathor.GetInstance(), 0);
			cardHold.put(Horus.GetInstance(), 0);
			cardHold.put(RAGather.GetInstance(), 0);
			cardHold.put(RandomNextAge.GetInstance(), 0);
			cardHold.put(RandomTrade.GetInstance(), 0);
			cardHold.put(RecruitFour.GetInstance(), 0);
			cardHold.put(RecruitThree.GetInstance(), 0);
			cardHold.put(RecruitFive.GetInstance(), 0);
			cardHold.put(RecruitOsiris.GetInstance(), 0);
			cardHold.put(Sekhmet.GetInstance(), 0);
		}else if(race == GlobalDef.Races.Greek)
		{
			cardHold.put(AttackFive.GetInstance(), 0);
			cardHold.put(AttackSix.GetInstance(), 0);
			cardHold.put(AttackSeven.GetInstance(), 0);
			cardHold.put(BuildThree.GetInstance(), 0);
			cardHold.put(BuildFour.GetInstance(), 0);
			cardHold.put(ExploreSame.GetInstance(), 0);
			cardHold.put(ExploreTwo.GetInstance(), 0);
			cardHold.put(GatherAll.GetInstance(), 0);
			cardHold.put(RandomNextAge.GetInstance(), 0);
			cardHold.put(RandomTrade.GetInstance(), 0);
			cardHold.put(RecruitThree.GetInstance(), 0);
			cardHold.put(RecruitFour.GetInstance(), 0);
			cardHold.put(RecruitFive.GetInstance(), 0);
			cardHold.put(Ares.GetInstance(), 0);
			cardHold.put(Hera.GetInstance(), 0);
			cardHold.put(Dionysus.GetInstance(), 0);
			cardHold.put(Poseidon.GetInstance(), 0);
			cardHold.put(Zeus.GetInstance(),0);
			cardHold.put(Apollo.GetInstance(), 0);
			cardHold.put(Hermes.GetInstance(), 0);
			
		}else if(race == GlobalDef.Races.Norse){
			cardHold.put(AttackFive.GetInstance(), 0);
			cardHold.put(AttackSix.GetInstance(), 0);
			cardHold.put(AttackSeven.GetInstance(), 0);
			cardHold.put(BuildThree.GetInstance(), 0);
			cardHold.put(BuildFour.GetInstance(), 0);
			cardHold.put(ExploreSame.GetInstance(), 0);
			cardHold.put(ExploreTwo.GetInstance(), 0);
			cardHold.put(GatherAll.GetInstance(), 0);
			cardHold.put(RandomTrade.GetInstance(), 0);
			cardHold.put(RandomNextAge.GetInstance(), 0);
			cardHold.put(RecruitThree.GetInstance(), 0);
			cardHold.put(RecruitFour.GetInstance(), 0);
			cardHold.put(RecruitFive.GetInstance(), 0);
			cardHold.put(Brag.GetInstance(), 0);
			cardHold.put(Njord.GetInstance(), 0);
			cardHold.put(Loki.GetInstance(), 0);
			cardHold.put(Skadi.GetInstance(), 0);
			cardHold.put(Forseti.GetInstance(), 0);
			cardHold.put(Freyia.GetInstance(), 0);
			cardHold.put(Hel.GetInstance(), 0);
		}
		
	}
	
	public void DrawCard(Card card) {
		// update user side
		int number = cardHold.get(card);
		number++;
		cardHold.put(card, number);
		
		// update pool side
		int cardID = getCardID().get(card);
		if(cardID < 7)	// permanent card, draw from permanent pool
		{
			int pNumber = this.permanentcardPool.get(card);
			pNumber--;
			permanentcardPool.put(card, pNumber);
		}else{
			int rNumber = this.randomcardPool.get(card);
			rNumber--;
			this.randomcardPool.put(card, rNumber);
		}
	}

	public void PlayCard(Card card) {
		int number = cardHold.get(card);
		number--;
		cardHold.put(card, number);
		
		int cardID = getCardID().get(card);
		if(cardID < 7)	// permanent card, add to permanent pool
		{
			int pNumber = permanentcardPool.get(card);
			pNumber++;
			permanentcardPool.put(card, pNumber);
		}else{
			int rNumber = this.randomcardPool.get(card);
			rNumber--;
			this.randomcardPool.put(card, rNumber);
		}
		
		card.Action(this);
		
	}

	public void DiscardCard(Card card) {
		int number = cardHold.get(card);
		number--;
		cardHold.put(card, number);
		
		int cardID = getCardID().get(card);
		if(cardID < 7)	// permanent card, add to permanent pool
		{
			int pNumber = permanentcardPool.get(card);
			pNumber++;
			permanentcardPool.put(card, pNumber);
		}else{
			int rNumber = this.randomcardPool.get(card);
			rNumber--;
			this.randomcardPool.put(card, rNumber);
		}
	}

	public void BurnCard(Card card) {
		int number = cardHold.get(card);
		number--;
		cardHold.put(card, number);
		
		int cardID = getCardID().get(card);
		if(cardID < 7)	// permanent card, add to permanent pool
		{
			int pNumber = permanentcardPool.get(card);
			pNumber++;
			permanentcardPool.put(card, pNumber);
		}else{
			int rNumber = this.randomcardPool.get(card);
			rNumber--;
			this.randomcardPool.put(card, rNumber);
		}
	}

	public void DestroyBuilding() {

	}

	public void Retreat() {

	}

	public void RollDice() {

	}
	
	private Hashtable<Card, Integer> getCardID()
	{
		if(race == GlobalDef.Races.Egypt)
			return GlobalDef.getEgyptCardID();
		if(race == GlobalDef.Races.Greek)
			return GlobalDef.getGreekCardID();
		else
			return GlobalDef.getNorseCardID();
	}


	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}
	
	

}
