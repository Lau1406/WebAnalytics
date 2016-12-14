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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which exercise do you want to execute? (e.g. '1a')");
        String identifier = scanner.nextLine().trim();

        exercise(identifier);
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
