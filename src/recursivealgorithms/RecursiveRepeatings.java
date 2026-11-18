package recursivealgorithms;

/*
 *
 * @author Valenciano
 * 18 nov 2026
 */
public class RecursiveRepeatings {
	
	public static void main(String[] args) {
		
		//First method of doing a repetitive job
		System.out.println("Repeat job");
		System.out.println("Repeat job");
		System.out.println("Repeat job");
		System.out.println("Repeat job");
		
		//Second method
		for (int i = 0; i < 5; i++) {
			System.out.println("Repeat job " + i);
		}
		
		//Third way of doing the same
		repeatJob(1);
		
	}

	private static void repeatJob(int i) {
		
		System.out.println("Repeat job" + i);
		
	}

}
