package data;

/**
 *
 * @author Sjef
 */
public class LinkedFilter extends Filter{
    protected Filter filter;
    protected Filter nextFilter;

    public LinkedFilter(Filter filter, Filter nextFilter) {
        this.filter = filter;
        this.nextFilter = nextFilter;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Filter getNextFilter() {
        return nextFilter;
    }

    public void setNextFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }
    

    @Override
    public boolean filterRecord(Record record) {
        if(this.filter.filterRecord(record)){
            if(this.nextFilter == null || this.nextFilter.filterRecord(record)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "First: " + this.filter + "\nSecond: " + String.valueOf(this.nextFilter) +"\n";
    }
}
