package data;

/**
 * Generate filters based on a seed filter
 * @author Sjef
 */
public interface FilterGenerator {    
    public Filter[] generate(Filter seed);
}
