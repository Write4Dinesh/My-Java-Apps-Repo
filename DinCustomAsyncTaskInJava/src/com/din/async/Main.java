package com.din.async;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		BufferedInputStream bis = new BufferedInputStream(System.in);
		try {
			// ascii of Enter key = 10
			// ascii of 'q' is = 113
			System.out.println("Enter q (ascii=113) to quit, any other key to continue");
			while (bis.read() != 113) {
				AsyncTask<String, String, Integer> task = new AsyncTask<String, String, Integer>();
				task.execute("Input data");
				System.out.println("Completed Execution");
				System.out.println("Enter q (ascii=113) to quit, any other key to continue");
			}
			System.out.println("Quitting Program");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (Exception exception) {
			}
		}
	}
}
