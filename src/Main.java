
import HW3.PerformBeamSearch;
import data.Importer;
import data.RecordSet;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Laurence on 2016-12-12.
 */
public class Main {

    private RecordSet recordSet;

    public static void main(String[] args) {
        if (args.length > 0) {
            new Main().exercise(args[0]);
        } else {
            new Main().run();
        }
    }

    private void exercise(String identifier) {
        HW2.Exercise e;
        switch (identifier) {
            case "3":
                //BeamSearch.beamSearch(recordSet, null, identifier, 1, 1, 3, null);
                this.importData("data/hw3/dataset_new_java_readable.csv",true);
                PerformBeamSearch.run(this.recordSet);
                break;
            case "2.1a":
                this.importData("data/dataset",true);
                e = new HW2.Exercise1a();
                e.setRecordSet(this.recordSet);
                e.run();
                break;
            case "2.1b":
                this.importData("data/dataset",true);
                e = new HW2.Exercise1b();
                e.setRecordSet(this.recordSet);
                e.run();
                break;
            case "2.1c":
                this.importData("data/dataset",true);
                e = new HW2.Exercise1c();
                e.setRecordSet(this.recordSet);
                e.run();
                break;
            case "2.1d":
                this.importData("data/dataset",true);
                e = new HW2.Exercise1d();
                e.setRecordSet(this.recordSet);
                e.run();
                break;
            default:
                System.out.println("Unknown exercise identifier");
                return;
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which exercise do you want to execute? (e.g. '2.1a')");
        String identifier = scanner.nextLine().trim();

        exercise(identifier);
    }

    private void importData(String path, boolean doPrintToConsole) {
        Importer importer = new Importer();

        long startTime = System.nanoTime();

        try {
            recordSet = importer.loadFromFile(path);
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
