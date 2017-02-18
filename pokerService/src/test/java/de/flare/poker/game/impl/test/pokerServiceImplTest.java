package de.flare.poker.game.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.flare.poker.game.model.Card;
import de.flare.poker.game.model.Hand;
import de.flare.poker.game.model.enums.Category;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;
import de.flare.poker.game.pokerService.PokerService;
import de.flare.poker.game.pokerserviceimpl.GetHandCategory;
import de.flare.poker.game.pokerserviceimpl.PokerServiceImpl;

public class pokerServiceImplTest {
	
	@Test
	public void pairIsHigherThanHighCard(){
		
		Hand handPair=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.QUEEN,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		Hand handHighCard=generateHand(Suit.C, Rank.ACE,Suit.D, Rank.TEN, Suit.H, Rank.FIVE, Suit.S, Rank.FOUR, Suit.C, Rank.TWO);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handPair,pokerService.calculateHighestRank(handPair, handHighCard));
		
	}
	
	@Test
	public void twoPairIsHigherThanPair(){
		
		Hand handTwoPairs=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.NINE,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		Hand handPair=generateHand(Suit.S, Rank.EIGHT,Suit.H, Rank.EIGHT,Suit.D, Rank.QUEEN,Suit.H, Rank.NINE,Suit.H, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handTwoPairs,pokerService.calculateHighestRank(handTwoPairs, handPair));
		
	}
	
	@Test
	public void threeOfAKindIsHigherThanTwoPairs(){
		
		Hand handThreeOfAKind=generateHand(Suit.C, Rank.KING,Suit.D, Rank.KING,Suit.H, Rank.KING,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		Hand handTwoPairs=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.H, Rank.NINE,Suit.D, Rank.NINE,Suit.D, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handThreeOfAKind,pokerService.calculateHighestRank(handThreeOfAKind, handTwoPairs));
		
	}
	
	@Test
	public void straightIsHigherThanThreeOfAKind(){
		
		
		Hand handStraight=generateHand(Suit.C, Rank.QUEEN,Suit.H, Rank.JACK, Suit.H, Rank.TEN, Suit.C, Rank.NINE, Suit.C, Rank.EIGHT);
		Hand handThreeOfAKind=generateHand(Suit.C, Rank.KING,Suit.D, Rank.KING,Suit.H, Rank.KING,Suit.D, Rank.NINE,Suit.C, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraight,pokerService.calculateHighestRank(handStraight, handThreeOfAKind));
		
	}
	
	@Test
	public void flushIsHigherThanStraight(){
		
		Hand handFlush=generateHand(Suit.D, Rank.TEN,Suit.D, Rank.EIGHT,Suit.D, Rank.SEVEN,Suit.D, Rank.FIVE,Suit.D, Rank.THREE);
		Hand handStraight=generateHand(Suit.C, Rank.QUEEN,Suit.H, Rank.JACK, Suit.H, Rank.TEN, Suit.C, Rank.NINE, Suit.C, Rank.EIGHT);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handFlush,pokerService.calculateHighestRank(handFlush, handStraight));
		
	}
	
	@Test
	public void fullHouseIsHigherThanFlush(){
		
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		Hand handFlush=generateHand(Suit.D, Rank.TEN,Suit.D, Rank.EIGHT,Suit.D, Rank.SEVEN,Suit.D, Rank.FIVE,Suit.D, Rank.THREE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handFullHouse,pokerService.calculateHighestRank(handFullHouse, handFlush));
		
	}
	
	@Test
	public void pokerIsHigherThanFullHouse(){
		
		Hand handPoker=generateHand(Suit.C, Rank.ACE,Suit.D, Rank.ACE, Suit.H, Rank.ACE, Suit.S, Rank.ACE, Suit.C, Rank.FOUR);
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handPoker,pokerService.calculateHighestRank(handPoker, handFullHouse));
		
	}
	
	@Test
	public void straightFlushIsHigherThanPoker(){
		
		Hand handStraightFlush=generateHand(Suit.H, Rank.ACE,Suit.H, Rank.KING, Suit.H, Rank.QUEEN, Suit.H, Rank.JACK, Suit.H, Rank.TEN);
		Hand handPoker=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.S, Rank.NINE, Suit.C, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraightFlush,pokerService.calculateHighestRank(handStraightFlush, handPoker));
		
	}
	
	@Test
	public void pokerIsHigherThanTwoPairs(){
		
		Hand handPoker=generateHand(Suit.C, Rank.ACE,Suit.D, Rank.ACE, Suit.H, Rank.ACE, Suit.S, Rank.ACE, Suit.C, Rank.FOUR);
		Hand handTwoPairs=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.NINE,Suit.C, Rank.NINE,Suit.D, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handPoker,pokerService.calculateHighestRank(handPoker, handTwoPairs));
		
	}
	
	@Test
	public void straightFlushIsHigherThanFullHouse(){
		
		Hand handStraightFlush=generateHand(Suit.H, Rank.ACE,Suit.H, Rank.KING, Suit.H, Rank.QUEEN, Suit.H, Rank.JACK, Suit.H, Rank.TEN);
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraightFlush,pokerService.calculateHighestRank(handStraightFlush, handFullHouse));
		
	}
	
	@Test
	public void checkCategoryHighCard(){
		
		Hand handHighCard=generateHand(Suit.C, Rank.ACE,Suit.D, Rank.TEN, Suit.H, Rank.FIVE, Suit.S, Rank.FOUR, Suit.C, Rank.TWO);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.HIGH_CARD,pokerService.getHandCategory(handHighCard));
		
	}
	
	@Test
	public void checkCategoryPair(){
		
		Hand handPair=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.QUEEN,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.PAIR,pokerService.getHandCategory(handPair));
		
	}
	
	@Test
	public void checkCategoryTwoPairs(){
		
		Hand handTwoPairs=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.NINE,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.TWO_PAIRS,pokerService.getHandCategory(handTwoPairs));
		
	}
	
	@Test
	public void checkCategoryThreeOfAKind(){
		
		Hand handThreeOfAKind=generateHand(Suit.C, Rank.KING,Suit.D, Rank.KING,Suit.H, Rank.KING,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.THREE_OF_A_KIND,pokerService.getHandCategory(handThreeOfAKind));
		
	}
	
	@Test
	public void checkCategoryStraight(){
		
		Hand handStraight=generateHand(Suit.C, Rank.QUEEN,Suit.H, Rank.JACK, Suit.H, Rank.TEN, Suit.C, Rank.NINE, Suit.C, Rank.EIGHT);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.STRAIGHT,pokerService.getHandCategory(handStraight));
		
	}
	
	@Test
	public void checkCategoryFlush(){
		
		Hand handFlush=generateHand(Suit.D, Rank.TEN,Suit.D, Rank.EIGHT,Suit.D, Rank.SEVEN,Suit.D, Rank.FIVE,Suit.D, Rank.THREE);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.FLUSH,pokerService.getHandCategory(handFlush));
		
	}
	
	@Test
	public void checkCategoryFullHouse(){
		
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.FULL_HOUSE,pokerService.getHandCategory(handFullHouse));
		
	}
	
	@Test
	public void checkCategoryPoker(){
		
		Hand handPoker=generateHand(Suit.C, Rank.ACE,Suit.D, Rank.ACE, Suit.H, Rank.ACE, Suit.S, Rank.ACE, Suit.C, Rank.FOUR);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.POKER,pokerService.getHandCategory(handPoker));
		
	}
	
	@Test
	public void checkCategoryStraightFlush(){
		
		Hand handStraightFlush=generateHand(Suit.H, Rank.ACE,Suit.H, Rank.KING, Suit.H, Rank.QUEEN, Suit.H, Rank.JACK, Suit.H, Rank.TEN);
		
		GetHandCategory pokerService=new PokerServiceImpl();
		assertEquals(Category.STRAIGHT_FLUSH,pokerService.getHandCategory(handStraightFlush));
		
	}
	
	@Test
	public void straightFlushLowAceIsHigherThanFullHouse(){
		
		
		Hand handStraightFlushLowAce=generateHand(Suit.H, Rank.TWO,Suit.H, Rank.THREE, Suit.H, Rank.FOUR, Suit.H, Rank.FIVE, Suit.H, Rank.ACE);
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraightFlushLowAce,pokerService.calculateHighestRank(handStraightFlushLowAce, handFullHouse));
		
	}
	
	@Test
	public void straightFlushIsHigherThanStraightFlushLowAce(){
		
		Hand handStraightFlush=generateHand(Suit.H, Rank.ACE,Suit.H, Rank.KING, Suit.H, Rank.QUEEN, Suit.H, Rank.JACK, Suit.H, Rank.TEN);
		Hand handStraightFlushLowAce=generateHand(Suit.S, Rank.TWO,Suit.S, Rank.THREE, Suit.S, Rank.FOUR, Suit.S, Rank.FIVE, Suit.S, Rank.ACE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraightFlush,pokerService.calculateHighestRank(handStraightFlush, handStraightFlushLowAce));
		
	}
	
	@Test
	public void straightIsHigherThanStraightLowAce(){
		
		Hand handStraight=generateHand(Suit.C, Rank.QUEEN,Suit.H, Rank.JACK, Suit.H, Rank.TEN, Suit.C, Rank.NINE, Suit.C, Rank.EIGHT);
		Hand handStraightLowAce=generateHand(Suit.H, Rank.TWO,Suit.D, Rank.THREE, Suit.S, Rank.FOUR, Suit.H, Rank.FIVE, Suit.H, Rank.ACE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraight,pokerService.calculateHighestRank(handStraight, handStraightLowAce));
		
	}
	
	@Test
	public void firstHighCardHandIsHigherThanSecondHighCardHand(){
		
		Hand firstHighCardHand=generateHand(Suit.C, Rank.TWO,Suit.H, Rank.THREE, Suit.H, Rank.FIVE, Suit.C, Rank.TEN, Suit.C, Rank.QUEEN);
		Hand secondHighCardHand=generateHand(Suit.H, Rank.FOUR,Suit.D, Rank.SIX, Suit.S, Rank.SEVEN, Suit.H, Rank.NINE, Suit.H, Rank.JACK);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(firstHighCardHand,pokerService.calculateHighestRankSameCategory(firstHighCardHand, secondHighCardHand));
		
	}
	
	@Test
	public void firstHighCardHandHasSameRankAsSecondHighCarHand(){
		
		Hand firstHighCardHand=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.FIVE,Suit.S, Rank.FOUR,Suit.S, Rank.QUEEN,Suit.S, Rank.SEVEN);
		Hand secondHighCarHand=generateHand(Suit.D, Rank.SEVEN,Suit.D, Rank.EIGHT,Suit.D, Rank.QUEEN,Suit.C, Rank.FIVE,Suit.C, Rank.FOUR);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertNull(pokerService.calculateHighestRankSameCategory(firstHighCardHand, secondHighCarHand));
		
	}
	
	@Test
	public void firstPairHandIsHigherThanSecondPairHand(){
		
		Hand firstPairHand=generateHand(Suit.C, Rank.SEVEN,Suit.D, Rank.FIVE,Suit.S, Rank.QUEEN,Suit.C, Rank.QUEEN,Suit.S, Rank.FOUR);
		Hand secondPairHand=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.QUEEN,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
				
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(firstPairHand,pokerService.calculateHighestRankSameCategory(firstPairHand, secondPairHand));
		
	}
	
	@Test
	public void secondTwoPairHandIsHigherThanFirstTwoPairHand(){
		
		Hand firstTwoPairHand=generateHand(Suit.H, Rank.FOUR,Suit.H, Rank.THREE,Suit.S, Rank.FOUR,Suit.D, Rank.EIGHT,Suit.C, Rank.THREE);
		Hand secondTwoPairHand=generateHand(Suit.H, Rank.ACE,Suit.S, Rank.THREE,Suit.D, Rank.FOUR,Suit.D, Rank.THREE,Suit.C, Rank.FOUR);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(secondTwoPairHand,pokerService.calculateHighestRankSameCategory(firstTwoPairHand, secondTwoPairHand));
		
	}
	
	@Test
	public void firstThreeOfAKindHandIsHigherThanSecondThreeOfAKindHand(){
		
		Hand firstThreeOfAKindHand=generateHand(Suit.H, Rank.FOUR,Suit.H, Rank.FIVE,Suit.S, Rank.FIVE,Suit.D, Rank.EIGHT,Suit.D, Rank.FIVE);
		Hand secondThreeOfAKindHand=generateHand(Suit.H, Rank.ACE,Suit.S, Rank.FOUR,Suit.D, Rank.FOUR,Suit.D, Rank.THREE,Suit.C, Rank.FOUR);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(firstThreeOfAKindHand,pokerService.calculateHighestRankSameCategory(firstThreeOfAKindHand, secondThreeOfAKindHand));
		
	}
	
	@Test
	public void firstStraightHandHasSameRankAsSecondStraightHand(){
		
		Hand firstStraightHand=generateHand(Suit.H, Rank.FOUR,Suit.H, Rank.FIVE,Suit.S, Rank.SIX,Suit.D, Rank.SEVEN,Suit.D, Rank.EIGHT);
		Hand SecondStraightHand=generateHand(Suit.D, Rank.FOUR,Suit.S, Rank.FIVE,Suit.D, Rank.SIX,Suit.H, Rank.SEVEN,Suit.C, Rank.EIGHT);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertNull(pokerService.calculateHighestRankSameCategory(firstStraightHand, SecondStraightHand));
		
	}
	
	@Test
	public void firstFlushHandIsHigherThanSecondFlushHand(){
		
		Hand firstFlushHand=generateHand(Suit.H, Rank.JACK,Suit.H, Rank.TEN,Suit.H, Rank.NINE,Suit.H, Rank.FIVE,Suit.H, Rank.THREE);
		Hand secondFlushHand=generateHand(Suit.D, Rank.TWO,Suit.D, Rank.FOUR,Suit.D, Rank.FIVE,Suit.D, Rank.EIGHT,Suit.D, Rank.TEN);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(firstFlushHand,pokerService.calculateHighestRankSameCategory(firstFlushHand, secondFlushHand));
		
	}
	
	@Test
	public void firstFullHouseHandIsHigherThanSecondFullHouseHand(){
		
		Hand firstFullHouseHand=generateHand(Suit.H, Rank.JACK,Suit.D, Rank.JACK,Suit.S, Rank.FIVE,Suit.S, Rank.JACK,Suit.D, Rank.FIVE);
		Hand secondFullHouseHand=generateHand(Suit.H, Rank.NINE,Suit.S, Rank.NINE,Suit.D, Rank.NINE,Suit.D, Rank.THREE,Suit.C, Rank.THREE);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(firstFullHouseHand,pokerService.calculateHighestRankSameCategory(firstFullHouseHand, secondFullHouseHand));
		
	}
	
	@Test
	public void firstPokerHandIsHigherThanSecondPokerHouseHand(){
		
		Hand firstPokerHand=generateHand(Suit.H, Rank.JACK,Suit.D, Rank.JACK,Suit.S, Rank.JACK,Suit.C, Rank.JACK,Suit.D, Rank.FIVE);
		Hand secondPokerHand=generateHand(Suit.H, Rank.NINE,Suit.S, Rank.NINE,Suit.D, Rank.NINE,Suit.C, Rank.NINE,Suit.C, Rank.THREE);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(firstPokerHand,pokerService.calculateHighestRankSameCategory(firstPokerHand, secondPokerHand));
		
	}
	
	@Test
	public void firstStraightFlushHandIsHigherThanSecondStraightFlushHouseHand(){
		
		Hand firstStraightFlushHand=generateHand(Suit.H, Rank.ACE,Suit.H, Rank.KING,Suit.H, Rank.QUEEN,Suit.H, Rank.JACK,Suit.H, Rank.TEN);
		Hand secondStraightFlushHouseHand=generateHand(Suit.H, Rank.TWO,Suit.H, Rank.THREE,Suit.H, Rank.FOUR,Suit.H, Rank.FIVE,Suit.H, Rank.SIX);
		
		PokerServiceImpl pokerService=new PokerServiceImpl();
		assertEquals(firstStraightFlushHand,pokerService.calculateHighestRankSameCategory(firstStraightFlushHand, secondStraightFlushHouseHand));
		
	}
	
	

	private Hand generateHand(Suit suit1,Rank rank1, Suit suit2, Rank rank2, Suit suit3, Rank rank3, Suit suit4, Rank rank4, Suit suit5, Rank rank5){
		
		Card card1=new Card(suit1, rank1);
		Card card2=new Card(suit2, rank2);
		Card card3=new Card(suit3, rank3);
		Card card4=new Card(suit4, rank4);
		Card card5=new Card(suit5, rank5);
		
		List<Card> cards=new ArrayList<Card>();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		
		return new Hand(cards);
	}

}
