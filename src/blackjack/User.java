package blackjack;
import java.util.Scanner;

public class User extends Person {
    Scanner scanner = new Scanner(System.in);
// new player
    public User() {
        super.makeName("User");
    }

    public void makeChoice(Deck deck, Deck cardAway) {
        int choice = 0;
        boolean receiveNumber = true;

        while (receiveNumber) {
            try {
                System.out.println("Type in 1 for HIT and 2 for Stay.");
                choice = scanner.nextInt();
                receiveNumber = false;
            }
            catch (Exception e) {
                System.out.println("Invalid selection, bruh!");
                scanner.next();
            }
            if (choice == 1) {
                this.hitMe(deck, cardAway);
                if (this.grabHand().getYourValue()>20) {
                    return;
                }
                else {
                    this.makeChoice(deck, cardAway);
                }
            } else if (choice == 2) {
                System.out.println("You have stayed. Oh my!");
                break;
            }
        }
    }
}
