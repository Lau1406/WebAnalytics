package data;

/**
 *
 * @author sjef
 */
public class Hchi extends BinarySubgroupQualityMeasure{

    public Hchi(RecordSet originalset, Filter positiveFilter) {
        super(originalset, positiveFilter);
    }

    @Override
    public float getScore(RecordSet subGroup) {
        //formula:
        //hwra = ((p*N - P*n)^2 / (P + N)^2) ((P+N)/(P*(p+n)) + (P+N)/(N*(p+n)) + (P+N)/(P*(P+N-p-n)) + (P+N)/(N*(P+N-p-n)))
        float p = (float)subGroup.filteredCount(this.positiveFilter);
        float P = (float)this.originalPositives;
        float n = (float)subGroup.getRecords().size() - p;
        float N = (float)this.originalset.getRecords().size() - P;
        
        float hchi = (P+N)/(P * (p+n));
        hchi += (P+N)/(N*(p+n));
        hchi += (P+N)/(P * (P+N-p-n));
        hchi += (P+N)/(N*(P+N-p-n));
        
        hchi *= Math.pow(p*N - P*n, 2)/Math.pow(P+N,2);
        
        return hchi;
    }
    
}
