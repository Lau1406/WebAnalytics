package HW2;

import data.Record;

/**
 *
 * @author sjef
 */
public class MatchFilter extends data.Filter {

    @Override
    public boolean filterRecord(Record record) {
        return record.get("match").equals("1");
    }
    
}
