package data;

import java.util.Comparator;
import java.util.LinkedList;
import javafx.util.Pair;

public class BeamSearch {
    /**
     * Perform a beamsearch to find filters that are "good" according to the qualitymeasure
     * 
     * @param qm quality measure that tells how good a filter is
     * @param refinementoperator to generate filters
     * @param beamwidth how many filters are remembered and looked at in the next level
     * @param beamdepth how many levels will the search go
     * @param resultsize how many results will the algoritm return
     * @param constraints ?
     * @return Priority queue with resultsize results
     */
    public static MaxSizePriorityQueue<Pair<Float,Filter>> beamSearch(
            QualityMeasure qm,
            FilterGenerator refinementoperator,
            int beamwidth,
            int beamdepth,
            int resultsize,
            Constraints constraints
            ){
        LinkedList<Filter> candidateQueue = new LinkedList<>();
        candidateQueue.add(new Unfiltered());
        MaxSizePriorityQueue<Pair<Float,Filter>> resultSet;
        resultSet = new MaxSizePriorityQueue<>(resultsize, new FloatFilterComparator());
        for(int level = 1;level<=beamdepth;level++){
            MaxSizePriorityQueue<Pair<Float,Filter>> beam = new MaxSizePriorityQueue<>(beamwidth,new FloatFilterComparator());
            
            while(!candidateQueue.isEmpty()){
                Filter seed = candidateQueue.pop();
                Filter[] filtersfromseed = refinementoperator.generate(seed);
                int i =0;
                for(Filter f : filtersfromseed){
                    float q = qm.getScore(f);
                    if(Float.isNaN(q)){
                        System.out.println("Nan for " + String.valueOf(f));
                    } else if(constraints.satisfy(f)){
                        resultSet.add(new Pair<>(q, f));
                        beam.add(new Pair<>(q,f));
                    }
                    System.out.println(resultSet.size() + " Level: " + String.valueOf(level) + " width: " + String.valueOf(candidateQueue.size()) + " - " + String.valueOf(i++) + "/" + String.valueOf(filtersfromseed.length));
                }
            }
            while(!beam.isEmpty()){
                candidateQueue.add(beam.pollLast().getValue());
            }
        }
        return resultSet;
    }
}

class FloatFilterComparator implements Comparator<Pair<Float, Filter>>{
    @Override
    public int compare(Pair<Float, Filter> o1, Pair<Float, Filter> o2) {
        float dkey = o1.getKey() - o2.getKey();
        if(dkey < 0){
            return -1;
        } else {
            return 1;
        }
    }
}
