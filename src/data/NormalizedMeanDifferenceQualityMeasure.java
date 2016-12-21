package data;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 *
 * @author Sjef
 */
public class NormalizedMeanDifferenceQualityMeasure implements QualityMeasure {

    protected RecordSet originalset;
    protected MeanDifferenceModelClass mc;
    protected Float[] originalsetresults;

    public NormalizedMeanDifferenceQualityMeasure(RecordSet originalset, MeanDifferenceModelClass mc) {
        this.originalset = originalset;
        this.mc = mc;
        this.originalsetresults = mc.fit(this.originalset);
    }

    public void print(Filter f) {
        for (String s : mc.targets) {
            System.out.print(String.format("%20s", s));
        }
        System.out.print('\n');
        for (Float v : originalsetresults) {
            System.out.print(String.format("%20s", v));
        }
        System.out.print('\n');
        this.getScore(f, true);
        System.out.print('\n');
        System.out.print('\n');
    }

    @Override
    public float getScore(Filter filter) {
        return this.getScore(filter, false);
    }

    public float getScore(Filter filter, boolean print) {
        Float[] result = mc.fit(RecordSet.filter(originalset, filter));
        if (print) {
            for (Float v : result) {
                System.out.print(String.format("%20s", v));
            }
        }
        System.out.print('\n');
        float score = 0f;
        for (int i = 0; i < result.length; i++) {
            float v = Math.abs(originalsetresults[i] - result[i]) / originalsetresults[i];
            if (print) {
                System.out.print(String.format("%20s", v));
            }
            score += v;
        }
        return score;
    }

}
