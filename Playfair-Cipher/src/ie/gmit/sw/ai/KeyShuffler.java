package ie.gmit.sw.ai;

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
        this.key = key;
    }

    public KeyShuffler() {
    }

    public void setKey(char[][] key) {
        this.key = key;
    }

    public char[][] shuffleKey(){
        Double random = Math.random()*100;
        if(random < 90){

        }else if (random <= 91){

        }else if (random <= 93){

        }else if (random <= 95){

        }else if (random <= 97){

        }else if (random <= 99){

        }
        return key;
    }

    public char[][] SwapSingleLetter(){
        int a = (int) (Math.random()*5);
        int b = (int) (Math.random()*5);
        int c = (int) (Math.random()*5);
        int d = (int) (Math.random()*5);
        char temp = key[a][b];
        key[a][b] = key[c][d];
        key[c][d] = temp;
        return  key;
    }
}
