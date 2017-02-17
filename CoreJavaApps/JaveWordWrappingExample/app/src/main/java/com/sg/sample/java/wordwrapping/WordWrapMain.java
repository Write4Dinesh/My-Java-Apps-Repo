package com.sg.sample.java.wordwrapping;

/**
 * Created by dinesh.k.masthaiah on 2/17/2017.
 */

public class WordWrapMain {
    private static final int FIRST_LINE_LENGTH = 26;
    public static void main(String[] args) {
        String original = "This is the First Line \n This is the 2nd line \n This is the 3rd line";
        System.out.println("Hello String!");
        if (original != null && !original.isEmpty() && original.length() > FIRST_LINE_LENGTH) {
            String first = original.substring(0, FIRST_LINE_LENGTH);
            if(first.lastIndexOf(" ")!=-1) {
                first = first.substring(0,first.lastIndexOf(" "));
            }
            String otherLines = original.substring(first.length());
            System.out.println("first Line = " + first.trim());
            System.out.println("otherLines = " + otherLines.trim());
        } else {
            System.out.println("whole string=" + original);
        }
    }
}
