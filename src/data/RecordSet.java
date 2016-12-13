package data;

import java.util.*;

/**
 * Created by jochem on 12-12-16.
 */
public class RecordSet {
    /**
     * All records of this record set
     */
    private List<Record> records;

    /**
     * A mapping from attribute name to a set of possible values
     */
    private Map<String, Set<String>> attributeValues;

    /**
     * @return all records of this record set
     */
    public List<Record> getRecords() {
        return records;
    }

    /**
     * @return a mapping from attribute name to a set of possible values
     */
    public Map<String, Set<String>> getAttributeValues() {
        return attributeValues;
    }

    /**
     * Create an empty record set
     */
    public RecordSet() {
        this.records = new ArrayList<>();
        this.attributeValues = new HashMap<>();
    }

    /**
     * Copy a record set from another record set
     * @param copyFrom the record set to copy
     */
    public RecordSet(RecordSet copyFrom) {
        this();
        this.records.addAll(copyFrom.getRecords());
        this.attributeValues = copyFrom.getAttributeValues();
    }

    /**
     * Add a record to the record set with the specified attributeValues and values
     * @param attributes attributeValues of the record
     * @param values values of the record
     */
    public void addRecord(List<String> attributes, List<String> values) {
        for (int n = 0; n < attributes.size(); n += 1) {
            registerPossibleValue(attributes.get(n), values.get(n));
        }

        this.records.add(new Record(attributes, values));
    }

    /**
     * Add a record to the record set and update attributeValues
     * @param record record to add
     */
    public void addRecord(Record record) {
        for (Map.Entry<String,String> attribute : record.getAttributes().entrySet()) {
            registerPossibleValue(attribute.getKey(), attribute.getValue());
        }

        this.records.add(record);
    }

    /**
     * Add multiple records to the record set
     * @param records a list of records to add
     */
    private void addRecords(List<Record> records) {
        for (Record record : records) {
            addRecord(record);
        }
    }

    /**
     * Returns a set of possible values for a specified attribute
     * @param attributeName attribute name
     * @return a set of possible values
     */
    public Set<String> getPossibleValues(String attributeName) {
        return this.attributeValues.get(attributeName);
    }

    /**
     * Register a possible value for a specified attribute name
     * @param attributeName attribute name
     * @param value attribute value
     */
    private void registerPossibleValue(String attributeName, String value) {
        Set<String> possibleAttributeValues = this.getAttributeValues().get(attributeName);

        // create set if it doesn't exist yet.
        if (possibleAttributeValues == null) {
            possibleAttributeValues = new HashSet<>();
            this.attributeValues.put(attributeName, possibleAttributeValues);
        }

        possibleAttributeValues.add(value);
    }
    
    /**
     * Count the number of records in the current RecordSet that satisfy the given filter
     * @param filter filter to apply
     * @return number of records satisfying the given filter
     */
    public int filteredCount(Filter filter){
        return filter.applyFilterCount(this.getRecords());
    }

    /**
     * Copy the record set and return a new record set
     * @param copyFrom record set to copy from
     * @return copied record set
     */
    public static RecordSet copy(RecordSet copyFrom) {
        return new RecordSet(copyFrom);
    }
    
    /**
     * Filter a record set and return the new, filtered record set
     * @param filterFrom record set to filter from
     * @param filter filter to apply
     * @return filtered record set
     */
    public static RecordSet filter(RecordSet filterFrom, Filter filter) {
        List<Record> recordsToFilter = filterFrom.getRecords();
        List<Record> filteredRecords = filter.applyFilter(recordsToFilter);

        RecordSet recordSet = new RecordSet();
        recordSet.addRecords(filteredRecords);
        return recordSet;
    }
}
