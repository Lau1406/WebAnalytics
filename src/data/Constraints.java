package data;

/**
 * Checks if a filter satisfies certain requirements
 * @author Sjef
 */
public interface Constraints {
    public boolean satisfy(Filter filer);
}
