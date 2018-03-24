package ie.gmit.sw.ai;

import java.util.Arrays;
import java.util.Map;

public class SimulatedAnnealing {
    // attributes fot SA
    private float temperature = 10;
    private float step = 1;
    private float finalTemperature = 0;
    private float trainsition = 55000;

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

        // main loop
        for (float temp = temperature; temp > finalTemperature; temp-=step){
            // Iterations at each temperature
            for (float trainsitions = trainsition; trainsitions > 0; trainsitions--){
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
                    }
                }
            }
            System.out.println("End of temp = "+temp+", score:"+ score);
        }
        // return the key
        return KeyGenerator.convertToString(parent);
    }

}
