package ie.gmit.sw.ai;

import java.util.Map;

public class HeuristicCalculator {

    public static Double getHeuristicValue(String plain_text,Map<String,Double> grams){
        Double heuristic = 0.0;
        int i = 0; // used to control move step
        while (i <= plain_text.length() - 4){
            //if (i + 4 > plain_text.length())
            String subString = plain_text.substring(i, i+4);
            // skip not exist grams, for example:'OQOZ'
            if(grams.get(subString) != null){
                heuristic += ProbabilityCalculator.calLogProbability(grams.get(subString));
            }
            i += 4; // change 4, more smaller, more accurate, and need more time!!!
        }
        return  heuristic;
    }
}
