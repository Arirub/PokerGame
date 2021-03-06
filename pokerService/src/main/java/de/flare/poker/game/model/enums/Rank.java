package de.flare.poker.game.model.enums;

public enum Rank {
	
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);

	private int rank;
	
	Rank(int rank){
		this.rank=rank;
	}

	public int getRank() {
		return rank;
	}
	
	public static Rank fromId(int id) {
        for (Rank type : Rank.values()) {
            if (type.getRank() == id) {
                return type;
            }
        }
        return null;
    }

	
}
