
package HW3;

import HW2.MatchFilter;
import data.AttributeEqualsFilterGenerator;
import data.BeamSearch;
import data.Constraints;
import data.Filter;
import data.FilterGenerator;
import data.MaxSizePriorityQueue;
import data.LooseConstraints;
import data.QualityMeasure;
import data.RecordSet;
import data.WeightedRelativeAccuracy;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

/**
 *
 * @author Sjef
 */
public class PerformBeamSearch {
    public static void run(RecordSet data){
        QualityMeasure qm = new WeightedRelativeAccuracy(data, new MatchFilter());
        Map<String, Set<String>> attrvals = data.getAttributeValues();
        attrvals.remove("match");
        attrvals.remove("decision");
        attrvals.remove("decision_o");
        attrvals.remove("has_null");
        FilterGenerator fg = new AttributeEqualsFilterGenerator(attrvals);
        Constraints c = new LooseConstraints();
        MaxSizePriorityQueue<Pair<Float,Filter>> r = BeamSearch.beamSearch(qm, fg, 5, 2, 10, c);
        while(!r.isEmpty())
        {
            Pair<Float, Filter> p = r.pollLast();
            System.out.println("\n=======\nScore: " + String.valueOf(p.getKey()) + " \n"+p.getValue().toString());
            
        }
    }
}
