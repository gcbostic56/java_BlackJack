package blackjack;


public class Card {
    private Suits suit;
    private Values value;


    public Card(Suits suit, Values value) {
        this.value = value;
        this.suit = suit;
    }

    // This takes the returned inputs and makes a copy card
    public Card(Card card) {
        this.suit = card.getSuits();
        this.value = card.getValue();
    }

    // the actual card value
    public int getActualValue() {
        return value.cardValue;
    }

    public Values getValue() {
        return this.value;
    }
    // The suit
    public Suits getSuits() {
        return this.suit;
    }
    public String toString() {
        return this.value.toString() + " of " + this.suit.toString();
    }
}
