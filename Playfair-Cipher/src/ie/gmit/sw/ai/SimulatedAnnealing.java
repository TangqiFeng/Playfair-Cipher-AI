package ie.gmit.sw.ai;

import java.util.Arrays;
import java.util.Map;

public class SimulatedAnnealing {
    private String cipher_text;
    private String plain_text;
    private Map<String,Double> grams;
    Double bestScore;
    char[][] bestKey = new char[5][5];
    public SimulatedAnnealing(String cipher_text, Map<String, Double> grams) {
        this.cipher_text = cipher_text;
        this.grams = grams;
        this.bestScore = Double.NEGATIVE_INFINITY;
    }

    public String getPlain_text() {
        return new Decryptor(bestKey, cipher_text).decrypt();
    }

    public String run(){
        // step 1
        // Generate a random 25 letter key called parent
        char[][] parent = new KeyGenerator().getRandomKey();

        // step 2
        // decrypt the cipher-text with the key
        plain_text = new Decryptor(parent, cipher_text).decrypt();

        // step 3
        // get heuristic value of the key (logProbability)
        Double score = HeuristicCalculator.getHeuristicValue(plain_text, grams);

        // Starting Temperature = 10
        // Final Temperature = 0
        // Temperature Decrement = 1
        int c=0;
        for (int temp = 10; temp > 0; temp--){
            // Iterations at each temperature = 50000
            for (int trainsitions = 60000; trainsitions > 0; trainsitions--){
                // make a small change to the key
                char[][] child = new KeyShuffler(parent).shuffleKey();
                // calculate child heuristic value
                plain_text = new Decryptor(child, cipher_text).decrypt();
                Double h = HeuristicCalculator.getHeuristicValue(plain_text, grams);
                // get delta (h(child) - h(parent))
                Double delta = h - score;
                if (delta > 0){
                    parent = child;
                    score = h;
                }else if (delta < 0){
                    Double p = Math.exp(delta/temp);
                    if (p > 0.5){
                        if (score > bestScore)
                        {
                            bestScore = score;
                            bestKey = parent;
                        }
                        parent = child;
                        score = h;
                        c++;
                    }
                }
            }
            //System.out.println(c); c=0;
            System.out.println("End of temp = "+temp+", score:"+ score);
        }
        // return the key
        return KeyGenerator.convertToString(parent);
    }

}
