package de.flare.poker.game.model;

import java.util.List;

public class Hand {

	List<Card> cards;

	public Hand(List<Card> cards) {
		super();
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
		
}
