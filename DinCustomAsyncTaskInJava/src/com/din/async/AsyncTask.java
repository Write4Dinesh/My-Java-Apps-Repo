package com.din.async;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AsyncTask<Request, Result, Progress> {
	public boolean isBackGroundThreadWorking = true;
	private Result mResult;
	private BlockingQueue<Integer> mProgressQueue = null;
	private static int MAX_PROGRESS = 100;

	public AsyncTask() {
		mProgressQueue = new ArrayBlockingQueue<Integer>(MAX_PROGRESS);
	}

	public Result doInBackground(Request request) {
		isBackGroundThreadWorking = true;
		String threadName = Thread.currentThread().getName();
		System.out.println("Executing in Background Thread:" + threadName);
		for (int i = 0; i < MAX_PROGRESS; i++) {
			mProgressQueue.add(i);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		isBackGroundThreadWorking = false;
		System.out.println("Execution completed by Background Thread:" + threadName);
		mResult = (Result) "Out put data";
		return mResult;
	}

	public void onPostExecute(Result result) {
		String threadName = Thread.currentThread().getName();
		System.out.println("Task Completed: Got the result=" + result + " in Main Thread:" + threadName);
	}

	public void execute(Request request) {
		BackGroundThread<Request, Result> bThread = new BackGroundThread<Request, Result>(this, request);
		bThread.start();
		try {
			// bThread.join();
			while (isBackGroundThreadWorking | !mProgressQueue.isEmpty()) {
				Progress progress = (Progress) mProgressQueue.take();
				onProgress(progress);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		onPostExecute((Result) bThread.getResult());
	}

	public void onProgress(Progress progress) {
		String threadName = Thread.currentThread().getName();
		System.out.println("Thread:" + threadName + ": is updating:" + progress + "% complete");
		try {
			Runtime.getRuntime().exec("clear");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
