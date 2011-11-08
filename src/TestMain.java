
import battlecard.*;
import global.GlobalDef;
import settings.*;
import tile.*;
import utility.*;
import building.*;
import actioncard.*;
import component.*;

import java.util.*;

public class TestMain {

	public static void main(String args[])
	{
		Bank.getInstance();
		Culture c = new Culture(GlobalDef.Races.Egypt);
		Culture c1 =  new Culture(GlobalDef.Races.Greek);
	//	Hashtable<BattleCard, Integer> h = c.getGameBoard().unitsPool;
		
		//Board b = c.getGameBoard();
		
		// test bank initialization
		/*Set<BattleCard> Kset = h.keySet();
		Iterator<BattleCard> iter = Kset.iterator();
		
		while(iter.hasNext()){
			BattleCard bc = iter.next();
			System.out.println(bc + ":  " + h.get(bc));
		}*/
		// end of test bank initialization
	}
}
