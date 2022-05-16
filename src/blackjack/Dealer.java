package blackjack;

public class Dealer extends Person {
    public Dealer() {
        super.makeName("Dealer");;
    }

    public void firstHand(){
        System.out.println("The dealer's hand looks like this:");
        System.out.println(super.grabHand().getYourCard(0));
        System.out.println("The second card is face down.");
    }
}
