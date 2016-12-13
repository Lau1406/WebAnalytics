package data;

/**
 * Class to measure the quality of a subgroup.
 * @author sjef
 */
public abstract class BinarySubgroupQualityMeasure {
    protected RecordSet originalset;
    protected RecordSet subgroup;
    protected int originalPositives, subgroupPositives;
    
    /**
     * The originalset, subgroup and a filter that filters if a record is positively classified are required.
     * @param originalset the original record set
     * @param subgroup a subgroup of the original set
     * @param positiveFilter a filter that can filter the records that are positively classified
     */
    public BinarySubgroupQualityMeasure(RecordSet originalset, RecordSet subgroup, Filter positiveFilter){
        this.originalset = originalset;
        this.subgroup = subgroup;
        
        this.originalPositives = this.originalset.filteredCount(positiveFilter);
        this.subgroupPositives = this.subgroup.filteredCount(positiveFilter);
    }
    
    public abstract float getScore();
    
    @Override
    public String toString(){
        return String.format("Number of positives: original %1$d/%2$d, subgroup %3$d/%4$d", 
                this.originalPositives, 
                this.originalset.getRecords().size(), 
                this.subgroupPositives, 
                this.subgroup.getRecords().size()
        );
    }
}
