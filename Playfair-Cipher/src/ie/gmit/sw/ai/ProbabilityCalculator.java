package ie.gmit.sw.ai;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProbabilityCalculator {
    // the path of file "4grams.txt"
    private String path;
    // The 4-grams and the count of their occurrence
    // were computed by sampling a set of text documents
    // containing an aggregate total of 4,224,127,912 4-grams
    private final Double total = 4224127912.0;

    public ProbabilityCalculator(String path) {
        this.path = path;
    }

    public Map<String,Double> get4Grams() {
        // used to stord all 4grams
        Map<String, Double> grams = new HashMap<String, Double>();
        try {
            File file = new File(path);
            // check file is exist
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.equals("")) {
                        String[] s = lineTxt.split("\\ +");
                        grams.put(s[0], Double.parseDouble(s[1]));
                    }
                }
                read.close(); //close InputStreamReader
                bufferedReader.close();// close BufferedReader
            }else {
                System.out.println("file is not exist.");
            }
        }catch (Exception e) {
            System.out.println("fail to read 4grams file.");
            e.printStackTrace();
        }
        return grams;
    }

    public Double calProbability(Double count){
        // formula:  P(q) = count(q) / n
        return count/total;
    }

    public Double calLogProbability(Double count){
        // formula:  log10(P(q)) = log10(count(q) / n)
        return Math.log10(count/total);
    }
}
