package ie.gmit.sw.ai;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    /*
     For decrypting, there are no chance exit scenario:
     1) for pair (cipher[2n] == cipher[2n+1])
     2) the length of cipher_text is odd
     3) cipher_text exists 'J'
     */
    public static String readFile(String path){
        String text = null;
        try {
            File file = new File(path);
            // check file is exist
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                StringBuffer sb = new StringBuffer();
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.equals("")) {
                        sb.append(lineTxt.toUpperCase());
                    }
                }
                text = sb.toString();
                read.close(); //close InputStreamReader
                bufferedReader.close();// close BufferedReader
            }else {
                System.out.println("file is not exist.");
                System.exit(0);
            }
        }catch (Exception e) {
            System.out.println("fail to read file.");
            e.printStackTrace();
        }
        if (text.length() % 2 !=0){
            System.out.println("the length of cipher_text should be even !");
            System.exit(0);
        }
        if (text.contains("J")){
            System.out.println("cipher_text should not contains char 'J' !");
            System.exit(0);
        }
        return text;
    }

    public static void writeFile(String path, String content){
        try {
            Files.write(Paths.get(path), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
