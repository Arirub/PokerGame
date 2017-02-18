package de.flare.poker.game.pokerserviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
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
import de.flare.poker.game.pokerService.PokerService;

public class PokerServiceImpl extends GetHandCategory implements PokerService {
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
