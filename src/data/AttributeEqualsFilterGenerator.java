package data;

import java.util.Map;
import java.util.Set;

/**
 * Filter generator, creates a filter for every possible attr - value combination
 * @author Sjef
 */
public class AttributeEqualsFilterGenerator implements FilterGenerator{
    protected Map<String, Set<String>> attrvals;
    protected int nofpossibilities;
    
    public AttributeEqualsFilterGenerator(Map<String, Set<String>> attrvals){
        this.attrvals = attrvals;
        //calculate how many filters will be generated
        this.nofpossibilities = 0;
        for(Set<String> v : attrvals.values()){
            nofpossibilities += v.size();
        }
    }

    /**
     * Generate filters based on the attribute value possibilities
     * Every filter is combined through a LinkedFilter with the seed filter
     * @param seed Filter from where to begin
     * @return Array of generated filters
     */
    @Override
    public Filter[] generate(Filter seed) {
        Filter[] result = new Filter[this.nofpossibilities];
        int i=0;
        for(String attr : attrvals.keySet()){
            for(String val : this.attrvals.get(attr)){
                Filter f = new AttributeFilter(attr, val);
                if(seed instanceof Unfiltered)
                    //optimisation for the unfiltered filter
                    result[i++] = f;
                else
                    result[i++] = new LinkedFilter(seed, f);
            }
        }
        return result;
    } 
}
