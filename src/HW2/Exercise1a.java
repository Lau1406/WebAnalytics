package HW2;

import data.Filter;
import data.FindSubGroup;
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
                return record.get("d_attractive_o").equals("[6-8]") && record.get("d_like").equals("[6-8]");
            }
        });
        
        WeightedRelativeAccuracy qm = new WeightedRelativeAccuracy(recordSet, new MatchFilter());
        FindSubGroup fs = new FindSubGroup(recordSet, qm, 0);
        fs.excludeAttribute("match");
        fs.excludeAttribute("decision");
        fs.excludeAttribute("decision_o");
        //fs.excludeAttribute("d_guess_prob_liked");
        
        fs.run();
        //System.out.println(qm.getScore(filteredSet));
        
    }
}
