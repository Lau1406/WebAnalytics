package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jochem on 12-12-16.
 */
public abstract class Filter {

    /**
     * Applies a filter to a list of records and returns the records that passed the filter in a new list
     * @param recordsToFilter list of records to filter
     * @return a new list of records containing only the records that passed the filter (i.e. filterRecord returned true)
     */
    public List<Record> applyFilter(List<Record> recordsToFilter) {
        List<Record> filteredRecords = new ArrayList<>();

        for (Record record : recordsToFilter) {
            if (filterRecord(record)) {
                filteredRecords.add(record);
            }
        }

        return filteredRecords;
    }

    /**
     * Filter a record in a dataset
     * @param record the record to filter
     * @return true if the record should remain in the dataset, false otherwise
     */
    public abstract boolean filterRecord(Record record);
}
