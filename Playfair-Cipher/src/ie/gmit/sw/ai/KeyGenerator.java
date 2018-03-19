package ie.gmit.sw.ai;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class KeyGenerator {
    // 25 alphabet array, without 'J'
    private char[] alphabet = {'A','B','C','D','E','F','G','H','I','K','L',
            'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    // generation of a random 25-letter key
    private void shuffle() {
        int index;
        Random random = ThreadLocalRandom.current();
        for (int i = alphabet.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                alphabet[index] ^= alphabet[i];
                alphabet[i] ^= alphabet[index];
                alphabet[index] ^= alphabet[i];
            }
        }
    }

    public char[][] getRandomKey(){
        shuffle();
        return get2dArrayKey();
    }

    // change key to 2d-array
    private char[][] get2dArrayKey(){
        char[][] newKey = new char[5][5];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                newKey[i][j] = alphabet[i * 5 + j];
            }
        }
        return newKey;
    }

    // 2-d array convert to String
    public static String convertToString(char[][] key){
        return Arrays.toString(key).replaceAll("\\[|\\]|,|\\s", "");
    }
}
