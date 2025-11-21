import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class App {
    private static ArrayList<Card> deck = new ArrayList<Card>();
    private static ArrayList<Card> playerHand = new ArrayList<Card>();
    private static ArrayList<Card> compHand = new ArrayList<Card>();
    private static Random random = new Random();
    public static void main(String[] args) throws Exception {
        initializeDeck();
        addCard(playerHand);
        System.out.println(playerHand.get(0).toString());
    }

    public static void hands()
    {

    }

    public static void addCard(ArrayList<Card> hand)
    {
        int index = random.nextInt(0, deck.size());
        hand.add(deck.get(index));
    }

    public static void initializeDeck()
    {
        deck.removeAll(deck);
        for (int i = 0; i < 6; i++) {
            deck.add(new Card(1, "Ace of Hearts"));
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

            deck.add(new Card(1, "Ace of Diamonds"));
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

            deck.add(new Card(1, "Ace of Clubs"));
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

            deck.add(new Card(1, "Ace of Spades"));
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
