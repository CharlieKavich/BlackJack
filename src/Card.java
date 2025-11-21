public class Card {
    private String name;
    private int value;
    

    public Card(int value, String name)
    {
        this.value = value;
        this.name = name;
    }

    public int getValue()
    {
        return value;
    }

    public String getName()
    {
        return name;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return String.format("Name: %s\nValue: %d", name, value);
    }
    
}
