package data;

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if(!(other instanceof AttributeFilter)) return false;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.attribute);
        hash = 41 * hash + Objects.hashCode(this.value);
        return hash;
    }
}
