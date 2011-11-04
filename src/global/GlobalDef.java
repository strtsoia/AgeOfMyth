package global;
/*
 * this class contains all global data, such as, units, resources, tiles
 */
public class GlobalDef {

	public enum Resources
	{
		FOOD,
		WOOD,
		GOLD,
		FAVOR,
		VICTORY;
	}
	
	public enum TileType
	{
		Production,
		Building
	}
	
	public enum CardType
	{
		Permanent,
		Random,
		Battle
	}
	
	public enum BuildingTiles
	{
		Wonder,
		Granary,
		Wall,
		Quarry,
		GoldMint,
		Market,
		Armory,
		SiegeEngineWorkshop,
		Monument,
		GreatTemple,
		StoreHouse,
		WoodWorkshop,
		Tower,
		House
	}
	
	public enum Terrain
	{
		Fertile,
		Forest,
		Hills,
		Mountains,
		Desert,
		Swamp
		
	}
	
	public enum ProductionTiles
	{
		FertileA,
		FertileB,
		FertileC,
		FertileD,
		ForestA,
		ForestB,
		ForestC,
		ForestD,
		HillA,
		HillB,
		HillC,
		HillD,
		MountainA,
		MountainB,
		MountainC,
		DesertA,
		DesertB,
		SwampA,
		SwampB,
		SwampC
	}
	
	public enum Unit 
    {
        chariot_archer, // Egypt units begin
        elephant,
        spearman,
        anubite,
        mummy,
        phoenix,
        scorpion,
        sphinx,
        wadjet,
        priest,
        pharaoh,
        sonofosiris,
        hippokon,   // Greek units begin
        hoplite,
        toxotes,
        centaur,
        cyclops,
        hydra,
        manticore,
        medusa,
        minotaur,
        classicGreekHero,
        heroicGreekHero,
        mythicGreekHero,
        jarl,   // Norse units begin
        throwing,
        huskarl,
        nidhogg,
        valkyrie,
        frostgiant,
        troll,
        dwarf,
        classicalNorseHero,
        heroicNorseHero,
        mythicNorseHero
    }
}
