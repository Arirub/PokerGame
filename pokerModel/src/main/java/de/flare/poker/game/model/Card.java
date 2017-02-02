package de.flare.poker.game.model;

import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;

public class Card {
	
	Suit suit;
	Rank rank;
	
	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}
	public Suit getSuit() {
		return suit;
	}
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	

}
