package com.sg.sample.java.wordwrapping;

import java.util.ArrayList;

/**
 * Created by dinesh.k.masthaiah on 2/17/2017.
 */

public class WordWrapMain {
    private static final int FIRST_LINE_LENGTH = 26;

    public static void main(String[] args) {
        String original = "This is the First Line This is the 2nd line  \nThis is the 3rd line";
        ArrayList<String> lines = splitLines(original, FIRST_LINE_LENGTH);
        for (String line : lines) {
            System.out.println(line);
        }

    }

    public static ArrayList<String> splitLines(String original, int firstLineLength) {
        ArrayList<String> lines = new ArrayList<>(2);
        if (original != null && !original.isEmpty() && original.length() > firstLineLength) {
            String first = original.substring(0, firstLineLength);
            if (first.lastIndexOf(" ") != -1) {
                first = first.substring(0, first.lastIndexOf(" "));
            }
            String otherLines = original.substring(first.length());
            lines.add(first.trim());
            lines.add(otherLines.trim());
        } else {
            lines.add(original);
        }
        return lines;
    }
}
