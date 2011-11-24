package battlecard;

import java.util.List;
import java.util.Arrays;
import java.util.Hashtable;

import global.GlobalDef;
import component.Culture;

public abstract class BattleCard {

	private static BattleCard[] MortalArray = new BattleCard[]{Chariot.getInstance(), Elephant.getInstance(), Spearman.getInstance(),
				Hippokon.getInstance(), Hoplite.getInstance(), Toxotes.getInstance(), Jarl.getInstance(),
				Throwing.getInstance(), Huskarl.getInstance()};
	protected static final List<BattleCard> Mortal = Arrays.asList(MortalArray);
	
	private static BattleCard[] GiantArray = new BattleCard[]{Elephant.getInstance(), Scorpion.getInstance(), Cyclops.getInstance(),
				Hydra.getInstance(), ForestGiant.getInstance()};
	protected static final List<BattleCard> Giant = Arrays.asList(GiantArray);
	
	private static BattleCard[] FlyerArray = new BattleCard[]{Phoenix.getInstance(), Manticore.getInstance(), Nidhogg.getInstance()};
	protected static final List<BattleCard> Flyer = Arrays.asList(FlyerArray);
	
	private static BattleCard[] WarriorArray = new BattleCard[]{Spearman.getInstance(), Wadjet.getInstance(), Hoplite.getInstance(),
				Minotaur.getInstance(), Huskarl.getInstance(), Troll.getInstance()};
	protected static final List<BattleCard> Warrior = Arrays.asList(WarriorArray);
	
	private static BattleCard[] GiantKillerArray = new BattleCard[]{Sphinx.getInstance(), Medusa.getInstance(), Dwarf.getInstance()};
	protected static final List<BattleCard> GiantKiller = Arrays.asList(GiantKillerArray);
	
	private static BattleCard[] HeroArray = new BattleCard[]{ClassicalGreekHero.getInstance(), ClassicalNorseHero.getInstance(),
				HeroicGreekHero.getInstance(), HeroicNorseHero.getInstance(), MythicGreekHero.getInstance(), Pharaoh.getInstance(),
				MythicNorseHero.getInstance(), Osiris.getInstance(), Priest.getInstance()};
	protected static final List<BattleCard> Hero = Arrays.asList(HeroArray);
	
	private static BattleCard[] CavalryArray = new BattleCard[]{Chariot.getInstance(), Anubite.getInstance(), Centaur.getInstance(),
				Hippokon.getInstance(), Jarl.getInstance(), Valkyric.getInstance()};
	protected static final List<BattleCard> Cavalry = Arrays.asList(CavalryArray);
	
	private static BattleCard[] ArcherArray = new BattleCard[]{Chariot.getInstance(), Centaur.getInstance(), Toxotes.getInstance(),
				Throwing.getInstance()};
	protected static final List<BattleCard> Archer = Arrays.asList(ArcherArray);
	
	private static BattleCard[] MythArray = new BattleCard[]{Scorpion.getInstance(), Wadjet.getInstance(), Sphinx.getInstance(),
				Anubite.getInstance(), Mummy.getInstance(), Centaur.getInstance(), Cyclops.getInstance(), Hydra.getInstance(),
				Manticore.getInstance(), Medusa.getInstance(), Minotaur.getInstance(), Nidhogg.getInstance(), Valkyric.getInstance(),
				ForestGiant.getInstance(), Troll.getInstance(), Dwarf.getInstance()};
	protected static final List<BattleCard> Myth = Arrays.asList(MythArray);
	
	public abstract int getRolls();
	public abstract void setBonus(int b);
	public abstract int getBonus();
	public abstract void CheckBonus(BattleCard card);
	public abstract Hashtable<GlobalDef.Resources, Integer> getCost();
	public abstract void GodPower(Culture a, Culture d, boolean b);
	public abstract GlobalDef.GodPowerTime getGodPowerTime();
}
