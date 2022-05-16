package blackjack;

public enum Suits {
    DIAMOND("Diamonds"),
    HEART("Hearts"),
    SPADE("Spades"),
    CLUB("Clubs");

    String suitName;

    Suits(String suitName) {
        this.suitName = suitName;
    }

    public String toString() {
        return suitName;
    }
}
