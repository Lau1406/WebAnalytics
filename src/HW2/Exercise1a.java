package HW2;

import data.BinarySubgroupQualityMeasure;
import data.Filter;
import data.Record;
import data.RecordSet;

/**
 *
 * @author sjef
 */
public class Exercise1a extends Exercise {

    @Override
    public void run() {
        RecordSet filteredSet = RecordSet.filter(recordSet, new Filter() {
            @Override
            public boolean filterRecord(Record record) {
                return record.get("gaming").equals("10");
            }
        });
        
        BinarySubgroupQualityMeasure qm = new BinarySubgroupQualityMeasure(recordSet, filteredSet, new MatchFilter()) {

            @Override
            public float getScore() {
                //for now
                return 0;
            }
        };
        System.out.println(qm);
    }
}
