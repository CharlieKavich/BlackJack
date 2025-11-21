public class Card {
    private int number;
    private int suit;

    public String toString()
    {
        String suitString;
        switch (suit) {
            case 1:
                suitString = "Hearts";
                break;
            case 2:
                suitString = "Diamonds";
                break;
            case 3:
                suitString = "Clubs";
                break;
            case 4:
                suitString = "Spades";
                break;
            default:
                throw new AssertionError();
        }

        return String.format("%2d of ", number, suitString);
    }
    
}
