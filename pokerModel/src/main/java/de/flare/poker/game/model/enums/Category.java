package de.flare.poker.game.model.enums;

public enum Category {

	HIGH_CARD(1),
	PAIR(2),
	TWO_PAIRS(3),
	THREE_OF_A_KIND(4),
	STRAIGHT_LOW_ACE(5),
	STRAIGHT(6),
	FLUSH(7),
	FULL_HOUSE(8),
	POKER(9),
	STRAIGHT_FLUSH_LOW_ACE(10),
	STRAIGHT_FLUSH(11);
	
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
