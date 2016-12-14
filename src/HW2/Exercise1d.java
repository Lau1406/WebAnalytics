package HW2;

import data.BinarySubgroupQualityMeasure;
import data.Filter;
import data.FindSubGroup;
import data.Hchi;
import data.Record;
import data.RecordSet;
import data.Unfiltered;
import data.WeightedRelativeAccuracy;

/**
 *
 * @author sjef
 */
public class Exercise1d extends Exercise  {

    @Override
    public void run() {
        
        Filter attr_like = new Filter() {
            @Override
            public boolean filterRecord(Record record) {
                return record.get("d_attractive_o").equals("[6-8]") && record.get("d_like").equals("[6-8]");
            }
        };
        Filter best = new Filter() {
            @Override
            public boolean filterRecord(Record record) {
                return record.get("match").equals("1");
            }
        };
        Filter prob = new Filter(){
            @Override
            public boolean filterRecord(Record record) {
                return record.get("d_guess_prob_liked").equals("[7-10]");
            }
        };
        Filter sharedint = new Filter() {

            @Override
            public boolean filterRecord(Record record) {
                return record.get("d_shared_interests_o").equals("[6-8]");
            }
        };
        Filter sharedintpar = new Filter() {

            @Override
            public boolean filterRecord(Record record) {
                return record.get("d_shared_interests_partner").equals("[6-8]");
            }
        };
        
        BinarySubgroupQualityMeasure qm = new Hchi(recordSet, new MatchFilter());
        FindSubGroup fs = new FindSubGroup(recordSet, qm, 0);
        fs.excludeAttribute("match");
        fs.excludeAttribute("decision");
        fs.excludeAttribute("decision_o");
        //fs.excludeAttribute("d_guess_prob_liked");
        
        //fs.run();
        System.out.println(qm.getScore(sharedintpar));
        System.out.println(qm.getScore(sharedint));
        System.out.println(qm.getScore(attr_like));
        System.out.println(qm.getScore(prob));
        System.out.println(qm.getScore(best));
        System.out.println(qm.getScore(new Unfiltered()));
    }
    
}
