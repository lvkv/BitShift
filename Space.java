public class Space {
    private int value;

    public Space(int v){
        value = v;
    }

    // Adds value of otherSpace to current space, sets value of otherSpace to 0
    public void add(Space otherSpace){
        this.setValue(this.getValue() + otherSpace.getValue());
        otherSpace.setValue(0);
    }

    // Sets Space value to newValue
    public void setValue(int newValue){
        value = newValue;
    }

    // Returns Space value as int
    public int getValue(){
        return value;
    }

    // Returns true if two spaces have the same value
    public boolean equals(Space otherSpace){
        return this.getValue() == otherSpace.getValue();
    }

    // Returns value as a String
    public String toString(){
        return Integer.toString(value);
    }
}
