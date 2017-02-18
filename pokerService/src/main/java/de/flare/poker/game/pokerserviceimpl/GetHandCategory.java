package de.flare.poker.game.pokerserviceimpl;

import java.util.Collections;
import java.util.TreeMap;

import de.flare.poker.game.model.Hand;
import de.flare.poker.game.model.enums.Category;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;

public class GetHandCategory {

	public GetHandCategory() {
		super();
	}

	public Category getHandCategory(Hand hand) {

		TreeMap<Rank, Integer> ranks = getRanks(hand);

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

	TreeMap<Rank, Integer> getRanks(Hand hand) {

		TreeMap<Rank, Integer> ranks = new TreeMap<Rank, Integer>();

		hand.getCards().forEach(c -> {
			if (ranks.get(c.getRank()) != null)
				ranks.put(c.getRank(), ranks.get(c.getRank()) + 1);
			else
				ranks.put(c.getRank(), 1);
		});
		return ranks;

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
		if (((lastCardRank - firstCardRank) == 4) || ((ranks.keySet().toArray()[3].equals(Rank.FIVE))
				&& (ranks.keySet().toArray()[4].equals(Rank.ACE)))) {

			if (suits.size() == 1) {
				if (ranks.keySet().toArray()[3].equals(Rank.FIVE)) {
					return Category.STRAIGHT_FLUSH_LOW_ACE;
				} else {
					return Category.STRAIGHT_FLUSH;
				}
			} else {
				if (ranks.keySet().toArray()[3].equals(Rank.FIVE)) {
					return Category.STRAIGHT_LOW_ACE;
				} else {
					return Category.STRAIGHT;
				}
			}
		} else {
			if (suits.size() == 1) {
				return Category.FLUSH;
			} else {
				return Category.HIGH_CARD;
			}
		}
	}

	private Category getCategoryThreeDistinctCards(TreeMap<Rank, Integer> ranks) {

		int highestNumberRanksRepeted = Collections
				.max(ranks.entrySet(), (card1, card2) -> card1.getValue() - card2.getValue()).getValue();

		if (highestNumberRanksRepeted == 3) {
			return Category.THREE_OF_A_KIND;
		} else {
			return Category.TWO_PAIRS;
		}
	}

	private Category getCategoryTwoDistinctCards(TreeMap<Rank, Integer> ranks) {

		int highestNumberRanksRepeted = Collections
				.max(ranks.entrySet(), (card1, card2) -> card1.getValue() - card2.getValue()).getValue();

		if (highestNumberRanksRepeted == 4) {
			return Category.POKER;
		} else {
			return Category.FULL_HOUSE;
		}
	}

}