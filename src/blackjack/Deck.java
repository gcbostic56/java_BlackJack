package blackjack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }
    public Deck(Deck newDeck) {
        Collections.copy(this.deck, newDeck.getAllCards());
    }
    public Deck(boolean createFullDeck) {
        deck = new ArrayList<Card>();
        if (createFullDeck) {
            for (Suits suit: Suits.values()) {
                for (Values values: Values.values()) {
                    deck.add(new Card(suit, values));
                }
            }
        }
    }
    public void shuffleDeck() {
        Collections.shuffle(deck, new Random());
    }
    public Card drawCard() {
        Card newCard = new Card(deck.get(0));
        deck.remove(0);
        return newCard;
    }
    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }
    public void addAllCards(ArrayList<Card> cards) {
        deck.addAll(cards);
    }
    public boolean doTheyHaveCards() {
        if (deck.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int deckSize() {
        return deck.size();
    }

    public ArrayList<Card> getAllCards() {
        return deck;
    }
    public void clearDeck() {
        deck.clear();
    }

    public void newDeckDiscard(Deck discard){
        this.addAllCards(discard.getAllCards());
        this.shuffleDeck();
        discard.clearDeck();
        System.out.println("No more new cards, time to re-shuffle!");
    }
    
    public String toString() {
        String output = "";
        for (Card card: deck) {
            output += card;
            output += "\n";
        }
        return output;
    }
    
}
