package global;

import java.util.Hashtable;
import building.*;
import randomcard.*;
import tile.*;
import battlecard.*;
import actioncard.*;


/*
 * this class contains all global data
 */
public class GlobalDef {

	/**
	 * @author strtsoia
	 */
	public enum Resources {
		FOOD,
		WOOD,
		GOLD,
		FAVOR,
		VICTORY;
	}

	/**
	 * @author strtsoia
	 */
	public enum Races {
	
		Egypt,
		Greek,
		Norse
	}

	/**
	 * @author strtsoia
	 */
	public enum Terrain {
		Fertile,
		Forest,
		Hills,
		Mountains,
		Desert,
		Swamp

	}

	/**
	 * @author strtsoia
	 */
	public enum Age {
	
		Ancient,
		Classical,
		Heroic,
		Mythic
	}
	
	public enum GodPowerTime
	{
		None,
		Before,
		During,
		Decidion,
		After,
		Finish,
	}
	
	private static Hashtable<Integer, Terrain> terrainMap = new Hashtable<Integer, Terrain>();

	public static Hashtable<Integer, Terrain> getTerrainMap() {
		if (terrainMap.size() == 0) {
			terrainMap.put(0, Terrain.Fertile);
			terrainMap.put(1, Terrain.Forest);
			terrainMap.put(2, Terrain.Hills);
			terrainMap.put(3, Terrain.Mountains);
			terrainMap.put(4, Terrain.Desert);
			terrainMap.put(5, Terrain.Swamp);

			return terrainMap;
		}

		return terrainMap;
	}

	private static Hashtable<Integer, Building> buildMap = new Hashtable<Integer, Building>();

	public static Hashtable<Integer, Building> getBuildingMap() {
		if (buildMap.size() == 0) {
			buildMap.put(0, House.GetInstance());
			buildMap.put(1, Wall.GetInstance());
			buildMap.put(2, Tower.GetInstance());
			buildMap.put(3, StoreHouse.GetInstance());
			buildMap.put(4, Market.GetInstance());
			buildMap.put(5, Armory.GetInstance());
			buildMap.put(6, Quarry.GetInstance());
			buildMap.put(7, Monument.GetInstance());
			buildMap.put(8, Garnary.GetInstance());
			buildMap.put(9, GoldMint.GetInstance());
			buildMap.put(10, GreatTemple.GetInstance());
			buildMap.put(11, WoodWorkshop.GetInstance());
			buildMap.put(12, Wonder.GetInstance());
			buildMap.put(13, SiegeEngineWorkshop.GetInstance());
			return buildMap;

		}

		return buildMap;
	}

	private static Hashtable<Integer, Resources> resourceMap = new Hashtable<Integer, Resources>();

	public static Hashtable<Integer, Resources> getResourceMap() {
		if (resourceMap.size() == 0) {
			resourceMap.put(0, Resources.FOOD);
			resourceMap.put(1, Resources.FAVOR);
			resourceMap.put(2, Resources.WOOD);
			resourceMap.put(3, Resources.GOLD);
			resourceMap.put(4, Resources.VICTORY);

			return resourceMap;

		}

		return resourceMap;
	}

	private static Hashtable<Integer, ResProduceTile> tileMap = new Hashtable<Integer, ResProduceTile>();

	public static Hashtable<Integer, ResProduceTile> getTileMap() {
		if (tileMap.size() == 0) {
			tileMap.put(0, FertileA.GetInstance());
			tileMap.put(1, FertileB.GetInstance());
			tileMap.put(2, FertileC.GetInstance());
			tileMap.put(3, FertileD.GetInstance());
			tileMap.put(4, ForestA.GetInstance());
			tileMap.put(5, ForestB.GetInstance());
			tileMap.put(6, ForestC.GetInstance());
			tileMap.put(7, ForestD.GetInstance());
			tileMap.put(8, HillsA.GetInstance());
			tileMap.put(9, HillsB.GetInstance());
			tileMap.put(10, HillsC.GetInstance());
			tileMap.put(11, HillsD.GetInstance());
			tileMap.put(12, MountainA.GetInstance());
			tileMap.put(13, MountainB.GetInstance());
			tileMap.put(14, MountainC.GetInstance());
			tileMap.put(15, DesertA.GetInstance());
			tileMap.put(16, DesertB.GetInstance());
			tileMap.put(17, SwampA.GetInstance());
			tileMap.put(18, SwampB.GetInstance());
			tileMap.put(19, SwampC.GetInstance());

			return tileMap;
		}

		return tileMap;
	}

	private static Hashtable<Integer, BattleCard> egyptBattleCard = new Hashtable<Integer, BattleCard>();

	public static Hashtable<Integer, BattleCard> getEgyptBattleCard() {
		if (egyptBattleCard.size() == 0) {
			egyptBattleCard.put(0, Anubite.getInstance());
			egyptBattleCard.put(1, Chariot.getInstance());
			egyptBattleCard.put(2, Elephant.getInstance());
			egyptBattleCard.put(3, Mummy.getInstance());
			egyptBattleCard.put(4, Pharaoh.getInstance());
			egyptBattleCard.put(5, Phoenix.getInstance());
			egyptBattleCard.put(6, Priest.getInstance());
			egyptBattleCard.put(7, Scorpion.getInstance());
			egyptBattleCard.put(8, Osiris.getInstance());
			egyptBattleCard.put(9, Spearman.getInstance());
			egyptBattleCard.put(10, Sphinx.getInstance());
			egyptBattleCard.put(11, Wadjet.getInstance());

			return egyptBattleCard;
		}

		return egyptBattleCard;
	}

	private static Hashtable<BattleCard, Integer> egyptUnitsID = new Hashtable<BattleCard, Integer>();

	public static Hashtable<BattleCard, Integer> getEgyPtUnitsID() {
		if (egyptUnitsID.size() == 0) {
			egyptUnitsID.put(Anubite.getInstance(), 0);
			egyptUnitsID.put(Chariot.getInstance(), 1);
			egyptUnitsID.put(Elephant.getInstance(), 2);
			egyptUnitsID.put(Mummy.getInstance(), 3);
			egyptUnitsID.put(Pharaoh.getInstance(), 4);
			egyptUnitsID.put(Phoenix.getInstance(), 5);
			egyptUnitsID.put(Priest.getInstance(), 6);
			egyptUnitsID.put(Scorpion.getInstance(), 7);
			egyptUnitsID.put(Osiris.getInstance(), 8);
			egyptUnitsID.put(Spearman.getInstance(), 9);
			egyptUnitsID.put(Sphinx.getInstance(), 10);
			egyptUnitsID.put(Wadjet.getInstance(), 11);

			return egyptUnitsID;
		}

		return egyptUnitsID;
	}

	private static Hashtable<Integer, BattleCard> greekBattleCard = new Hashtable<Integer, BattleCard>();

	public static Hashtable<Integer, BattleCard> getGreekBattleCard() {
		if (greekBattleCard.size() == 0) {
			greekBattleCard.put(0, Centaur.getInstance());
			greekBattleCard.put(1, ClassicalGreekHero.getInstance());
			greekBattleCard.put(2, Cyclops.getInstance());
			greekBattleCard.put(3, HeroicGreekHero.getInstance());
			greekBattleCard.put(4, Hippokon.getInstance());
			greekBattleCard.put(5, Hoplite.getInstance());
			greekBattleCard.put(6, Hydra.getInstance());
			greekBattleCard.put(7, Manticore.getInstance());
			greekBattleCard.put(8, Medusa.getInstance());
			greekBattleCard.put(9, Minotaur.getInstance());
			greekBattleCard.put(10, MythicGreekHero.getInstance());
			greekBattleCard.put(11, Toxotes.getInstance());

			return greekBattleCard;
		}

		return greekBattleCard;
	}

	private static Hashtable<BattleCard, Integer> greekUnitsID = new Hashtable<BattleCard, Integer>();

	public static Hashtable<BattleCard, Integer> getGreekUnitsID() {
		if (greekUnitsID.size() == 0) {
			greekUnitsID.put(Centaur.getInstance(), 0);
			greekUnitsID.put(ClassicalGreekHero.getInstance(), 1);
			greekUnitsID.put(Cyclops.getInstance(), 2);
			greekUnitsID.put(HeroicGreekHero.getInstance(), 3);
			greekUnitsID.put(Hippokon.getInstance(), 4);
			greekUnitsID.put(Hoplite.getInstance(), 5);
			greekUnitsID.put(Hydra.getInstance(), 6);
			greekUnitsID.put(Manticore.getInstance(), 7);
			greekUnitsID.put(Medusa.getInstance(), 8);
			greekUnitsID.put(Minotaur.getInstance(), 9);
			greekUnitsID.put(MythicGreekHero.getInstance(), 10);
			greekUnitsID.put(Toxotes.getInstance(), 11);

			return greekUnitsID;
		}

		return greekUnitsID;
	}

	private static Hashtable<Integer, BattleCard> norsekBattleCard = new Hashtable<Integer, BattleCard>();

	public static Hashtable<Integer, BattleCard> getNorseBattleCard() {
		if (norsekBattleCard.size() == 0) {
			norsekBattleCard.put(0, Troll.getInstance());
			norsekBattleCard.put(1, ClassicalNorseHero.getInstance());
			norsekBattleCard.put(2, Valkyric.getInstance());
			norsekBattleCard.put(3, Nidhogg.getInstance());
			norsekBattleCard.put(4, Dwarf.getInstance());
			norsekBattleCard.put(5, Jarl.getInstance());
			norsekBattleCard.put(6, MythicNorseHero.getInstance());
			norsekBattleCard.put(7, Huskarl.getInstance());
			norsekBattleCard.put(8, ForestGiant.getInstance());
			norsekBattleCard.put(9, HeroicNorseHero.getInstance());
			norsekBattleCard.put(10, Throwing.getInstance());
			return norsekBattleCard;
		}

		return norsekBattleCard;
	}

	private static Hashtable<BattleCard, Integer> norseUnitsID = new Hashtable<BattleCard, Integer>();

	public static Hashtable<BattleCard, Integer> getNorseUnitsID() {
		if (norseUnitsID.size() == 0) {
			norseUnitsID.put(Troll.getInstance(), 0);
			norseUnitsID.put(ClassicalNorseHero.getInstance(), 1);
			norseUnitsID.put(Valkyric.getInstance(), 2);
			norseUnitsID.put(Nidhogg.getInstance(), 3);
			norseUnitsID.put(Dwarf.getInstance(), 4);
			norseUnitsID.put(Jarl.getInstance(), 5);
			norseUnitsID.put(MythicNorseHero.getInstance(), 6);
			norseUnitsID.put(Huskarl.getInstance(), 7);
			norseUnitsID.put(ForestGiant.getInstance(), 8);
			norseUnitsID.put(HeroicNorseHero.getInstance(), 9);
			norseUnitsID.put(Throwing.getInstance(), 10);
			return norseUnitsID;
		}

		return norseUnitsID;
	}
	
	/* card ID mapping */	
	private static Hashtable<Integer, Card> actionCard = new Hashtable<Integer, Card>();
	public static Hashtable<Integer, Card> getActionCard()
	{
		if(actionCard.size() == 0){
			actionCard.put(0, AttackCard.GetInstance());
			actionCard.put(1, BuildingCard.GetInstance());
			actionCard.put(2, ExploreCard.GetInstance());
			actionCard.put(3, GatherCard.GetInstance());
			actionCard.put(4, NextAgeCard.GetInstance());
			actionCard.put(5, RecruitCard.GetInstance());
			actionCard.put(6, TradeCard.GetInstance());
			
			return actionCard;
		}
		
		return actionCard;
	}
	
	private static Hashtable<Card, Integer> actionCardID = new Hashtable<Card, Integer>();
	public static Hashtable<Card, Integer> getActionCardID()
	{
		if(actionCardID.size() == 0)
		{
			actionCardID.put(AttackCard.GetInstance(), 0);
			actionCardID.put(BuildingCard.GetInstance(), 1);
			actionCardID.put(ExploreCard.GetInstance(), 2);
			actionCardID.put(GatherCard.GetInstance(), 3);
			actionCardID.put(NextAgeCard.GetInstance(),4);
			actionCardID.put(RecruitCard.GetInstance(), 5);
			actionCardID.put(TradeCard.GetInstance(), 6);
			
			return actionCardID;
		}
		
		return actionCardID;
	}
	
	private static Hashtable<Integer, Card> egyptRandomCard = new Hashtable<Integer, Card>();
	public static Hashtable<Integer, Card> getEgyptRandomCard()
	{
		if(egyptRandomCard.size() == 0)
		{
			egyptRandomCard.put(7, AttackFive.GetInstance());
			egyptRandomCard.put(8, AttackSix.GetInstance());
			egyptRandomCard.put(9, AttackSeven.GetInstance());
			egyptRandomCard.put(10, BuildThree.GetInstance());
			egyptRandomCard.put(11, BuildFour.GetInstance());
			egyptRandomCard.put(12, ExploreSame.GetInstance());
			egyptRandomCard.put(13, ExploreTwo.GetInstance());
			egyptRandomCard.put(14, GatherAll.GetInstance());
			egyptRandomCard.put(15, RandomTrade.GetInstance());
			egyptRandomCard.put(16, RecruitThree.GetInstance());
			egyptRandomCard.put(17, RecruitFour.GetInstance());
			egyptRandomCard.put(18, RecruitFive.GetInstance());
			egyptRandomCard.put(19, RandomNextAge.GetInstance());
			egyptRandomCard.put(20, Hathor.GetInstance());
			egyptRandomCard.put(21, Horus.GetInstance());
			egyptRandomCard.put(22, RAGather.GetInstance());
			egyptRandomCard.put(23, RecruitOsiris.GetInstance());
			egyptRandomCard.put(24, Sekhmet.GetInstance());
			
			return egyptRandomCard;
		}
		
		return egyptRandomCard;
	}

	private static Hashtable<Card, Integer> egyptCardID = new Hashtable<Card, Integer>();
	public static Hashtable<Card, Integer> getEgyptCardID()
	{
		if(egyptCardID.size() == 0)
		{
			egyptCardID.put(AttackCard.GetInstance(), 0);
			egyptCardID.put(BuildingCard.GetInstance(), 1);
			egyptCardID.put(ExploreCard.GetInstance(), 2);
			egyptCardID.put(GatherCard.GetInstance(), 3);
			egyptCardID.put(NextAgeCard.GetInstance(), 4);
			egyptCardID.put(RecruitCard.GetInstance(), 5);
			egyptCardID.put(TradeCard.GetInstance(), 6);
			egyptCardID.put(AttackFive.GetInstance(), 7);
			egyptCardID.put(AttackSix.GetInstance(), 8);
			egyptCardID.put(AttackSeven.GetInstance(), 9);
			egyptCardID.put(BuildThree.GetInstance(), 10);
			egyptCardID.put(BuildFour.GetInstance(), 11);
			egyptCardID.put(ExploreSame.GetInstance(), 12);
			egyptCardID.put(ExploreTwo.GetInstance(), 13);
			egyptCardID.put(GatherAll.GetInstance(), 14);
			egyptCardID.put(RandomTrade.GetInstance(), 15);
			egyptCardID.put(RecruitThree.GetInstance(), 16);
			egyptCardID.put(RecruitFour.GetInstance(), 17);
			egyptCardID.put(RecruitFive.GetInstance(), 18);
			egyptCardID.put(RandomNextAge.GetInstance(), 19);
			egyptCardID.put(Hathor.GetInstance(), 20);
			egyptCardID.put(Horus.GetInstance(), 21);
			egyptCardID.put(RAGather.GetInstance(), 22);
			egyptCardID.put(RecruitOsiris.GetInstance(), 23);
			egyptCardID.put(Sekhmet.GetInstance(), 24);
			
			return egyptCardID;
		}
		
		return egyptCardID;
	}
	
	private static Hashtable<Integer, Card> greekRandomCard = new Hashtable<Integer, Card>();
	public static Hashtable<Integer, Card> getGreekRandomCard()
	{
		if(greekRandomCard.size() == 0){
			greekRandomCard.put(7, AttackFive.GetInstance());
			greekRandomCard.put(8, AttackSix.GetInstance());
			greekRandomCard.put(9, AttackSeven.GetInstance());
			greekRandomCard.put(10, BuildThree.GetInstance());
			greekRandomCard.put(11, BuildFour.GetInstance());
			greekRandomCard.put(12, ExploreSame.GetInstance());
			greekRandomCard.put(13, ExploreTwo.GetInstance());
			greekRandomCard.put(14, GatherAll.GetInstance());
			greekRandomCard.put(15, RandomNextAge.GetInstance());
			greekRandomCard.put(16, RandomTrade.GetInstance());
			greekRandomCard.put(17, RecruitThree.GetInstance());
			greekRandomCard.put(18, RecruitFour.GetInstance());
			greekRandomCard.put(19, RecruitFive.GetInstance());
			greekRandomCard.put(20, Ares.GetInstance());
			greekRandomCard.put(21, Hera.GetInstance());
			greekRandomCard.put(22, Dionysus.GetInstance());
			greekRandomCard.put(23, Poseidon.GetInstance());
			greekRandomCard.put(24, Zeus.GetInstance());
			greekRandomCard.put(25, Apollo.GetInstance());
			greekRandomCard.put(26, Hermes.GetInstance());
			
			return greekRandomCard;
		}
		
		return greekRandomCard;
	}
	
	private static Hashtable<Card, Integer> greekCardID = new Hashtable<Card, Integer>();
	public static Hashtable<Card, Integer> getGreekCardID()
	{
		if(greekCardID.size() == 0)
		{
			greekCardID.put(AttackCard.GetInstance(), 0);
			greekCardID.put(BuildingCard.GetInstance(), 1);
			greekCardID.put(ExploreCard.GetInstance(), 2);
			greekCardID.put(GatherCard.GetInstance(), 3);
			greekCardID.put(NextAgeCard.GetInstance(), 4);
			greekCardID.put(RecruitCard.GetInstance(), 5);
			greekCardID.put(TradeCard.GetInstance(), 6);
			greekCardID.put(AttackFive.GetInstance(), 7);
			greekCardID.put(AttackSix.GetInstance(), 8);
			greekCardID.put(AttackSeven.GetInstance(), 9);
			greekCardID.put(BuildThree.GetInstance(), 10);
			greekCardID.put(BuildFour.GetInstance(), 11);
			greekCardID.put(ExploreSame.GetInstance(), 12);
			greekCardID.put(ExploreTwo.GetInstance(), 13);
			greekCardID.put(GatherAll.GetInstance(), 14);
			greekCardID.put(RandomNextAge.GetInstance(), 15);
			greekCardID.put(RandomTrade.GetInstance(), 16);
			greekCardID.put(RecruitThree.GetInstance(), 17);
			greekCardID.put(RecruitFour.GetInstance(), 18);
			greekCardID.put(RecruitFive.GetInstance(), 19);
			greekCardID.put(Ares.GetInstance(), 20);
			greekCardID.put(Hera.GetInstance(), 21);
			greekCardID.put(Dionysus.GetInstance(), 22);
			greekCardID.put(Poseidon.GetInstance(), 23);
			greekCardID.put(Zeus.GetInstance(),24);
			greekCardID.put(Apollo.GetInstance(), 25);
			greekCardID.put(Hermes.GetInstance(), 26);
			
			return greekCardID;
		}
		return greekCardID;
	}

	private static Hashtable<Integer, Card> norseRandomCard = new Hashtable<Integer, Card>();
	public static Hashtable<Integer, Card> getNorseRandomCard()
	{
		if(norseRandomCard.size() == 0){
			norseRandomCard.put(7, AttackFive.GetInstance());
			norseRandomCard.put(8, AttackSix.GetInstance());
			norseRandomCard.put(9, AttackSeven.GetInstance());
			norseRandomCard.put(10, BuildThree.GetInstance());
			norseRandomCard.put(11, BuildFour.GetInstance());
			norseRandomCard.put(12, ExploreSame.GetInstance());
			norseRandomCard.put(13, ExploreTwo.GetInstance());
			norseRandomCard.put(14, GatherAll.GetInstance());
			norseRandomCard.put(15, RandomTrade.GetInstance());
			norseRandomCard.put(16, RandomNextAge.GetInstance());
			norseRandomCard.put(17, RecruitThree.GetInstance());
			norseRandomCard.put(18, RecruitFour.GetInstance());
			norseRandomCard.put(19, RecruitFive.GetInstance());
			norseRandomCard.put(20, Brag.GetInstance());
			norseRandomCard.put(21, Njord.GetInstance());
			norseRandomCard.put(22, Loki.GetInstance());
			norseRandomCard.put(23, Skadi.GetInstance());
			norseRandomCard.put(24, Forseti.GetInstance());
			norseRandomCard.put(25, Freyia.GetInstance());
			norseRandomCard.put(26, Hel.GetInstance());
			
			return norseRandomCard;
		}
		
		return norseRandomCard;
	}

	private static Hashtable<Card, Integer> norseCardID = new Hashtable<Card, Integer>();
	public static Hashtable<Card, Integer> getNorseCardID()
	{
		if(norseCardID.size() == 0)
		{
			norseCardID.put(AttackCard.GetInstance(), 0);
			norseCardID.put(BuildingCard.GetInstance(), 1);
			norseCardID.put(ExploreCard.GetInstance(), 2);
			norseCardID.put(GatherCard.GetInstance(), 3);
			norseCardID.put(NextAgeCard.GetInstance(), 4);
			norseCardID.put(RecruitCard.GetInstance(), 5);
			norseCardID.put(TradeCard.GetInstance(), 6);
			norseCardID.put(AttackFive.GetInstance(), 7);
			norseCardID.put(AttackSix.GetInstance(), 8);
			norseCardID.put(AttackSeven.GetInstance(), 9);
			norseCardID.put(BuildThree.GetInstance(), 10);
			norseCardID.put(BuildFour.GetInstance(), 11);
			norseCardID.put(ExploreSame.GetInstance(), 12);
			norseCardID.put(ExploreTwo.GetInstance(), 13);
			norseCardID.put(GatherAll.GetInstance(), 14);
			norseCardID.put(RandomTrade.GetInstance(), 15);
			norseCardID.put(RandomNextAge.GetInstance(), 16);
			norseCardID.put(RecruitThree.GetInstance(), 17);
			norseCardID.put(RecruitFour.GetInstance(), 18);
			norseCardID.put(RecruitFive.GetInstance(), 19);
			norseCardID.put(Brag.GetInstance(), 20);
			norseCardID.put(Njord.GetInstance(), 21);
			norseCardID.put(Loki.GetInstance(), 22);
			norseCardID.put(Skadi.GetInstance(), 23);
			norseCardID.put(Forseti.GetInstance(), 24);
			norseCardID.put(Freyia.GetInstance(), 25);
			norseCardID.put(Hel.GetInstance(), 26);
			
			return norseCardID;
		}
		
		return norseCardID;
	}
}
