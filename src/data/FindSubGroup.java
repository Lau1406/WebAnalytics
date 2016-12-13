package data;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

/**
 * Class to find a subgroup that maximized a given quality measure
 * @author sjef
 */
public class FindSubGroup {
    protected RecordSet recordSet;
    protected BinarySubgroupQualityMeasure qualityMeasure;
    protected ArrayList<String> excludedAttributes;
    protected int depthmax = 2;
    protected float iterateThreshhold ;
    
    /**
     * 
     * @param recordSet the set to find subgroups in
     * @param qualityMeasure the quality measure
     * @param iterateThreshhold if the quality measure score is below this value don't continue on that path 
     */
    public FindSubGroup(RecordSet recordSet, BinarySubgroupQualityMeasure qualityMeasure, float iterateThreshhold)
    {
        this.recordSet = recordSet;
        this.qualityMeasure = qualityMeasure;
        this.excludedAttributes = new ArrayList<String>();
        this.iterateThreshhold = iterateThreshhold;
    }
    
    public void excludeAttribute(String attr){
        this.excludedAttributes.add(attr);
    }
    
    public void run(Filter initial_filter){
        RecordSet subset = RecordSet.filter(this.recordSet, initial_filter);
        Pair<Float, String>  r = this.iterate(0, this.qualityMeasure.getScore(subset), subset, "");
        System.out.println("Best with " + r.getValue() + "-> " + Float.toString(r.getKey()));
    }
    
    public void run(){
        this.run(new Unfiltered());
    }
    
    public Pair<Float,String> iterate(int depth, float maxscore, RecordSet subgroup, String qualifier){
        int n = 0;
        String bestq = qualifier + "Unfiltered";
        
        Map<String, Set<String>> attrvals = recordSet.getAttributeValues();
        int  l = attrvals.keySet().size();
        for(String attribute : attrvals.keySet()){
            if(this.excludedAttributes.contains(attribute))
                continue;
            for(String val : attrvals.get(attribute)){
                Filter f = new Filter() {

                    @Override
                    public boolean filterRecord(Record record) {
                        return record.get(attribute).equals(val);
                    }
                };
                float score = qualityMeasure.getScore(RecordSet.filter(subgroup, f));
                String newq = qualifier + "-" + attribute+"="+val;
                if(depth+1 < this.depthmax && score > iterateThreshhold){
                    Pair<Float,String> r = this.iterate(depth + 1, score, RecordSet.filter(subgroup, f), newq);
                    score = r.getKey();
                    newq = r.getValue();
                }
                if(score > maxscore){
                    maxscore = score;
                    bestq = newq;
                }
                if(depth == 0)
                    System.out.println(Float.toString(score) + ","+ newq+"," + Integer.toString(n) + "/" +Integer.toString(l));
            }
            n++;
        }
        
        return new Pair<Float,String>(maxscore, bestq);
//        }
    }
}
