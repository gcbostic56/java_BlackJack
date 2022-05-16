package blackjack;
import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }
    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }
    public void clearDeck(Deck discard) {
        discard.addAllCards(hand);
        hand.clear();
    }

    public String toString() {
        String output = "";
        for (Card card: hand) {
            output += card + " - ";
        }
        return output;
    }

    public int getYourValue() {
        int value = 0;
        int numberAces = 0;

        for (Card card: hand) {
            value += card.getActualValue();
            if (card.getActualValue() == 11) {
                numberAces ++;
            }
        }
        if (value > 21 && numberAces > 0) {
            while (numberAces > 0 && value > 21) {
                numberAces --;
                value -= 10;
            }
        }
        return value;
    }
    public Card getYourCard(int index) {
        return hand.get(index);
    }
}
