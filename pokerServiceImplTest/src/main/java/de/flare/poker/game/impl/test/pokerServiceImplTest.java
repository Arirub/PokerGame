package de.flare.poker.game.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.flare.poker.game.model.Card;
import de.flare.poker.game.model.Hand;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;
import de.flare.poker.game.pokerService.PokerService;
import de.flare.poker.game.pokerseviceimpl.PokerServiceImpl;

public class pokerServiceImplTest {
	
	@Test
	public void pairIsHigherThanHighCard(){
		
		Hand handPair=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.QUEEN,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		Hand handHighCard=generateHand(Suit.C, Rank.ACE_HIGH,Suit.D, Rank.TEN, Suit.H, Rank.FIVE, Suit.S, Rank.FOUR, Suit.C, Rank.TWO);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handPair,pokerService.calculateHigherRank(handPair, handHighCard));
		
	}
	
	@Test
	public void twoPairIsHigherThanPair(){
		
		Hand handTwoPairs=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.NINE,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		Hand handPair=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.QUEEN,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handTwoPairs,pokerService.calculateHigherRank(handTwoPairs, handPair));
		
	}
	
	@Test
	public void threeOfAKindIsHigherThanTwoPairs(){
		
		Hand handThreeOfAKind=generateHand(Suit.C, Rank.KING,Suit.D, Rank.KING,Suit.H, Rank.KING,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		Hand handTwoPairs=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.NINE,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handThreeOfAKind,pokerService.calculateHigherRank(handThreeOfAKind, handTwoPairs));
		
	}
	
	@Test
	public void straightIsHigherThanThreeOfAKind(){
		
		
		Hand handStraight=generateHand(Suit.C, Rank.QUEEN,Suit.H, Rank.JACK, Suit.H, Rank.TEN, Suit.C, Rank.NINE, Suit.C, Rank.EIGHT);
		Hand handThreeOfAKind=generateHand(Suit.C, Rank.KING,Suit.D, Rank.KING,Suit.H, Rank.KING,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraight,pokerService.calculateHigherRank(handStraight, handThreeOfAKind));
		
	}
	
	@Test
	public void flushIsHigherThanStraight(){
		
		Hand handFlush=generateHand(Suit.D, Rank.TEN,Suit.D, Rank.EIGHT,Suit.D, Rank.SEVEN,Suit.D, Rank.FIVE,Suit.D, Rank.THREE);
		Hand handStraight=generateHand(Suit.C, Rank.QUEEN,Suit.H, Rank.JACK, Suit.H, Rank.TEN, Suit.C, Rank.NINE, Suit.C, Rank.EIGHT);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handFlush,pokerService.calculateHigherRank(handFlush, handStraight));
		
	}
	
	@Test
	public void fullHouseIsHigherThanFlush(){
		
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		Hand handFlush=generateHand(Suit.D, Rank.TEN,Suit.D, Rank.EIGHT,Suit.D, Rank.SEVEN,Suit.D, Rank.FIVE,Suit.D, Rank.THREE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handFullHouse,pokerService.calculateHigherRank(handFullHouse, handFlush));
		
	}
	
	@Test
	public void pokerIsHigherThanFullHouse(){
		
		Hand handPoker=generateHand(Suit.C, Rank.ACE_HIGH,Suit.D, Rank.ACE_HIGH, Suit.H, Rank.ACE_HIGH, Suit.S, Rank.ACE_HIGH, Suit.C, Rank.FOUR);
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handPoker,pokerService.calculateHigherRank(handPoker, handFullHouse));
		
	}
	
	@Test
	public void straightFlushIsHigherThanPoker(){
		
		Hand handStraightFlush=generateHand(Suit.H, Rank.ACE_HIGH,Suit.H, Rank.KING, Suit.H, Rank.QUEEN, Suit.H, Rank.JACK, Suit.H, Rank.TEN);
		Hand handPoker=generateHand(Suit.C, Rank.ACE_HIGH,Suit.D, Rank.ACE_HIGH, Suit.H, Rank.ACE_HIGH, Suit.S, Rank.ACE_HIGH, Suit.C, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraightFlush,pokerService.calculateHigherRank(handStraightFlush, handPoker));
		
	}
	
	@Test
	public void pokerIsHigherThanTwoPairs(){
		
		Hand handPoker=generateHand(Suit.C, Rank.ACE_HIGH,Suit.D, Rank.ACE_HIGH, Suit.H, Rank.ACE_HIGH, Suit.S, Rank.ACE_HIGH, Suit.C, Rank.FOUR);
		Hand handTwoPairs=generateHand(Suit.C, Rank.EIGHT,Suit.D, Rank.EIGHT,Suit.D, Rank.NINE,Suit.C, Rank.NINE,Suit.C, Rank.FOUR);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handPoker,pokerService.calculateHigherRank(handPoker, handTwoPairs));
		
	}
	
	@Test
	public void straightFlushIsHigherThanFullHouse(){
		
		Hand handStraightFlush=generateHand(Suit.H, Rank.ACE_HIGH,Suit.H, Rank.KING, Suit.H, Rank.QUEEN, Suit.H, Rank.JACK, Suit.H, Rank.TEN);
		Hand handFullHouse=generateHand(Suit.C, Rank.NINE,Suit.D, Rank.NINE, Suit.H, Rank.NINE, Suit.C, Rank.FIVE, Suit.S, Rank.FIVE);
		
		PokerService pokerService=new PokerServiceImpl();
		assertEquals(handStraightFlush,pokerService.calculateHigherRank(handStraightFlush, handFullHouse));
		
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
