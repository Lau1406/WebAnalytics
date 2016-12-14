package HW2;

import data.*;

/**
 *
 * @author sjef
 */
public class Exercise1b extends Exercise  {

    @Override
    public void run() {

        Specificity qm = new Specificity(recordSet, new MatchFilter());
        FindSubGroup fs = new FindSubGroup(recordSet, qm, 1);

        fs.excludeAttribute("match");
        fs.excludeAttribute("decision");
        fs.excludeAttribute("decision_o");

        fs.run();
        //System.out.println(qm.getScore(filteredSet));

    }
    
}
