/**
 * This is the card class for the black jack final project.
 * @author Charlie Kavich
 * @since 11-20-25
 */
public class Card {
    /**
     * the name of the card with the number and class
     */
    private String name;
    /**
     * the value of the card.
     */
    private int value;
    
    /**
     * Constructor for the card
     * @param value the desired value for the card
     * @param name a string representing the card with its number and class
     */
    public Card(int value, String name)
    {
        this.value = value;
        this.name = name;
    }

    /**
     * getter method for the card's value
     * @return the value of the card
     */
    public int getValue()
    {
        return value;
    }

    /**
     * getter method for the card's name
     * @return the string representing the card
     */
    public String getName()
    {
        return name;
    }

    /**
     * A setter method for the value of the card.
     * @param value the new desired value for the card
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * A setter method for the name of the card.
     * @param name the new desired name for the card.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * toString method for the card that creates a string with the card's name and value.
     */
    public String toString()
    {
        return String.format("Name: %s\nValue: %d", name, value);
    }
    
}
