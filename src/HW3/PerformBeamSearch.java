
package HW3;

import data.*;
import javafx.util.Pair;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sjef
 */
public class PerformBeamSearch {
    public static void run(RecordSet data){
        String[] targets = new String[]{
            "click_count",
            "view_count",
            "pageview_count",
            "session_count"
        };
        MeanDifferenceModelClass mc = new MeanDifferenceModelClass(targets);
        NormalizedMeanDifferenceQualityMeasure qm = new NormalizedMeanDifferenceQualityMeasure(data, mc);
        Map<String, Set<String>> attrvals = data.getAttributeValues();
        for(String t: targets){
            attrvals.remove(t);
        }
        attrvals.remove("user_id");
        FilterGenerator fg = new AttributeEqualsFilterGenerator(attrvals);
        Constraints c = new SetSizeConstraint(data, 25);

        MaxSizePriorityQueue<Pair<Float,Filter>> r = BeamSearch.beamSearch(qm, fg, 10, 5, 10, c);
        while(!r.isEmpty())
        {
            Pair<Float, Filter> p = r.pollLast();
            System.out.println("\n=======Set size:" + String.valueOf(RecordSet.filter(data, p.getValue()).getRecords().size()) +
                    "\nScore: " + String.valueOf(p.getKey()) + " \n"+p.getValue().toString());
            qm.print(p.getValue());

        }
    }
}
