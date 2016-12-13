package HW2;

import data.Filter;
import data.Record;
import data.RecordSet;
import data.WeightedRelativeAccuracy;

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
                return record.get("importance_same_race").equals("1");
            }
        });
        
        WeightedRelativeAccuracy qm = new WeightedRelativeAccuracy(recordSet, filteredSet, new MatchFilter());
        System.out.println(qm);
        System.out.println(qm.getScore());
    }
}
