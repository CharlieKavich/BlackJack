import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Black Jack final project.
 * This is the main app that has the logic and interfacing for a game of blackjack.
 * @author Charlie Kavich
 * @since 11-20-25
 */

public class App {
    /**
     * This is an integer representing the amount of cards in the full 6 decks
     * that make of a shoe for casino blackjack. Once the shoe array list called deck
     * is less than half of the total shoe size in this int, it will be refreshed.
     */
    private static final int shoeSize = 312;
    /**
     * This is an array list of cards representing the 6 decks that make up the shoe.
     * It is literally just an array list that will be initialized with 6 full decks
     * of playing cards represented by the card class. I should have named it shoe,
     * but by the time I thought about it, it was already implemented in a bunch of places
     * as deck.
     */
    private static ArrayList<Card> deck = new ArrayList<Card>();
    /**
     * This is an array list that represents the player's hand of cards.
     * The cards are represented by the card class.
     */
    private static ArrayList<Card> playerHand = new ArrayList<Card>();
    /**
     * This is an array list that represents the computer/dealer's hand of cards.
     * The cards are represented by the card class.
     */
    private static ArrayList<Card> compHand = new ArrayList<Card>();
    /**
     * Instance variable for my random object so I don't have to repeatedly declare new ones.
     */
    private static Random random = new Random();
    /**
     * Instance variable for my scanner object so I don't have to repeatedly declare new ones.
     */
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean cont = true;
        int handCounter = 1;
        initializeDeck();
        System.out.println("Welcome to Black Jack!\nSpecial Rules:\n1. Six Deck Shoe that will be shuffled once less than half of the cards remain.\n2. Face cards are worth 10 and Aces are worth 11 unless such a number would make the player or dealer bust, in which case the Ace gos to 1.\n3. The dealer hits until his hand is worth >= 17.\n");

        do {
            System.out.println("Hand " + handCounter + ":");
            handInitialize();
            displayCompHand(false);
            handStateCheck(playerHand);
            displayPlayerHand();
            int canContinue = handStateCheck(playerHand);
            boolean invalid = true;
            while(canContinue == 1)
            {
                String inputs = "";
                while(invalid)
                {
                    System.out.println("Would you like to hit? (Y/N)");
                    try 
                    {
                        inputs = in.nextLine();
                        invalid = false;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Your input was invalid please try again. 2");
                    }

                }

                if(inputs.toLowerCase().equals("y"))
                {
                    addCard(playerHand);
                    System.out.print("You chose to hit.");
                    handStateCheck(playerHand); //this is only here to update the value of the ace in case you go over 21
                    displayPlayerHand();
                    
                }
                else if(inputs.toLowerCase().equals("n"))
                {
                    System.out.print("You chose to stay.");
                    displayPlayerHand();
                    break;
                }
                else
                {
                    System.out.println("Your input was invalid please try again 1.");
                }

                invalid = true;
                canContinue = handStateCheck(playerHand);
            }

            gameEndAndDealerAction();

            boolean exit = false;
            while(!exit)
            {
                invalid = true;
                String playAgainAnswer = "";
                while(invalid)
                    {
                        System.out.println("Would you like to play again? (Y/N)");
                        try 
                        {
                            playAgainAnswer = in.nextLine();
                            invalid = false;
                        } 
                        catch (InputMismatchException e) 
                        {
                            System.out.println("Your input was invalid please try again.");
                        }

                    }
                
                if(playAgainAnswer.toLowerCase().equals("y"))
                {
                    cont = true;
                    exit = true;
                }
                else if(playAgainAnswer.toLowerCase().equals("n"))
                {
                    System.out.println("Goodbye!");
                    cont = false;
                    exit = true;
                }
                else
                {
                    System.out.println("Your input was invalid please try.");
                }
            }
            
            if(deck.size() < shoeSize / 2)
            {
                initializeDeck();
            }

            handCounter += 1;
        } while (cont); 
        


    }
    /**
     * This method is where the logic of who wins and the dealers actions reside.
     * If the player doesn't bust, the dealer will hit until they value of their hand is greater than 17 or they bust.
     * In the case neither the player or dealer busts, whichever hand is closer to 21 wins.
     * In the case of a tie, a tie message will present itself.
     */
    public static void gameEndAndDealerAction() 
    {
        if(handStateCheck(playerHand) == 2)
        {
            System.out.println("The player busts and therefore the dealer wins!");
        }
        else
        {
            System.out.println("\nThe dealer will now show.");
            displayCompHand(true);
            while(handStateCheck(compHand) == 1 && handTotal(compHand) < 17)
            {
                addCard(compHand);
                System.out.println("\nThe dealer hits.");
                handStateCheck(compHand); //this is only here for the ace rule in case the dealer goes over 21
                displayCompHand(true);
            }
            if(handStateCheck(compHand) == 2)
            {
                System.out.println("The dealer busts and therefore the player wins!");
            }
            else if(handTotal(playerHand) > handTotal(compHand))
            {
                System.out.println("The player is closer to 21 and therefore wins!");
            }
            else if(handTotal(playerHand) < handTotal(compHand))
            {
                System.out.println("The dealer is closer to 21 and therefore wins!");
            }
            else 
            {
                System.out.println("It is a tie!");
            }
        }
    }

    /**
     * This method simply displays the cards in your hand and their total value.
     */
    public static void displayPlayerHand()
    {
        System.out.println("\nYour hand:");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println("Card " + (i + 1) + ": " + playerHand.get(i).getName());
        }
        System.out.println("Your total is currently: " + handTotal(playerHand));
    }

    /**
     * This method displays the dealers hand.
     * Before the player makes their actions the dealer's
     * first card is hidden. After the player is done
     * making decisions, the dealer reveals the hidden card.
     * @param ready this boolean reprents whether or not the dealer is ready to reveal the hidden card.
     */
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
            System.out.println("The dealer's total is currently: " + handTotal(compHand));
        }
        
    }

    /**
     * Initializes the hands for the beginning of a round of blackjack
     * by clearing whatever was in the hands and adding 2 cards
     * for both the player and the dealer.
     */
    public static void handInitialize()
    {
    
        playerHand.removeAll(playerHand);
        compHand.removeAll(compHand);
        for (int i = 0; i < 2; i++) {
            addCard(playerHand);
            addCard(compHand);
        }
    }

    /**
     * This method checks a hand to see what state it is in of which there are three.
     * The first is blackjack, the second is playable, and the third is busted.
     * This method also will update the value of the hand for the Ace rule.
     * The ace is 11 by default, but if it prevents you from busting, its value will go to 1.
     * @param hand the array list of cards which you are checking the handstate of
     * @return a 0 means the hand is a black jack, a 1 means the hand is still playable, and a 2 means the hand is a bust.
     */
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

    /**
     * Computes the total value of the hand specified by the parameter.
     * @param hand the array list for the total value of the cards to be calculated
     * @return the total value of the hand
     */
    public static int handTotal(ArrayList<Card> hand) 
    {
        int total = 0;
        for (int i = 0; i < hand.size(); i++) {
            total += hand.get(i).getValue();
        }
        return total;
    }   

    /**
     * Adds a card to the hand and removes it from the shoe.
     * @param hand the hand arraylist of cards that the card is being added to.
     */
    public static void addCard(ArrayList<Card> hand)
    {

        int index = random.nextInt(0, deck.size());
        hand.add(deck.get(index));
        deck.remove(index);
    }

    /**
     * Initializes the shoe by adding 6 decks of playing cards worth of card objects to the deck array list.
     */
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
