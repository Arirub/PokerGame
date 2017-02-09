package de.flare.poker.game.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import de.flare.poker.game.model.Card;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;

public class PokerInputTest {
	
	@Test
	public void duplicatedCardInSecondHand() {

		HashMap<String, Card> firstHand =generateHandHashMap(Suit.C, Rank.EIGHT, Suit.D, Rank.EIGHT, Suit.D, Rank.QUEEN, Suit.C, Rank.NINE, Suit.C, Rank.FOUR);
		HashMap<String, Card> secondHand = generateHandHashMap(Suit.C, Rank.ACE, Suit.D, Rank.TEN, Suit.H, Rank.FIVE, Suit.C, Rank.FOUR, Suit.C, Rank.TWO);

		assertTrue(PokerInput.checkRepeatedIncorrectNumberCards(firstHand, secondHand));

	}
	@Test
	public void notDuplicatedCardInSecondHand() {

		HashMap<String, Card> firstHand =generateHandHashMap(Suit.C, Rank.EIGHT, Suit.D, Rank.EIGHT, Suit.D, Rank.QUEEN, Suit.C, Rank.NINE, Suit.C, Rank.FOUR);
		HashMap<String, Card> secondHand = generateHandHashMap(Suit.C, Rank.ACE, Suit.D, Rank.TEN, Suit.H, Rank.FIVE, Suit.D, Rank.FOUR, Suit.C, Rank.TWO);

		assertFalse(PokerInput.checkRepeatedIncorrectNumberCards(firstHand, secondHand));

	}
	@Test
	public void addCardsFromStringCorrect() {

		String hand="C8,D8,D12,C9,C4";
		HashMap<String, Card> handGenerated =generateHandHashMap(Suit.C, Rank.EIGHT, Suit.D, Rank.EIGHT, Suit.D, Rank.QUEEN, Suit.C, Rank.NINE, Suit.C, Rank.FOUR);
		
		assertEquals(handGenerated,PokerInput.addCards(hand));

	}
	@Test
	public void addCardsFromStringFail() {
		
		String hand="C8,D8,D12,C4";
		
		assertNull(PokerInput.addCards(hand));
		
	}
	@Test
	public void checkCardExistsCorrect() {

		String cardString="C3";
		Card card = new Card(Suit.C, Rank.THREE);
		assertEquals(card,PokerInput.checkCardExists(cardString));

	}
	@Test
	public void checkCardExistsFail() {

		String cardString="C15";
		assertNull(PokerInput.checkCardExists(cardString));

	}

	private HashMap<String, Card> generateHandHashMap(Suit suit1, Rank rank1, Suit suit2, Rank rank2, Suit suit3, Rank rank3, Suit suit4,
			Rank rank4, Suit suit5, Rank rank5) {

		Card card1 = new Card(suit1, rank1);
		Card card2 = new Card(suit2, rank2);
		Card card3 = new Card(suit3, rank3);
		Card card4 = new Card(suit4, rank4);
		Card card5 = new Card(suit5, rank5);

		HashMap<String, Card> cards = new HashMap<String, Card>();
		cards.put(suit1.toString()+rank1.toString(),card1);
		cards.put(suit2.toString()+rank2.toString(),card2);
		cards.put(suit3.toString()+rank3.toString(),card3);
		cards.put(suit4.toString()+rank4.toString(),card4);
		cards.put(suit5.toString()+rank5.toString(),card5);

		return cards;
	}
}
