package data;

/**
 * Filter that filters all records where attribute equals a certain value
 * @author Sjef
 */
public class AttributeFilter extends Filter {
    protected String attribute;
    protected Object value;
    
    public AttributeFilter(String attribute, Object value)
    {
        this.attribute = attribute;
        this.value = value;
    }
    
    @Override
    public boolean filterRecord(Record record) {
        return record.get(this.attribute).equals(this.value);
    }
    
    @Override
    public String toString(){
        return "Attribute filter, " + attribute + "="+value;
    }
}
