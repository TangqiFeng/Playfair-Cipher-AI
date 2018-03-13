package ie.gmit.sw.ai;

import java.util.Scanner;

/*
 resource from :
 https://github.com/EmaCorrea/Playfair-Cipher/blob/master/Playfair/src/v3/Playfair_Cipher.java

 PlayFair encrypt & decrypt
 */
public class Test {

    static char cipher [][] = new char[5][5];
    static String message, decipherS;
    static StringBuilder cipherTxt = new StringBuilder();
    static StringBuilder plainTxt = new StringBuilder();
    static int index = 0, alphIndex = 0, length;

    public static void main(String[] args) {

        System.out.println("*****Playfair Cipher*****");
        System.out.println();

        System.out.print("Please write the key: ");
        Scanner kb = new Scanner (System.in);
        String key = kb.nextLine().toUpperCase();
        //key = removeDuplicates(key).replaceAll("\\s+","");

        System.out.print("\nPlease write the message: ");
        message = kb.nextLine().toUpperCase();

        // Remueve espacios en blanco en el string
        message = message.replaceAll("\\s+","");

        // Remueve las J
        key = key.replaceAll("J", " ");
        key = key.replaceAll("\\s", "");

        // Añade la llave y el alfabeto a la matriz
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(index < key.length()) {
                    cipher[i][j] = key.charAt(index);
                    index++;
                }
            }
        }

        printCipher();

        pairByPair(message);

        System.out.println("\nThe plain text is: " + plainTxt);

        kb.close();
    }

    //***************************************************//

//    // remove dublicates
//    public static String removeDuplicates(String s) {
//        StringBuilder noDupes = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            String si = s.substring(i, i + 1);
//            if (noDupes.indexOf(si) == -1)
//                noDupes.append(si);
//        }
//        return noDupes.toString();
//    }

    // Imprime el cipher en consola
    public static void printCipher() {
        System.out.println();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(cipher[i][j] + " ");
            }
            System.out.println();
        }
    }


    //***************************************************//

    // Envia cada par de letras consecutivas a decifrar o cifrar
    public static void pairByPair(String message) {
        char A, B;

        //
        if(message.length() % 2 == 0) {
            //System.out.println("even");
            length = message.length() - 1;
        }
        else {
            //System.out.println("odd");
            length = message.length();
        }


        //System.out.println(message);
        for(int i = 0; i < length; i += 2) {
            //System.out.println("letter A: " + message.charAt(i));
            A = message.charAt(i);
            if(i + 1 > length)
                B = 'X';
            else
                //System.out.println("letter : " + message.charAt(i+1));
                B = message.charAt(i + 1);

            // Agrega un caracter 'X' si hay dos letras iguales consecutivas
            if(A == B) {
                B = 'X';

                // When an X is appended, if the string is uneven, add an X at the end
                // so it can be paired
                length += 2;
                if((length) % 2 == 1) {
                    StringBuilder ex = new StringBuilder(message);
                    ex.append("X");
                    message = ex.toString();
                }
                i-= 1;
            }
            // Sustituye los caracteres de 'J' por caracteres de 'I'
            if(A == 'J')
                A = 'I';
            if(B == 'J')
                B = 'I';

            plainTxt.append(findCharPosition(A, B, cipher));
        }
    }

    //***************************************************//
    // find a b position
    public static String findCharPosition(char A, char B, char[][] matrix) {
        int msgIndex = 0, rowA = 0, rowB = 0, colA = 0, colB = 0;
        while(msgIndex < length) {
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
            msgIndex++;
        }
        decipherS = decrypt(rowA, colA, rowB, colB, matrix);
        return decipherS;
    }

    //***************************************************//
    // Decrypt
    public static String decrypt(int rowA, int colA, int rowB, int colB, char[][] matrix) {
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

