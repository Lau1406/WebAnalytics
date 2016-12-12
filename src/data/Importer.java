package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jochem on 12-12-16.
 */
public class Importer {

    private RecordSet recordSet;
    private List<String> attributeNames;

    /**
     * Import the dataset from a file and return the recordset
     *
     * @param fileName file name of the data set
     * @returns the record set
     * @throws IOException if something went wrong when reading the file
     */
    public RecordSet loadFromFile(String fileName) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(fileName));

        this.recordSet = new RecordSet();
        this.attributeNames = new ArrayList<>();

        parseLines(lines);

        return this.recordSet;
    }

    /**
     * Parse a stream of lines
     * @param lines stream of lines
     */
    private void parseLines(Stream<String> lines) {
        lines.forEach(line -> {
            // skip empty lines
            if (line.trim().length() == 0) {
                return;
            }

            if (line.startsWith("@")) {
                parseAnnotatedLine(line);
            } else {
                parseRecord(line);
            }
        });
    }

    /**
     * Parse an annotated (@-) line
     * @param line line to parse
     */
    private void parseAnnotatedLine(String line) {
        // we don't care about the relation name.
        if (line.startsWith("@relation")) {
            return;
        }

        // attribute!
        if (line.startsWith("@attribute")) {
            String[] parts = line.split(" ", 3);

            assert(parts.length == 3);

            String attributeName = parts[1];
            String attributeValues = parts[2]; // ignored, for now..

            this.attributeNames.add(attributeName);
        }
    }

    /**
     * Parse a line as comma separated values
     * @param line line to parse
     */
    private void parseRecord(String line) {
        String splitRegex = "(?:(?:\"[^\"]*\")|(?:'[^']*'))?(\\,)";
        String[] values = line.split(splitRegex, this.attributeNames.size());
        List<String> valueList = new ArrayList<>();
        for(String value : values) {
            valueList.add(value);
        }

        this.recordSet.addRecord(this.attributeNames, valueList);
    }
}
