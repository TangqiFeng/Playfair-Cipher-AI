package ie.gmit.sw.ai;

import java.util.Random;

/*
 change the key
• Swap single letters (90%)
• Swap random rows (2%)
• Swap columns (2%)
• Flip all rows (2%)
• Flip all columns (2%)
• Reverse the whole key (2%)
 */
public class KeyShuffler {
    private char[][] key = new char[5][5];

    public KeyShuffler(char[][] key) {
        for (int i = 0; i < 5; i++){
            System.arraycopy(key[i],0,this.key[i],0,5);
        }
    }

    public KeyShuffler() {
    }

    public void setKey(char[][] key) {
        this.key = key;
    }

    public char[][] shuffleKey(){
        int random = (int) (Math.random()*50);
        if(random > 4){
            return swapSingleLetter();
        }else if (random == 0){
            return swapRandomRows();
        }else if (random == 1){
            return  swapRandomCols();
        }else if (random == 2){
            return filpAllRows();
        }else if (random == 3){
            return filpAllCols();
        }else if (random == 4){
            return reverseWholeKey();
        }
        return key;
    }

    // generate 4 random number, range [0,4]
    // swap key[a][b] <=> key[c][d]
    private char[][] swapSingleLetter(){
        int a = (int) (Math.random()*5);
        int b = (int) (Math.random()*5);
        int c = (int) (Math.random()*5);
        int d = (int) (Math.random()*5);
        char[][] newKey = new char[5][5];
        System.arraycopy(key,0,newKey,0,key.length);
        char temp = newKey[a][b];
        newKey[a][b] = newKey[c][d];
        newKey[c][d] = temp;
        return  newKey;
    }

    // generate 2 random number, range [0,4]
    // swap row a <=> row b
    public char[][] swapRandomRows(){
        int a = (int) (Math.random()*5);
        int b = (int) (Math.random()*5);
        char[] temp = key[a];
        key[a] = key[b];
        key[b] = temp;
        return key;
    }

    // generate 2 random number, range [0,4]
    // swap col a <=> col b
    private char[][] swapRandomCols(){
        int a = (int) (Math.random()*5);
        int b = (int) (Math.random()*5);
        for (int i = 0; i < 5; i++){
            char temp = key[i][a];
            key[i][a] = key[i][b];
            key[i][b] = temp;
        }
        return key;
    }

    // flip all rows
    private char[][] filpAllRows(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 2; j++){
                char temp = key[i][j];
                key[i][j] = key[i][4 - j];
                key[i][4 - j] = temp;
            }
        }
        return key;
    }

    // flip all cols
    private char[][] filpAllCols(){
        for (int i = 0; i < 2; i++){
            char[] temp = key[i];
            key[i] = key[4-i];
            key[4-i] = temp;
        }
        return key;
    }

    // Reverse the whole key
    private char[][] reverseWholeKey(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 5; j++){
                char temp = key[i][j];
                key[i][j] = key[4 - i][4 - j];
                key[4 - i][4 - j] = temp;
            }
            char temp = key[2][i];
            key[2][i] = key[2][4 - i];
            key[2][4 - i] = temp;
        }
        return key;
    }

}
