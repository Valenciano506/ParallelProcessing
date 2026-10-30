package sum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *
 * @author Valenciano
 * 29 oct 2026
 */
public class ParallelSuming {
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		Runnable task = () -> {
			System.out.println("Thread name is: " + Thread.currentThread().getName());
		};
		
		executor.submit(task);
		Thread thread;
		
	}

}
