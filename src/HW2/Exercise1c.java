package HW2;


import data.Filter;
import data.Record;
import data.RecordSet;

import java.util.*;

/**
 *
 * @author sjef
 */
public class Exercise1c extends Exercise  {
    private ArrayList<Float> sensitivities;

    @Override
    public void run() {
        System.out.println("Exercise 1c");
        float threshold = 0.8f;

        // Brute force
        sensitivities = new ArrayList<>();
        float highestSansitivity = 0;
        String highestAttribute = "";
        String highestValue = "";

        ArrayList<String> attr = new ArrayList<>();
        attr.addAll(recordSet.getAttributeValues().keySet());
        ArrayList<String> values = new ArrayList<>();


        for (String s : attr) {
            // attribute 'match' gives 1 for obvious reasons, we skip this one
            if (s.equals("match")) continue;
            // Loop over all attributes
            values.clear();
            values.addAll(recordSet.getAttributeValues().get(s));
            for (String value : values) {
                // Loop over all possible values
                float sensitivity = genSensitivity(recordSet, s, value);
                sensitivities.add(sensitivity);
                if (sensitivity > threshold) {
                    System.out.println("High attribute value combo: " + s + ":" + value + ":" + sensitivity);
                    System.out.println("Positives:" + RecordSet.filter(recordSet, new Filter() {
                        @Override
                        public boolean filterRecord(Record record) {
                            return record.get(s).equals(value) && record.get("match").equals("1");
                        }
                    }).getRecords().size());
                    System.out.println("Negatives:" + RecordSet.filter(recordSet, new Filter() {
                        @Override
                        public boolean filterRecord(Record record) {
                            return record.get(s).equals(value) && record.get("match").equals("0");
                        }
                    }).getRecords().size());
                }
                if (sensitivity >= highestSansitivity) {
                    highestSansitivity = sensitivity;
                    highestAttribute = s;
                    highestValue = value;
                }
            }
        }

        // Not rly useful without a mapping to the attributes and values
        sensitivities.sort((o1, o2) -> {
            if (o1 > o2) return -1;
            if (o1 < o2) return 1;
            return 0;
        });

        System.out.println("Highest attribute value combo: " + highestAttribute + ":" + highestValue);
        System.out.println("With a sensitivity of: " + highestSansitivity);
        System.out.println("From all these values:");
        System.out.println(sensitivities);
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
