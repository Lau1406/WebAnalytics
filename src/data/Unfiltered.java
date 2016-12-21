package data;

/**
 * 
 * @author sjef
 */
public class Unfiltered extends Filter{
    @Override
    public boolean filterRecord(Record record) {
        return true;
    }
    
    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        return other instanceof Unfiltered;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
