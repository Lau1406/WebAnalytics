package data;

/**
 * Created by Yorick on 13-12-2016.
 */
public class Specificity extends BinarySubgroupQualityMeasure {

    public Specificity(RecordSet originalset, Filter positiveFilter) {
        super(originalset, positiveFilter);
    }

    @Override
    public float getScore(RecordSet subgroup) {
        //formula:
        //specificity = 1 - (n/N)
        float totalp = (float)subgroup.filteredCount(this.positiveFilter);;
        float totalP = (float)this.originalPositives;
        float totalOrg = (float)this.originalset.getRecords().size();
        float totalSub = (float)subgroup.getRecords().size();
        float n = totalSub - totalp;
        float N = totalOrg - totalP;


        return (1 - (n/N));
    }



}