package blackjack;

public abstract class Person {
    private Hand hand;
    private String name;

    public Person() {
        this.hand = new Hand();
        this.name = "";
    }
    public Hand grabHand() {
        return this.hand;
    }

    public void makeHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }
    
    public void makeName(String name) {
        this.name = name;
    }

    public void putToTextHand(){
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.getYourValue());
    }

    public void hitMe(Deck deck, Deck discard){
        if (!deck.doTheyHaveCards()) {
            deck.newDeckDiscard(discard);
        }
        this.hand.drawCard(deck);
        System.out.println(this.name + " gets a card");
        this.putToTextHand();
        

    }

    public boolean gotBlackjack(){
        if(this.grabHand().getYourValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }
}
