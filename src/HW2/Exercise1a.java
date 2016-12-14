package HW2;

import data.FindSubGroup;
import data.WeightedRelativeAccuracy;

/**
 *
 * @author sjef
 */
public class Exercise1a extends Exercise {
    

    @Override
    public void run() {

        WeightedRelativeAccuracy qm = new WeightedRelativeAccuracy(recordSet, new MatchFilter());
        FindSubGroup fs = new FindSubGroup(recordSet, qm, 0);
        fs.excludeAttribute("match");
        fs.excludeAttribute("decision");
        fs.excludeAttribute("decision_o");
        
        fs.run();
//        System.out.println(qm.getScore(best));
        
    }
}
