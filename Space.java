class Space {
    private int value;

    Space(int v){
        value = v;
    }

    // I know, this looks bad
    Space(Space space){ value = space.getValue(); }

    // Adds value of otherSpace to current space, sets value of otherSpace to 0
    void add(Space otherSpace){
        this.setValue(this.getValue() + otherSpace.getValue());
        otherSpace.setValue(0);
    }

    // Sets Space value to newValue
    void setValue(int newValue){
        value = newValue;
    }

    // Returns Space value as int
    int getValue(){
        return value;
    }

    // Returns true if two spaces have the same value
    boolean equals(Space otherSpace){
        return this.getValue() == otherSpace.getValue();
    }

    // Returns value as a String
    public String toString(){
        return Integer.toString(value);
    }
}
