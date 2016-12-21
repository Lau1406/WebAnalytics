package data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sjef
 */
public class NoDuplicatesConstraint implements Constraints {
    @Override
    public boolean satisfy(Filter filter) {
        Map<Filter, Integer> filterCount = new HashMap<>();
        this.countFilters(filter, filterCount);
        return filterCount.values().stream().allMatch(i -> i==1);
    }
    
    protected void countFilters(Filter filter, Map<Filter, Integer> filterCount){
        if(filter == null)
            return;
        if(filter instanceof LinkedFilter){
            this.countFilters(((LinkedFilter)filter).filter, filterCount);
            this.countFilters(((LinkedFilter)filter).nextFilter, filterCount);
        } else {
            if(filterCount.containsKey(filter)){
                filterCount.put(filter, filterCount.get(filter) + 1);
            } else {
                filterCount.put(filter, 1);
            }
        }
    }
}
