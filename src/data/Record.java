package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by jochem on 12-12-16.
 */
public class Record {
    private Map<String, String> attributes;

    public Record(List<String> attributes, List<String> values) {
        this.attributes = new HashMap<>();

        for (int n = 0; n < attributes.size(); n += 1) {
            String value = values.get(n);
            this.attributes.put(attributes.get(n), value);
        }
    }

    public String get(String key) {
        return this.attributes.get(key);
    }

    public Map<String, String> getAttributes() { return this.attributes; }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        for (Map.Entry<String, String> attribute : this.attributes.entrySet()) {
            joiner.add(attribute.getKey() + "=" + attribute.getValue());
        }
        return joiner.toString();
    }
}
