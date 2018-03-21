package ie.gmit.sw.ai;

import java.util.Arrays;
import java.util.Map;

public class SimulatedAnnealing {
    private String cipher_text;
    private String plain_text;
    private Map<String,Double> grams;

    public SimulatedAnnealing(String cipher_text, Map<String, Double> grams) {
        this.cipher_text = cipher_text;
        this.grams = grams;
    }

    public String getPlain_text() {
        return plain_text;
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
        for (int temp = 50; temp >= 0; temp--){
            // Iterations at each temperature = 50000
            for (int trainsitions = 15000; trainsitions >= 0; trainsitions--){
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
                    if (p > Math.random()){
                        parent = child;
                        score = h;
                    }
                }
            }
            System.out.println("End of temp = "+temp+", key:"+ Arrays.deepToString(parent));
        }
        // return the key
        return KeyGenerator.convertToString(parent);
    }

}
