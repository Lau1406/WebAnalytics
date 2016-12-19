package data;

/**
 * Measure the quality of a filter
 * @author Sjef
 */
public interface QualityMeasure {
    public abstract float getScore(Filter filter);
}
