
package com.ahmed.m.hassaan.cryptograph.data.model;

import android.util.Log;

import java.util.LinkedHashSet;
import java.util.Set;

public class PlayFair {

    final char key[][];


    public PlayFair(String s) {
        key = this.fillKey(removeDuplicates(s));
    }

    private char[][] fillKey(String keyword) {
        char key[][] = new char[5][5];
        int n = keyword.length();
        int k = 0, alpha = 65;
        for (int i = 1; i <= 5; ) {
            for (int j = 1; j <= 5; ) {
                if (k < n) {
                    key[i - 1][j - 1] = keyword.charAt(k);
                    System.out.print(key[i - 1][j - 1] + " ");
                    j++;
                    k++;
                } else {
                    if (keyword.indexOf((char) alpha) == -1) {
                        key[i - 1][j - 1] = (char) alpha;
                        System.out.print(key[i - 1][j - 1] + " ");
                        j++;
                        if ((char) alpha == 'I') {
                            alpha++;
                        }
                    }
                    alpha++;
                }

            }
            i++;
            System.out.println();
        }
        return key;
    }

    private static String removeDuplicates(String string) {

        char[] chars = string.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    public String decode(String cipher) {

        StringBuilder res = new StringBuilder();
        int n = cipher.length();
        int i = 0;
        while (i < n) {
            if (i != n - 1) {
                res.append(findMatchInKeyDecode(cipher.substring(i, i + 2)));
            }
            i += 2;
        }
        return res.toString();

    }

    public String encode(String plaintext) {
        StringBuilder res = new StringBuilder();
        int n = plaintext.length();
        int i = 0;
        while (i < n) {

            if (i != n - 1) {
                res.append(findMatchInKeyEncode(plaintext.substring(i, i + 2)));
            } else {
                res.append(findMatchInKeyEncode(plaintext.charAt(i) + "X"));
            }
            i += 2;
        }
        return res.toString();
    }

    private String findMatchInKeyDecode(String input) {
        StringBuilder s = new StringBuilder();
        int found = 0;
        char a = input.charAt(0);
        char b = input.charAt(1);
        int p[] = new int[2], q[] = new int[2];
        label:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == a) {
                    p[0] = i;
                    p[1] = j;
                    found++;
                } else if (key[i][j] == b) {
                    q[0] = i;
                    q[1] = j;
                    found++;
                }
                if (found == 2) {
                    break label;
                }
            }
        }
        if (p[0] == q[0]) {
            s.append(key[p[0]][(p[1] + 4) % 5]);
            s.append(key[q[0]][(q[1] + 4) % 5]);

        } else if (p[1] == q[1]) {
            s.append(key[(p[0] + 4) % 5][p[1]]);
            s.append(key[(q[0] + 4) % 5][q[1]]);
        } else {
            s.append(key[p[0]][q[1]]);
            s.append(key[q[0]][p[1]]);

        }
        return s.toString();
    }

    private String findMatchInKeyEncode(String input) {
        StringBuilder s = new StringBuilder();
        int found = 0;
        char a = input.charAt(0);
        char b = input.charAt(1);
        int p[] = new int[2], q[] = new int[2];
        label:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == a) {
                    p[0] = i;
                    p[1] = j;
                    found++;
                } else if (key[i][j] == b) {
                    q[0] = i;
                    q[1] = j;
                    found++;
                }
                if (found == 2) {
                    break label;
                }
            }
        }
        if (p[0] == q[0]) {
            s.append(key[p[0]][(p[1] + 1) % 5]);
            s.append(key[q[0]][(q[1] + 1) % 5]);

        } else if (p[1] == q[1]) {
            s.append(key[(p[0] + 1) % 5][p[1]]);
            s.append(key[(q[0] + 1) % 5][q[1]]);
        } else {
            s.append(key[p[0]][q[1]]);
            s.append(key[q[0]][p[1]]);

        }
        return s.toString();
    }


}
/*

    String key;
    String plainText;
    String encrypt = "";
    String alpha = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
    String unique = "";
    char[][] matrix = new char[5][5];
    int k = 0;


    public PlayFair(String key, String plaintext) {
        //Removing spaces and replace 'I' with 'J
        this.key = key.replaceAll("\\s", "").replace("I", "J");

        this.plainText = plaintext.replaceAll("\\s", "").replace("I", "J");
    }

    public String encrypt() {

        //Getting all unique characters
        for (int i = 0; i < key.length(); i++) {
            if (!unique.contains("" + key.charAt(i))) {
                unique = "".concat(unique).concat(String.valueOf(key.charAt(i)));
            }
        }

        Log.d("TAG_PlayFair", "encrypt: Uniqe is  "+unique);

        //Creating matrix of unique chars

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = unique.charAt(k);
                k++;
            }
        }

        //Printing matrix
//        System.out.println("Matrix");
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println("");
//        }

        //Check msg for double occurences and add 'X' in between
        for (int i = 0; i < plainText.length(); i += 2) {
            if (plainText.charAt(i) == plainText.charAt((i + 1) % plainText.length())) {
                plainText = plainText.substring(0, i + 1) + "X" + plainText.substring(i + 1, plainText.length());
            }
            //System.out.println("Separated multiple occurences = "+plainText);
        }

        //If length is odd append 'X' at end
        if (plainText.length() % 2 != 0) {
            plainText += "X";
            //System.out.println("Appending X to plainText = "+plainText);
        }
        System.out.println("Separating occurences(if there) = " + plainText);

        //Encryption Code
        if (plainText.length() % 2 == 0) {
            for (int i = 0; i < plainText.length(); i += 2) {
                String pos = findPos(matrix, plainText.charAt(i), plainText.charAt(i + 1));
                if (pos.charAt(0) == pos.charAt(2)) {
                    //for same row
                    int r = Integer.parseInt(pos.charAt(0) + "");
                    int c = (Integer.parseInt(pos.charAt(1) + "") + 1) % 5;
                    int c1 = (Integer.parseInt(pos.charAt(3) + "") + 1) % 5;
                    encrypt = encrypt + matrix[r][c] + matrix[r][c1];
                } else if (pos.charAt(1) == pos.charAt(3)) {
                    //for same col
                    int r = (Integer.parseInt(pos.charAt(0) + "") + 1) % 5;
                    int r1 = (Integer.parseInt(pos.charAt(2) + "") + 1) % 5;
                    int c = Integer.parseInt(pos.charAt(1) + "");
                    encrypt = encrypt.concat(String.valueOf(matrix[r][c])).concat(String.valueOf(matrix[r1][c]));
                } else {
                    //for diff row and col
                    int r = Integer.parseInt(pos.charAt(0) + "");
                    int c = Integer.parseInt(pos.charAt(3) + "");
                    int r1 = Integer.parseInt(pos.charAt(2) + "");
                    int c1 = Integer.parseInt(pos.charAt(1) + "");
                    encrypt = encrypt + matrix[r][c] + matrix[r1][c1];
                }
                // System.out.println(pos);
                // System.out.println(plainText);
            }
            System.out.println("Encrypted plainText = " + encrypt);
        }
        return encrypt;
    }

    //Function to find the position of 2 chars
    public static String findPos(char arr[][], char ch, char ch1) {
        String chPos = "";
        String ch1Pos = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == ch) {
                    chPos = chPos + i + j;
                } else if (arr[i][j] == ch1) {
                    ch1Pos = ch1Pos + i + j;
                }
            }
        }
        return chPos + ch1Pos;
    }
}*/
