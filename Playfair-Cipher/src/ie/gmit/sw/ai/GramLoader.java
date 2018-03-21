package ie.gmit.sw.ai;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GramLoader {
    // the path of file "4grams.txt"
    private String path;

    public GramLoader(String path) {
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
                System.exit(0);
            }
        }catch (Exception e) {
            System.out.println("fail to read 4grams file.");
            e.printStackTrace();
        }
        return grams;
    }
}
