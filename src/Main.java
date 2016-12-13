import data.Filter;
import data.Importer;
import data.Record;
import data.RecordSet;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Laurence on 2016-12-12.
 */
public class Main {

    private RecordSet recordSet;

    public static void main(String[] args) {
        if(args.length > 0){
            new Main().exercise(args[0]);
        } else {
            new Main().run();
        }
    }
    
    private void exercise(String identifier){
        this.importData(true);
        HW2.Exercise e;
        switch(identifier){
            case "1a":
                e = new HW2.Exercise1a();
                break;
            case "1b":
                e = new HW2.Exercise1b();
                break;
            case "1c":
                e = new HW2.Exercise1c();
                break;
            case "1d":
                e = new HW2.Exercise1d();
                break;
            default:
                System.out.println("Unknown exercise identifier");
                return;
        }
        e.setRecordSet(this.recordSet);
        e.run();
    }

    private void run() {
        importData(true);

        // usage examples:

        // 1. print a record (note; attributes order is semi-random, due to using a HashMap)
        System.out.println("Here's record number 13:");
        System.out.println(recordSet.getRecords().get(13));
        System.out.println();

        // 2. get possible values of an attribute
        String fieldName = "gender";
        System.out.println("Possible values for field '" + fieldName + "':");
        Set<String> possibleValues = recordSet.getPossibleValues(fieldName);
        for (String value : possibleValues) {
            System.out.println("- " + value);
        }
        System.out.println();

        // 3. filter record set
        RecordSet filteredSet = RecordSet.filter(recordSet, new Filter() {
            @Override
            public boolean filterRecord(Record record) {
                return record.get("gaming").equals("10");
            }
        });
        System.out.println("Only " + filteredSet.getRecords().size() + " records have gaming=10");
        System.out.println();
    }

    private void importData(boolean doPrintToConsole) {
        Importer importer = new Importer();

        long startTime = System.nanoTime();

        try {
            recordSet = importer.loadFromFile("data/dataset");
        } catch (IOException e) {
            throw new RuntimeException("Importer caused exception", e);
        }

        long endTime = System.nanoTime();

        if (doPrintToConsole) {
            System.out.println(recordSet.getRecords().size() + " records loaded (took " + (endTime - startTime) / 1000000000.0 + " seconds)");
            System.out.println();
        }
    }
}
