package pokerService;

import de.flare.poker.game.model.Hand;

public interface pokerService {
	public Hand calculateHigherRank(Hand handFirst, Hand handSecond) ;
		
}
