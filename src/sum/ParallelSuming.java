package sum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *
 * @author Valenciano
 * 12 nov 2026
 */
public class ParallelSuming {
	
	public static void main(String[] args) {
		
		//Define variables
		ArrayList<Integer> nums = new ArrayList<>(100);
		//int[] array = new int[100];
		List<Integer> nums2 = new ArrayList<>(100);
		
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
	}

}
