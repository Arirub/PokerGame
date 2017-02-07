package de.flare.poker.game.pokerService;

import de.flare.poker.game.model.Hand;

public interface PokerService {
	public Hand calculateHighestRank(Hand handFirst, Hand handSecond) ;
		
}
