import data.Importer;
import data.RecordSet;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Laurence on 2016-12-12.
 */
public class Main {

    private RecordSet recordSet;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        importData();

        // usage examples:

        // 1. print a record (note; attributes order is semi-random, due to using a HashMap)
        System.out.println("Here's record number 13:");
        System.out.println(recordSet.getRecords().get(13))

        // 2. get possible values of an attribute
        String fieldName = "gender";
        System.out.println("Possible values for field '" + fieldName + "':");
        Set<String> possibleValues = recordSet.getPossibleValues(fieldName);
        for (String value : possibleValues) {
            System.out.println("- " + value);
        }
    }

    private void importData() {
        Importer importer = new Importer();

        long startTime = System.nanoTime();

        try {
            recordSet = importer.loadFromFile("data/dataset");
        } catch (IOException e) {
            throw new RuntimeException("Importer caused exception", e);
        }

        long endTime = System.nanoTime();

        System.out.println(recordSet.getRecords().size() + " records loaded (took " + (endTime - startTime) / 1000000000.0 + " seconds)");
        System.out.println();
    }
}
