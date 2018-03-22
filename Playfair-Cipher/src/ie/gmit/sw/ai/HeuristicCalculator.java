package ie.gmit.sw.ai;

import java.util.Map;

public class HeuristicCalculator {
    // The 4-grams and the count of their occurrence
    // were computed by sampling a set of text documents
    // containing an aggregate total of 4,224,127,912 4-grams
    private static final Double total = 4224127912.0;

    public static Double getHeuristicValue(String plain_text,Map<String,Double> grams){
        Double heuristic = 0.0;
        // i used to control move step
        // change 1, more smaller, more accurate, and need more time!!!
        for (int i=0;i<plain_text.length()-3;i += 1){
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
