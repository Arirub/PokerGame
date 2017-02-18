package de.flare.poker.game;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.flare.poker.game.model.Card;
import de.flare.poker.game.model.Hand;
import de.flare.poker.game.model.enums.Rank;
import de.flare.poker.game.model.enums.Suit;
import de.flare.poker.game.pokerService.PokerService;
import de.flare.poker.game.pokerserviceimpl.PokerServiceImpl;

@Path("/hi")
public class PokerRest {

	@GET
	@Path("/ari")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello() {

		//PokerService poker=new PokerService();
		
		Hand hand1=generateHand(Suit.H, Rank.ACE,Suit.H, Rank.KING,Suit.H, Rank.QUEEN,Suit.H, Rank.JACK,Suit.H, Rank.TEN);
		Hand hand2=generateHand(Suit.H, Rank.TWO,Suit.H, Rank.THREE,Suit.H, Rank.FOUR,Suit.H, Rank.FIVE,Suit.H, Rank.SIX);
		List<Hand> hands=new ArrayList<Hand>();
		hands.add(hand1);
		hands.add(hand2);
		
		Card card1=new Card(Suit.H, Rank.ACE);
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(hands);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			  			
			 e.printStackTrace();		
		}
		
		return Response.status(200).entity("hola guapa").build();
		
	}
//	[
//	{"cards":
//		[{"suit":"H","rank":"ACE"},
//		{"suit":"H","rank":"KING"},
//		{"suit":"H","rank":"QUEEN"},
//		{"suit":"H","rank":"JACK"},
//		{"suit":"H","rank":"TEN"}]},
//	{"cards":
//		[{"suit":"H","rank":"TWO"},
//		{"suit":"H","rank":"THREE"},
//		{"suit":"H","rank":"FOUR"},
//		{"suit":"H","rank":"FIVE"},
//		{"suit":"H","rank":"SIX"}]}
//]
	@POST
	@Path("/winner")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWinnerHand(List<Hand> hands){
		
		System.out.println(hands.size());
		
		PokerService pokerService= new PokerServiceImpl();
		Hand winnerHand=pokerService.calculateHighestRank(hands.get(0), hands.get(1));
		for(int i=0;i<winnerHand.getCards().size();i++){
			System.out.println(winnerHand.getCards().get(i).getSuit()+""+winnerHand.getCards().get(i).getRank());
		}
		
		String result="The winner is:"+winnerHand.toString();
		return Response.status(200).entity(result).build();
	}
	//{"suit":"S","rank":"TWO"}
	@POST
	@Path("/suit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSuit(Card card){
		
		System.out.println(card.getSuit());
		String result="The winner is:";
		return Response.status(200).entity(result).build();
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
