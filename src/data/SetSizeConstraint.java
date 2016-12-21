package data;

/**
 *
 * @author Sjef
 */
public class SetSizeConstraint extends NoDuplicatesConstraint{
    protected int minsize;
    protected RecordSet originalset;
    
    public SetSizeConstraint(RecordSet originalSet, int minsize){
        this.minsize = minsize;
        this.originalset = originalSet;
    }
    
    
    @Override
    public boolean satisfy(Filter filter) {
        return RecordSet.filter(this.originalset, filter).getRecords().size() > this.minsize && super.satisfy(filter);
    }
}
