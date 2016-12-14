package HW2;

import data.*;

/**
 *
 * @author sjef
 */
public class Exercise1b extends Exercise  {

    @Override
    public void run() {
        RecordSet filteredSet = RecordSet.filter(recordSet, new Filter() {
            @Override
            public boolean filterRecord(Record record) {
                return record.get("ambtition_important").equals("6.67") && record.get("match").equals("1") ;
            }
        });
        //System.out.println("Only " + filteredSet.getRecords().size() + " records have gaming=10");

        Specificity qm = new Specificity(recordSet, new MatchFilter());
        FindSubGroup fs = new FindSubGroup(recordSet, qm, 1);

        fs.excludeAttribute("match");
        fs.excludeAttribute("decision");
        fs.excludeAttribute("decision_o");

        fs.run();
        //System.out.println(qm.getScore(filteredSet));

    }
    
}
