package data;

/**
 * Class to measure the quality of a subgroup.
 * @author sjef
 */
public abstract class BinarySubgroupQualityMeasure {
    protected RecordSet originalset;
    protected int originalPositives;
    protected Filter positiveFilter;
    
    /**
     * The originalset and a filter that filters if a record is positively classified are required.
     * @param originalset the original record set
     * @param positiveFilter a filter that can filter the records that are positively classified
     */
    public BinarySubgroupQualityMeasure(RecordSet originalset, Filter positiveFilter){
        this.originalset = originalset;
        this.positiveFilter = positiveFilter;
        
        this.originalPositives = this.originalset.filteredCount(positiveFilter);
    }
    
    /**
     * Get quality measure score regarding given subgroup
     * @param subGroup
     * @return float score
     */
    public abstract float getScore(RecordSet subGroup);
    
    public float getScore(Filter filter){
        return this.getScore(RecordSet.filter(originalset, filter));
    }
}
