package ie.gmit.sw.ai;

/*
 this class used to decrypt PlayFair cipher
 adapt from :
 https://github.com/EmaCorrea/Playfair-Cipher/blob/master/Playfair/src/v3/Playfair_Cipher.java

 this decryptor stores a 5*5 2d-array for the key.
 When starting decrypt, need loop each col and row to find such pair letter's position
 */
public class Decryptor {
    private char[][] key;
    private String cipher_text;
    private StringBuilder plain_text = new StringBuilder();
    // used to loop pairs of plain_text
    private int length;

    public Decryptor(char[][] key, String cipher_text) {
        this.key = key;
        this.cipher_text = cipher_text;
        this.length=cipher_text.length()-1;
    }

    public Decryptor() {
    }

    public void setKey(char[][] key) {
        this.key = key;
    }

    public void setCipher_text(String cipher_text) {
        this.cipher_text = cipher_text;
    }

    /*
     For decrypting, there are no chance exit scenario:
     1) for pair (cipher[2n] == cipher[2n+1])
     2) the length of cipher_text is odd
     3) cipher_text exists 'J'
     4) cipher_text is not end with 'X'
     */
    public String decrypt(){
        // used for check pairs for decrypting
        char A, B;
        for(int i = 0; i < length; i += 2) {
            A = cipher_text.charAt(i);
            B = cipher_text.charAt(i + 1);
            if(A == B) {
                B = 'X';
            }
            plain_text.append(findCharPosition(A, B, key));
        }
        return plain_text.toString();
    }

    // find a b position
    private String findCharPosition(char A, char B, char[][] matrix) {
        int rowA = 0, rowB = 0, colA = 0, colB = 0;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(matrix[i][j] == A) {
                    rowA = i;
                    colA = j; }
                if(matrix[i][j] == B) {
                    rowB = i;
                    colB = j; }
            }
        }
        return decrypt(rowA, colA, rowB, colB, matrix);
    }

    // Decrypt
    private String decrypt(int rowA, int colA, int rowB, int colB, char[][] matrix) {
        char s1, s2;
        StringBuilder pair = new StringBuilder();
        if(rowA == rowB){
            s1 = matrix[rowA][(colA + 4) % 5];
            s2 = matrix[rowB][(colB + 4) % 5];
            pair.append(s1).append(s2);
            return pair.toString();
        }
        else if(colA == colB) {
            s1 = matrix[(rowA + 4) % 5][colA];
            s2 = matrix[(rowB + 4) % 5][colB];
            pair.append(s1).append(s2);
            return pair.toString();
        }
        else if(rowA != rowB && colA != colB) {
            s1 = matrix[rowA][colB];
            s2 = matrix[rowB][colA];
            pair.append(s1).append(s2);
        }
        return pair.toString();
    }

}
