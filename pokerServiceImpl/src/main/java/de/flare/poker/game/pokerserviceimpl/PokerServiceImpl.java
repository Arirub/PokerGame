package de.flare.poker.game.pokerserviceimpl;

import java.util.Collections;
import java.util.TreeMap;

import de.flare.poker.game.model.Hand;
import de.flare.poker.game.model.enums.Category;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;
import de.flare.poker.game.pokerService.PokerService;

public class PokerServiceImpl implements PokerService {

	public Hand calculateHigherRank(Hand handFirst, Hand handSecond) {
		getHandCategory(handFirst);
		getHandCategory(handSecond);
		return null;
	}

	public Category getHandCategory(Hand hand) {
		TreeMap<Rank, Integer> ranks = new TreeMap<Rank, Integer>();

		hand.getCards().forEach(c -> {
			if (ranks.get(c.getRank()) != null)
				ranks.put(c.getRank(), ranks.get(c.getRank()) + 1);
			else
				ranks.put(c.getRank(), 1);
		});

		Category category = null;
		switch (ranks.size()) {
		case 5:
			category = getCategoryFiveDistinctCards(ranks, hand);
			break;
		case 4:
			category = Category.PAIR;
			break;
		case 3:
			category = getCategoryThreeDistinctCards(ranks);
			break;
		case 2:
			category = getCategoryTwoDistinctCards(ranks);
			break;
		default:
			break;
		}
		return category;
	}

	private Category getCategoryFiveDistinctCards(TreeMap<Rank, Integer> ranks, Hand hand) {
		int firstCardRank = ranks.firstKey().getRank();
		int lastCardRank = ranks.lastKey().getRank();

		TreeMap<Suit, Integer> suits = new TreeMap<Suit, Integer>();
		hand.getCards().forEach(c -> {
			if (suits.get(c.getSuit()) != null)
				suits.put(c.getSuit(), suits.get(c.getSuit()) + 1);
			else
				suits.put(c.getSuit(), 1);
		});
		if ((lastCardRank - firstCardRank) == 4)  {
			if (suits.size() == 1) {
				System.out.println("STRAIGHT FLUSH");
				return Category.STRAIGHT_FLUSH;

			} else {
				System.out.println("STRAIGHT");
				return Category.STRAIGHT;
			}
		} else {
			if (suits.size() == 1) {
				System.out.println("FLUSH");
				return Category.FLUSH;
			} else {
				System.out.println("HIGH CARD");
				return Category.HIGH_CARD;// Esto aqui o deberia calcular cual
											// es la carta mas alta??
			}
		}
	}

	private Category getCategoryThreeDistinctCards(TreeMap<Rank, Integer> ranks) {
		int highestNumberOfRanksRepeted = Collections
				.max(ranks.entrySet(), (card1, card2) -> card1.getValue() - card2.getValue()).getValue();
		if (highestNumberOfRanksRepeted == 3) {
			System.out.println("THREE OF A KIND");
			return Category.THREE_OF_A_KIND;
		} else {
			System.out.println("TWO PAIRS");
			return Category.TWO_PAIRS;
		}
	}

	private Category getCategoryTwoDistinctCards(TreeMap<Rank, Integer> ranks) {

		int highestNumberOfRanksRepeted = Collections
				.max(ranks.entrySet(), (card1, card2) -> card1.getValue() - card2.getValue()).getValue();
		if (highestNumberOfRanksRepeted == 4) {
			System.out.println("POKER");
			return Category.POKER;
		} else {
			System.out.println("FULL HOUSE");
			return Category.FULL_HOUSE;
		}
	}

}
