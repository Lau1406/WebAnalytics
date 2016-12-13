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
    
}
