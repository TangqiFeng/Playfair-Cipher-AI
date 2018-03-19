package ie.gmit.sw.ai;

/*
 this class used to decrypt PlayFair cipher
 adapt from :
 https://github.com/EmaCorrea/Playfair-Cipher/blob/master/Playfair/src/v3/Playfair_Cipher.java
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
    }

    public Decryptor() {
    }

    public void setKey(char[][] key) {
        this.key = key;
    }

    public void setCipher_text(String cipher_text) {
        this.cipher_text = cipher_text;
    }

    public String decrypt(){
        // used for check pairs for decrypting
        char A, B;
        // flag to check is/not add 'x' to the end manually
        boolean flag = false;
        // check cipher_text is odd/even X
        if(cipher_text.length() % 2 == 0) {
            //System.out.println("even");
            length = cipher_text.length() - 1;
        }
        else {
            //System.out.println("odd");
            length = cipher_text.length();
        }
        // Add 'X'
        // 1) If the plaintext has an odd number of characters, append
        //    the letter X to make it even
        // 2) Parse any double letters, replace the second occurrence
        //    with the letter X
        for(int i = 0; i < length; i += 2) {
            //System.out.println("letter A: " + message.charAt(i));
            A = cipher_text.charAt(i);
            /* when text length is odd, and 'X' already added to the end by manually,
               then, it will contribute to infinite loop.*/
            // check if A = 'X' and is the last one
            if(i == length-2){
                // exit loop if the last char is 'X', which is added manually
                if(flag)
                    break;
            }
            if(i + 1 > length)
                B = 'X';
            else
                //System.out.println("letter : " + message.charAt(i+1));
                B = cipher_text.charAt(i + 1);

            // Add an 'X' character if there are two consecutive equal letters
            if(A == B) {
                B = 'X';

                // When an X is appended, if the string is uneven, add an X at the end
                // so it can be paired
                length += 2;
                if((length) % 2 == 1) {
                    StringBuilder ex = new StringBuilder(cipher_text);
                    ex.append("X");
                    flag = true;
                    cipher_text = ex.toString();
                }
                i-= 1;
            }
            // Substitute 'J' characters for 'I' characters
            if(A == 'J')
                A = 'I';
            if(B == 'J')
                B = 'I';

            plain_text.append(findCharPosition(A, B, key));
        }
        return plain_text.toString();
    }

    // find a b position
    private String findCharPosition(char A, char B, char[][] matrix) {
        int  rowA = 0, rowB = 0, colA = 0, colB = 0;
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
