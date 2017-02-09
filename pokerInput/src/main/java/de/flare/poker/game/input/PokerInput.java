package de.flare.poker.game.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import de.flare.poker.game.model.Card;
import de.flare.poker.game.model.Hand;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;
import de.flare.poker.game.pokerService.PokerService;
import de.flare.poker.game.pokerserviceimpl.PokerServiceImpl;

public class PokerInput {
	
	public static void main(String[] args) {
		
		System.out.println("A poker deck contains 52 cards - each card has a suit which is one of clubs, diamonds, hearts, "
				+ "or spades \n(denoted C, D, H, and S in the input data). Each card also has a value which is one "
				+ "\nof 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace (denoted 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A)."
				+ "\nFor scoring purposes, the suits are unordered while the values are ordered as given above, "
				+ "\nwith 2 being the lowest and ace the highest value.\n\n");
		System.out.println(
				"You have to introduce the first hand, must contain 5 cards (e.g.:HA,S3,D4,D3,C4) and press Enter");
		
		Scanner scanner = new Scanner(System.in);
		String firstHandInput = scanner.next();
		System.out.println("You have to introduce the second hand, must contain 5 cards (e.g.:HA,S3,D4,D3,C4) and press Enter");
		String secondHandInput = scanner.next();

		scanner.close();

		HashMap<String, Card> firstHandGenerated = addCards(firstHandInput);
		HashMap<String, Card> secondHandGenerated = addCards(secondHandInput);

		if (checkRepeatedIncorrectNumberCards(firstHandGenerated, secondHandGenerated)) {
			System.out.println("Please review your cards! (Incorrect / Duplicated)");
		} else {

			List<Card> firstHandCards = new ArrayList<Card>();
			List<Card> secondHandCards = new ArrayList<Card>();
			firstHandGenerated.forEach((k, v) -> {
				firstHandCards.add(v);
			});
			secondHandGenerated.forEach((k, v) -> {
				secondHandCards.add(v);
			});
			Hand firstHand = new Hand(firstHandCards);
			Hand secondHand = new Hand(secondHandCards);

			PokerService pokerService = new PokerServiceImpl();
			Hand winnerHand = pokerService.calculateHighestRank(firstHand, secondHand);
			if(winnerHand==null) {
				System.out.println("There is no winner same Rank and same Category");
			}else {
				if (winnerHand.equals(firstHand)) {
					System.out.println("The winner is the first hand: " + firstHandInput.toUpperCase());
				}else {
					System.out.println("The winner is the second hand: " + secondHandInput.toUpperCase());
				}
			}
			
		}
	}

	public static Boolean checkRepeatedIncorrectNumberCards(HashMap<String, Card> firstHandGenerated,
			HashMap<String, Card> secondHandGenerated) {
		
		if ((firstHandGenerated != null) && (secondHandGenerated != null)) {

			if ((firstHandGenerated.size() == 5) || (secondHandGenerated.size() == 5)) {

				HashMap<String, Card> noRepeatedCards = new HashMap<String, Card>();
				noRepeatedCards.putAll(firstHandGenerated);
				noRepeatedCards.putAll(secondHandGenerated);
				if (noRepeatedCards.size() == 10) {
					return false;
				}
			}
		}
		return true;
	}

	public static HashMap<String, Card> addCards(String hand) {
		
		String[] handCards = hand.split(",");
		HashMap<String, Card> cards = new HashMap<String, Card>();

		if (handCards.length == 5) {
			for (int i = 0; i < handCards.length; i++) {
				Card card = checkCardExists(handCards[i]);
				if (card != null) {
					cards.put(card.getSuit().toString() + card.getRank().toString(), card);
				}
			}
			return cards;
		}
		return null;

	}

	public static Card checkCardExists(String card) {
		
		Suit suit;
		if(card.length()==2) {
			try {
				 suit= Suit.valueOf(card.substring(0, 1).toUpperCase());
						
				if (suit != null) {
					String rankString = card.substring(1, card.length()).toUpperCase();
					int rankInt;
					switch (rankString) {
					case "T":
						rankInt = 10;
						break;
					case "J":
						rankInt = 11;
						break;
					case "Q":
						rankInt = 12;
						break;
					case "K":
						rankInt = 13;
						break;
					case "A":
						rankInt = 14;
						break;
					default:
						rankInt = Integer.parseInt(rankString);
						break;
					}
					Rank rank = Rank.fromId(rankInt);
		
					if (rank != null) {
						Card cardHand = new Card(suit, rank);
						return cardHand;
					}
				}
			}catch(Exception e) {
				return null;
			}
		}
		return null;

	}
}
