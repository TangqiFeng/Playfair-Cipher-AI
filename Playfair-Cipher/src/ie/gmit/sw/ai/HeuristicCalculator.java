package ie.gmit.sw.ai;

import java.util.Map;

public class HeuristicCalculator {
    // The 4-grams and the count of their occurrence
    // were computed by sampling a set of text documents
    // containing an aggregate total of 4,224,127,912 4-grams
    private static final Double total = 4224127912.0;

    // step is used to control move step
    // e.g. text = HAPPYDAYS
    // step=1 => HAPP APPY PPYD ... ...
    // step=2 => HAPP PPYD YNAY ... ...
    // more smaller, more accurate, and need more time!!!
    private static int step = 2;

    public static Double getHeuristicValue(String plain_text,Map<String,Double> grams){
        Double heuristic = 0.0;
        for (int i=0;i<plain_text.length()-3;i += step){
            //if (i + 4 > plain_text.length())
            String subString = plain_text.substring(i, i+4);
            // skip not exist grams, for example:'OQOZ'
            if(grams.containsKey(subString)){
                Double value = grams.get(subString);
                heuristic += Math.log10(value)/Math.log10(total);
            }else {
                heuristic += 0;
            }
        }
        return  heuristic;
    }
}
