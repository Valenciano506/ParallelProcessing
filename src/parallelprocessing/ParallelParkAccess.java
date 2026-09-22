package parallelprocessing;

import modelos.aparcamiento;
import modelos.empresa;

/*
 * @author Valenciano
 * 11 sept 2026
 */
public class ParallelParkAccess {
	
	public static void main(String[] args) {
		
		aparcamiento parking1 = new aparcamiento("madrid", "Aparcamiento 1", 100);
		aparcamiento parking2 = new aparcamiento("barcelona", "Aparcamiento 2", 200);
		empresa empresaValen = new empresa("Valen");
		empresaValen.registrarAparcamiento(parking1);
		empresaValen.registrarAparcamiento(parking2);
		
		int numOfThreads = 40;
		Thread[] threads = new Thread[numOfThreads];
		for (int i = 0; i < numOfThreads; i++) {
			Thread t = new EntranceJob(parking1, "Justo" + i);
			threads[i] = t;
		}
		Thread t1 = new EntranceJob(parking1, "Justo");
		Thread t2 = new EntranceJob(parking1, "Camicha");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(parking1.getCochesAparcados());
		
	}

}
