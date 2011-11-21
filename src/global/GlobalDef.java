package global;

import java.util.Hashtable;
import building.*;
import tile.*;
import battlecard.*;

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

}
