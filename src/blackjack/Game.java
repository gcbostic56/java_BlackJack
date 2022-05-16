package blackjack;

import java.util.Scanner;

public class Game {
    private Deck deck;
    private Deck cardAway;
    private int userMoney;
    private Dealer dealer;
    private User user;
    private int wins;
    private int losses;
    private int ties;
    private int myBet;
    public Game() {
        wins = 0;
        losses = 0;
        ties = 0;
        userMoney = 500;
        myBet = 0;
        deck = new Deck(true);
        cardAway = new Deck();

        dealer = new Dealer();
        user = new User();

        deck.shuffleDeck();
        letsPlayAGame();
    }

    private void letsPlayAGame() {
        if (wins > 0 || losses > 0 || ties > 0) {
            System.out.println("Current standings... Wins: " + wins + " Losses: " + losses + " and Ties: " + ties);
            dealer.grabHand().clearDeck(cardAway);
            user.grabHand().clearDeck(cardAway);
        }
        if (deck.deckSize() < 4) {
            deck.newDeckDiscard(cardAway);
        }
        if (userMoney <= 0) {
            System.out.println("You are now poor, congratulations. It is time to exit the casino. Goodbye");
            System.exit(0);
        }

        takeBet();
        dealer.grabHand().drawCard(deck);
        dealer.grabHand().drawCard(deck);

        user.grabHand().drawCard(deck);
        user.grabHand().drawCard(deck);

        dealer.firstHand();
        user.putToTextHand();

        if (dealer.gotBlackjack()) {
            dealer.putToTextHand();
            if (user.gotBlackjack()) {
                System.out.println("It's a tie with both scoring 21!");
                ties++;
                toContinue();
            } else {
                System.out.println("Dealer has Black Jack. Sorry bub!");
                losses++;
                userMoney -= myBet;
                System.out.println("Not to worry you still have $" + userMoney);
                toContinue();
            }
        }

        if (user.gotBlackjack()) {
            System.out.println("You dirty dog! BLACK JACK! You did the thing.");
            wins++;
            userMoney += myBet;
            System.out.println("You won some money, my fellow gambler! You now have $ " + userMoney);
            toContinue();
        }

        questionDoubleDown(myBet);

        user.makeChoice(deck, cardAway);

        if (user.grabHand().getYourValue() > 21) {
            System.out.println("You have busted, my fellow player! TAKE THAT FAT L!");
            losses++;
            userMoney -= myBet;
            System.out.println("Not to worry you still have $" + userMoney);
            toContinue();
        }

        dealer.putToTextHand();
        while (true) {
            if (dealer.grabHand().getYourValue() < 18) {
                dealer.hitMe(deck, cardAway);

            } else {
                break;
            }
        }

        if (dealer.grabHand().getYourValue() > 21) {
            System.out.println("Dealer has busted! AYE!");
            wins++;
            userMoney += myBet;
            System.out.println("You won some money, my fellow gambler! You now have $ " + userMoney);
            toContinue();
        } else if (dealer.grabHand().getYourValue() > user.grabHand().getYourValue()) {
            userMoney -= myBet;
            System.out.println("Sorry my fellow player, you lose! :(");
            System.out.println("Not to worry you still have $" + userMoney);
            losses++;
        }

        else if (user.grabHand().getYourValue() > dealer.grabHand().getYourValue()) {
            System.out.println("You win bruh!");
            wins++;
            userMoney += myBet;
            System.out.println("You now have $ " + userMoney);

        } else {
            System.out.println("Tie!");
            ties++;
            System.out.println("You get your money back, my fellow gambler! You now have $ " + userMoney);
        }

        toContinue();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome fellow gambling addicts to another game of Blackjack!");
        
        System.out.println("You will start with $500 of betting money before I cut you off.");
        Game blackjack = new Game();
    }
    
    public void takeBet() {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Please place your bet in increments of $5.");
        // myBet = Integer.parseInt(scanner.nextLine());
        while(true) {
        System.out.println("Please place your bet in increments of $5.");
        myBet = Integer.parseInt(scanner.nextLine());
        if (myBet % 5 == 0 && myBet > 0) {
            break;
        } 
    }
    }

    private void toContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wish to continue?");
        System.out.println("Enter 1 for Continue.");
        System.out.println("Enter 0 to Exit Casino and Take A Well-Deserved Break.");
        int selection = scanner.nextInt();
        if (selection == 1) {
            letsPlayAGame();
        } else {
            System.out.println("Goodbye gambler!");
            System.exit(0);
        }
    }

    private void questionDoubleDown(int myBet) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wish to Double Down? 1 for yes, 2 for no.");
        int doubleDownChoice = scanner.nextInt();
        int DoubleBet = myBet * 2;
        if (doubleDownChoice == 1) {
            System.out.println("You have now bet " + DoubleBet);
            doubleDown(DoubleBet);
        } else {
            return;
        }
    }

    private void doubleDown(int DoubleBet) {
        user.grabHand().drawCard(deck);
        user.putToTextHand();
        if (user.grabHand().getYourValue() > 21) {
            System.out.println("You have busted, my fellow player! TAKE THAT FAT L!");
            losses++;
            userMoney -= DoubleBet;
            System.out.println("Not to worry you still have $" + userMoney);
            toContinue();
        }
        dealer.putToTextHand();
        while (true) {
            if (dealer.grabHand().getYourValue() < 18) {
                dealer.hitMe(deck, cardAway);

            } else {
                break;
            }
        }

        if (dealer.grabHand().getYourValue() > 21) {
            System.out.println("Dealer has busted! AYE!");
            wins++;
            userMoney += DoubleBet;
            System.out.println("You won some money, my fellow gambler! You now have $ " + userMoney);
            toContinue();
        } else if (dealer.grabHand().getYourValue() > user.grabHand().getYourValue()) {
            userMoney -= DoubleBet;
            System.out.println("Sorry my fellow player, you lose! :(");
            System.out.println("Not to worry you still have $" + userMoney);
            losses++;
        }

        else if (user.grabHand().getYourValue() > dealer.grabHand().getYourValue()) {
            System.out.println("You win bruh!");
            wins++;
            userMoney += DoubleBet;
            System.out.println("You now have $ " + userMoney);

        } else {
            System.out.println("Tie!");
            ties++;
            System.out.println("You get your money back, my fellow gambler! You now have $ " + userMoney);
        }

        toContinue();
        return;
    }
}