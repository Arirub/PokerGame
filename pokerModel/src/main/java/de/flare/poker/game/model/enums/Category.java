package de.flare.poker.game.model.enums;

public enum Category {

	HIGH_CARD(1),
	PAIR(2),
	TWO_PAIRS(3),
	THREE_OF_A_KIND(4),
	STRAIGHT(5),
	FLUSH(6),
	FULL_HOUSE(7),
	POKER(8),
	STRAIGHT_FLUSH_LOW_ACE(9),
	STRAIGHT_FLUSH(10);
	
	private int category;

	private Category(int category) {
		this.category = category;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
}
