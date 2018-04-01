# Playfair-Cipher-AI

> author: [Tangqi Feng](https://tangqifeng.github.io/)

[Using Simulated Annealing to Break a Playfair Cipher](https://github.com/TangqiFeng/Playfair-Cipher-AI/blob/img/aiAssignment2018.pdf).

> Module: Artificial Intelligence / 4th Year  
> Lecturer: Dr. John.Healy

A menu-driven command line UI that enables a cipher-text source to be specified (a
file or URL) and an output destination file for decrypted plain-text.

Decrypt cipher-text with a simulated annealing algorithm that uses a log-probability
and n-gram statistics as a heuristic evaluation function. 

sample: 

cipher_text: HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZTFIRCEOMIYOZAEAMKIUZNQHTWDUOBVUDUPNOIEHEQKDLYWXNWILAZDYYOFTWAGADTVUDXXIEKITLKGKSIUYYOYETWDUOCHEFWHEKOABOKHUIMAREMWNFWFWIUNTTIOIOZAZTWFBRCHEXMTYDYHETIDTYOZRMAXWIUOITZMYDHBXHKUMSKXTDYMNXIMRNKZMXIHEXMHDTOOEKYSBQUKDOCHEZATCDEEKVUMXFCISGUYRFWRCOEEQUNZIIAKMVMQHEQMNXIWMKDKYWFIMTIQRUYTZDHPESMVUMRNKTIEXDKMSDQKSIWVOXIITRVQHEXDKPXOITWTHALNIHNOICZYOUDDUPNSMSUYOVNMXXIAURMKYYOPKSIDHHQVDIXMBYKPNOICZXMRAEQPKTZCIAZDYMXUAYOVMMIAXWVQYGXXITZUAYOPKMTUAHE (500 characters)

max_temp: 10, min_temp:0, step:1, transitions:55000 [can be changing in SimulatedAnnealing.java]

moving step: 2 [can be changing in HeuristicCalculator.java ]

![running](https://github.com/TangqiFeng/Playfair-Cipher-AI/blob/img/hobbit.gif)

## How this repository designed

This repository includes:

* CipherBreaker.java => Runner class, contains a simple menu-driven command line UI.

* FileHandler.java => Load cipher text and check it is/not correct. Because the encrypting rules of playdair, the cipher text should have even number length, do not cotain char 'J', do not end with 'X' and for pair (cipher[2n] != cipher[2n+1]). Therefore, this class also check these when load cipher text. || write method write plain text to a .txt file.

* Decryptor.java => playfair cypher decrypting scripts. store the key to a 2-d char array and follow the rules.

* KeyGenerator.java => generation of a random 25-letter key

* HeuristicCalculator.java => using n-Gram Statistics as a Heuristic Function. log() probability is used here.

* GramLoader.java => The resource quadgrams.txt is a text file containing a total of 389,373 4-grams, from a
maximum possible number of 26^4 = 456,976. The 4-grams and the count of their occurrence were computed by sampling a set of text documents containing an aggregate total of 4,224,127,912 4-grams. This class load these grams into a map<String,Double>

* KeyShuffler.java => this class make a small change to the key.

* SimulatedAnnealing.java => the main algorithm - Simulated Annealing, training to get the best key, and decrypting the cipher.

The UML Diagram:
![uml](https://github.com/TangqiFeng/Playfair-Cipher-AI/blob/img/playfairUML.png)

### Note: the key is generated randomly at the beginning, the best result cannot be guaranteed. different max/min temperature, temp steps, transitions and the move steps when calculating the Heuristic Value may contribute different result!

## How to run?

* 1.Import project src code into your favorite IDEA (Eclipse, Intellij etc.)
  
  * do not forget to add Tomcat server !
  * make sure you have the resource file: 4grams.txt
  * prepare a .txt file store the cipher text
  
  2.Run the CipherBreaker.java => follow the instructions...
  
* 1.open command-line (CMD), go to the directory where playfair.jar is.

  2.use java to run the project, typing ''' $ java –cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker '''
  
  3.follow the instructions...
  
  * make sure you have the resource file: 4grams.txt
  * prepare a .txt file store the cipher text
  
### Note: playfair.jar does not cotain the source file - 4grams.txt, you need prepare it and typing the right path manually when the project running.
