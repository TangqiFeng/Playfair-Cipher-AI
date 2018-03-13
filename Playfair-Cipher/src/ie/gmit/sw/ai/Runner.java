package ie.gmit.sw.ai;

public class Runner {
    public static void main(String[] args) {
        Decryptor de = new Decryptor();
        char[][] cipher = new char[5][5];
        // Add the key and the alphabet to the matrix
        String key = "THEQUICKBROWNFXMPDVLAZYGS";
        String cipher_text = "HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZTFIRCEOMIYOZAEAMKIUZNQHTWDUOBVUDUPNOIEHEQKDLYWXNWILAZDYYOFTWAGADTVUDXXIEKITLKGKSIUYYOYETWDUOCHEFWHEKOABOKHUIMAREMWNFWFWIUNTTIOIOZAZTWFBRC";
        int index = 0;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(index < key.length()) {
                    cipher[i][j] = key.charAt(index);
                    index++;
                }
            }
        }
        de.setKey(cipher);
        de.setCipher_text(cipher_text);
        System.out.println(de.decrypt());
    }
}
