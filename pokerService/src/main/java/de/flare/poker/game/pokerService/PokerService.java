package de.flare.poker.game.pokerService;

import de.flare.poker.game.model.Hand;

public interface PokerService {
	public Hand calculateHigherRank(Hand handFirst, Hand handSecond) ;
		
}
