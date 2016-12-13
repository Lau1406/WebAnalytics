package data;

/**
 *
 * @author sjef
 */
public class WeightedRelativeAccuracy extends BinarySubgroupQualityMeasure{

    public WeightedRelativeAccuracy(RecordSet originalset, RecordSet subgroup, Filter positiveFilter) {
        super(originalset, subgroup, positiveFilter);
    }

    @Override
    public float getScore() {
        //formula:
        //hwra = ((p+n)/(P+N))(p/(p+n) - P/(P+N))
        float p = (float)this.subgroupPositives;
        float P = (float)this.originalPositives;
        //(p+n)
        float ppn = (float)this.subgroup.getRecords().size();
        //(P+N)
        float PPN = (float)this.originalset.getRecords().size();
        return (ppn/PPN) * (p/ppn - P/PPN);
    }
    
}
