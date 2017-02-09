package de.flare.poker.game.pokerserviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.flare.poker.game.model.Hand;
import de.flare.poker.game.model.enums.Category;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;
import de.flare.poker.game.pokerService.PokerService;

public class PokerServiceImpl implements PokerService {
	private static final Log log = LogFactory.getLog(PokerServiceImpl.class);

	public Hand calculateHighestRank(Hand firstHand, Hand secondHand) {

		Category firstCategory = getHandCategory(firstHand);
		Category secondCategory = getHandCategory(secondHand);
		log.info("The category of the first hand: " + firstCategory);
		log.info("The category of the second hand: " + secondCategory);

		if (firstCategory.equals(secondCategory)) {
			Hand winnerHand = calculateHighestRankSameCategory(firstHand, secondHand);
			return winnerHand;
		} else {
			if (firstCategory.getCategory() > secondCategory.getCategory()) {
				return firstHand;
			} else {
				return secondHand;
			}
		}
	}

	private TreeMap<Rank, Integer> getRanks(Hand hand) {

		TreeMap<Rank, Integer> ranks = new TreeMap<Rank, Integer>();

		hand.getCards().forEach(c -> {
			if (ranks.get(c.getRank()) != null)
				ranks.put(c.getRank(), ranks.get(c.getRank()) + 1);
			else
				ranks.put(c.getRank(), 1);
		});
		return ranks;

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

	public Hand calculateHighestRankSameCategory(Hand firstHand, Hand secondHand) {

		LinkedHashMap<Rank, Integer> ranksFirstHand = getOrderedMap(firstHand);
		LinkedHashMap<Rank, Integer> ranksSecondHand = getOrderedMap(secondHand);

		List<Integer> firstKeys = new ArrayList<Integer>();
		List<Integer> secondKeys = new ArrayList<Integer>();

		ranksFirstHand.forEach((k, v) -> {
			firstKeys.add(k.getRank());
		});
		ranksSecondHand.forEach((k, v) -> {
			secondKeys.add(k.getRank());
		});

		for (int i = 0; i < firstKeys.size(); i++) {
			if (firstKeys.get(i) > secondKeys.get(i))
				return firstHand;
			if (firstKeys.get(i) < secondKeys.get(i))
				return secondHand;
		}

		return null;
	}

	private LinkedHashMap<Rank, Integer> getOrderedMap(Hand hand) {

		TreeMap<Integer, Integer> ranksHand = new TreeMap<Integer, Integer>(Comparator.reverseOrder());

		hand.getCards().forEach(c -> {
			if (ranksHand.get(c.getRank().getRank()) != null)
				ranksHand.put(c.getRank().getRank(), ranksHand.get(c.getRank().getRank()) + 1);
			else
				ranksHand.put(c.getRank().getRank(), 1);
		});

		Map<Object, Object> ranksMapped = ranksHand.entrySet().stream()
				.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		log.info("Sorted Map: " + Arrays.toString(ranksMapped.entrySet().toArray()));

		LinkedHashMap<Rank, Integer> orderedCategoryValues = new LinkedHashMap<>();
		ranksMapped.forEach((k, v) -> {
			int index = (int) k;
			orderedCategoryValues.put(Rank.values()[index - 2], (Integer) v);
		});

		return orderedCategoryValues;
	}

}
