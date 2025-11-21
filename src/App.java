import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.lang.model.util.ElementScanner14;


public class App {
    private static final int shoeSize = 312;
    private static ArrayList<Card> deck = new ArrayList<Card>();
    private static ArrayList<Card> playerHand = new ArrayList<Card>();
    private static ArrayList<Card> compHand = new ArrayList<Card>();
    private static Random random = new Random();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean cont = true;
        int handCounter = 1;
        initializeDeck();
        System.out.println("Welcome to Black Jack!\nSpecial Rules:\n1. Six Deck Shoe that will be shuffled once less than half of the cards remain.\n");

        do {
            System.out.println("Hand " + handCounter + ":");
            handInitialize();
            displayCompHand(false);
            displayPlayerHand();
            int canContinue = handStateCheck(playerHand);
            while(canContinue == 1)
            {
                boolean invalid = true;
                String inputs = "";
                while(invalid)
                {
                    System.out.println("Would you like to hit? (Y/N)");
                    try 
                    {
                        inputs = in.nextLine();
                        invalid = false;
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Your input was invalid please try again.");
                    }

                }

                if(inputs.equals("y"))
                {
                    addCard(playerHand);
                    System.out.print("You chose to hit.");
                    displayPlayerHand();
                }
                else if(inputs.equals("n"))
                {
                    System.out.print("You chose to stay.");
                    displayPlayerHand();
                    break;
                }
                else
                {
                    System.out.println("Your input was invalid please try again.");
                }

                canContinue = handStateCheck(playerHand);
            }
            dealerAction();
            cont = false;
        } while (cont); 
        


    }

    public static void dealerAction() 
    {
        System.out.println("\nThe dealer will now show.");
        displayCompHand(true);
        while(handStateCheck(compHand) == 1 && handTotal(compHand) < 17)
        {
            addCard(compHand);
            System.out.println("\nThe dealer hits.");
            displayCompHand(true);
        } 

    }

    public static void displayPlayerHand()
    {
        System.out.println("\nYour hand:");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println("Card " + (i + 1) + ": " + playerHand.get(i).getName());
        }
    }

    public static void displayCompHand(boolean ready)
    {   
        if(!ready)
        {
            System.out.println("\nDealer hand:");
            System.out.println("Card 1: Hidden");
            for (int i = 1; i < compHand.size(); i++) {
            System.out.println("Card " + (i + 1) + ": " + compHand.get(i).getName());
            }
        }
        else
        {
            System.out.println("\nDealer hand:");
            for (int i = 0; i < compHand.size(); i++) {
                System.out.println("Card " + (i + 1) + ": " + compHand.get(i).getName());
            }
        }
        
    }

    public static void handInitialize()
    {
    
        playerHand.removeAll(playerHand);
        compHand.removeAll(compHand);
        for (int i = 0; i < 2; i++) {
            addCard(playerHand);
            addCard(compHand);
        }
    }

    public static int handStateCheck(ArrayList<Card> hand)
    {
        int total = handTotal(hand);
        int handState = 2; //There are three handstates. 0 is a BlackJack, 1 is a playing hand, and 2 is a bust
        int index = 0;
    
        while(total > 21 && index < hand.size())
        {
            if(hand.get(index).getValue() == 11)
                {
                    hand.get(index).setValue(1);
                }   
            total = handTotal(hand);
            index += 1;
        }

        if(total == 21)
        {
            handState = 0;
        }
        else if(total < 21)
        {
            handState = 1;
        }
        else
        {
            handState = 2;
        }

        return handState;

    }

    public static int handTotal(ArrayList<Card> hand) 
    {
        int total = 0;
        for (int i = 0; i < hand.size(); i++) {
            total += hand.get(i).getValue();
        }
        return total;
    }   

    public static void addCard(ArrayList<Card> hand)
    {
        int index = random.nextInt(0, deck.size());
        hand.add(deck.get(index));
        deck.remove(index);
    }

    public static void initializeDeck()
    {
        deck.removeAll(deck);
        for (int i = 0; i < 6; i++) {
            deck.add(new Card(11, "Ace of Hearts"));
            deck.add(new Card(2, "Two of Hearts"));
            deck.add(new Card(3, "Three of Hearts"));
            deck.add(new Card(4, "Four of Hearts"));
            deck.add(new Card(5, "Five of Hearts"));
            deck.add(new Card(6, "Six of Hearts"));
            deck.add(new Card(7, "Seven of Hearts"));
            deck.add(new Card(8, "Eight of Hearts"));
            deck.add(new Card(9, "Nine of Hearts"));
            deck.add(new Card(10, "Ten of Hearts"));
            deck.add(new Card(10, "Jack of Hearts"));
            deck.add(new Card(10, "Queen of Hearts"));
            deck.add(new Card(10, "King of Hearts"));

            deck.add(new Card(11, "Ace of Diamonds"));
            deck.add(new Card(2, "Two of Diamonds"));
            deck.add(new Card(3, "Three of Diamonds"));
            deck.add(new Card(4, "Four of Diamonds"));
            deck.add(new Card(5, "Five of Diamonds"));
            deck.add(new Card(6, "Six of Diamonds"));
            deck.add(new Card(7, "Seven of Diamonds"));
            deck.add(new Card(8, "Eight of Diamonds"));
            deck.add(new Card(9, "Nine of Diamonds"));
            deck.add(new Card(10, "Ten of Diamonds"));
            deck.add(new Card(10, "Jack of Diamonds"));
            deck.add(new Card(10, "Queen of Diamonds"));
            deck.add(new Card(10, "King of Diamonds"));

            deck.add(new Card(11, "Ace of Clubs"));
            deck.add(new Card(2, "Two of Clubs"));
            deck.add(new Card(3, "Three of Clubs"));
            deck.add(new Card(4, "Four of Clubs"));
            deck.add(new Card(5, "Five of Clubs"));
            deck.add(new Card(6, "Six of Clubs"));
            deck.add(new Card(7, "Seven of Clubs"));
            deck.add(new Card(8, "Eight of Clubs"));
            deck.add(new Card(9, "Nine of Clubs"));
            deck.add(new Card(10, "Ten of Clubs"));
            deck.add(new Card(10, "Jack of Clubs"));
            deck.add(new Card(10, "Queen of Clubs"));
            deck.add(new Card(10, "King of Clubs"));

            deck.add(new Card(11, "Ace of Spades"));
            deck.add(new Card(2, "Two of Spades"));
            deck.add(new Card(3, "Three of Spades"));
            deck.add(new Card(4, "Four of Spades"));
            deck.add(new Card(5, "Five of Spades"));
            deck.add(new Card(6, "Six of Spades"));
            deck.add(new Card(7, "Seven of Spades"));
            deck.add(new Card(8, "Eight of Spades"));
            deck.add(new Card(9, "Nine of Spades"));
            deck.add(new Card(10, "Ten of Spades"));
            deck.add(new Card(10, "Jack of Spades"));
            deck.add(new Card(10, "Queen of Spades"));
            deck.add(new Card(10, "King of Spades"));
        }
        
    }

    
}
