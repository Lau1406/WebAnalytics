package data;

/**
 *
 * @author sjef
 */
public class WeightedRelativeAccuracy extends BinarySubgroupQualityMeasure{

    public WeightedRelativeAccuracy(RecordSet originalset, Filter positiveFilter) {
        super(originalset, positiveFilter);
    }

    @Override
    public float getScore(RecordSet subgroup) {
        //formula:
        //hwra = ((p+n)/(P+N))(p/(p+n) - P/(P+N))
        float p = (float)subgroup.filteredCount(this.positiveFilter);
        float P = (float)this.originalPositives;
        //(p+n)
        float ppn = (float)subgroup.getRecords().size();
        //(P+N)
        float PPN = (float)this.originalset.getRecords().size();
        return (ppn/PPN) * (p/ppn - P/PPN);
    }
    
}
