package ie.gmit.sw.ai;

import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        String cipher_text = "HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZTFIRCEOMIYOZAEAMKIUZNQHTWDUOBVUDUPNOIEHEQKDLYWXNWILAZDYYOFTWAGADTVUDXXIEKITLKGKSIUYYOYETWDUOCHEFWHEKOABOKHUIMAREMWNFWFWIUNTTIOIOZAZTWFBRC";
        ProbabilityCalculator cal = new ProbabilityCalculator("src/4grams.txt");
        Map<String,Double> grams = cal.get4Grams();
        System.out.println(new SimulatedAnnealing(cipher_text,grams).run());


//        // For testing Decryptor
//        char[][] cipher = new char[5][5];
//        // Add the key and the alphabet to the matrix
//        String key = "THEQUICKBROWNFXMPDVLAZYGS";
//        int index = 0;
//        for(int i = 0; i < 5; i++) {
//            for(int j = 0; j < 5; j++) {
//                if(index < key.length()) {
//                    cipher[i][j] = key.charAt(index);
//                    index++;
//                }
//            }
//        }
//        System.out.println(
//                new Decryptor(cipher,"SIIOOBKCSMKOHQSLBAKDKH”")
//                        .decrypt());

//        // for testing HeuristicCalculator & ProbabilityCalculator
//        ProbabilityCalculator cal = new ProbabilityCalculator("src/4grams.txt");
//        Map<String,Double> grams = cal.get4Grams();
//        System.out.println(HeuristicCalculator.getHeuristicValue("HAPPYDAYS",grams));
    }
}
