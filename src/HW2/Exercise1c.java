package HW2;


import data.Filter;
import data.Record;
import data.RecordSet;

/**
 *
 * @author sjef
 */
public class Exercise1c extends Exercise  {

    @Override
    public void run() {
        System.out.println("Exercise 1c");

        float sensitivity = genSensitivity(recordSet, "samerace", "1");
        System.out.println("sensitivity = " + sensitivity);
    }

    private float genSensitivity(RecordSet recordSet, String attr, String attrValue) {
        // Get big P
        int bigP = RecordSet.filter(recordSet, new Filter() {
            @Override
            public boolean filterRecord(Record record) {
                return record.get("match").equals("1");
            }
        }).getRecords().size();

        // Gen small p based on attr
        int smallP = RecordSet.filter(recordSet, new Filter() {
            @Override
            public boolean filterRecord(Record record) {
                return record.get(attr).equals(attrValue) && record.get("match").equals("1");
            }
        }).getRecords().size();
        return (float)smallP / bigP;
    }
    
}
