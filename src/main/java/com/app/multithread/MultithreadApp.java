package com.app.multithread;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
public class MultithreadApp {

	public static void main(String[] args) {

		System.out.println("===Multithread App Started===");

		List<Eod> eods = MultithreadEod.readCsv();

		for (int x = 1; x <= 2; x++){
			Runnable runnable = new MultithreadEod(x, eods);
			Thread threadNo1 = new Thread(runnable);
			/** No 1 */
			threadNo1.start();
			try {
				threadNo1.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			/** No 2a */
			Thread threadNo2a = new Thread(runnable);
			threadNo2a.start();

			/** No 2b */
			Thread threadNo2b = new Thread(runnable);
			threadNo2b.start();
		}

		/** No 3 */
		ExecutorService executorServiceNo3 =
				Executors.newFixedThreadPool(8);

		for (int y = 1; y <= 8; y++){
			Runnable runnableNo3 = new MultithreadEod(y, eods);
			executorServiceNo3.execute(runnableNo3);
		}

		executorServiceNo3.shutdown();
		while (!executorServiceNo3.isTerminated()){}

		System.out.println("===Multithread App Finished===");
	}
}
