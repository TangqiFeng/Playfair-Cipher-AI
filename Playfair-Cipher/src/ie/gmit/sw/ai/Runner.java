package ie.gmit.sw.ai;

import java.util.Map;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println("playdair-cipher SA");
        Scanner cin = new Scanner(System.in);
        System.out.println("please type the path of cipher_text file:");
        String cipherPath = cin.nextLine();
        String cipher_text = FileHandler.readFile(cipherPath);
        System.out.println("please type the path of file '4grams.txt':");
        String gramPath = cin.nextLine();
        ProbabilityCalculator cal = new ProbabilityCalculator(gramPath);
        Map<String,Double> grams = cal.get4Grams();
        SimulatedAnnealing SA = new SimulatedAnnealing(cipher_text,grams);
        System.out.println("key: "+ SA.run());
        FileHandler.writeFile("plain_text",SA.getPlain_text());
        System.out.println("plain_text.txt is generated. (current path)");



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
