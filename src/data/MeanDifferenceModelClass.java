package data;

/**
 *
 * @author Sjef
 */
public class MeanDifferenceModelClass implements ModelClass<Float[]> {
    protected String[] targets;
    
    public MeanDifferenceModelClass(String[] targets){
        this.targets = targets;
    }
    @Override
    public Float[] fit(RecordSet data) {
        Float[] results = new Float[targets.length];
        for(int i = 0;i<results.length;i++){
            results[i] = 0f;
        }
        for(Record r : data.getRecords()){
            for(int i = 0;i<targets.length;i++){
                results[i] += Float.parseFloat(r.get(targets[i]));
            }
        }
        for(int i = 0;i<results.length;i++){
            results[i] /= data.getRecords().size();
        }
        return results;
    }
    
    
}
