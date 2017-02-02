package de.flare.poker.game;

import de.flare.game.model.Hand;

public interface pokerService {
	public Hand calculateHigherRank(Hand handFirst, Hand handSecond);
}
