/**
 * 
 */
package parallelprocessing;

import modelos.aparcamiento;

/*
 *
 * @author Valenciano
 * 15 sept 2026
 */
public class EntranceJob extends Thread {
	
	private aparcamiento parking;
	
	//Initialize the parking object through the constructor
	public EntranceJob(aparcamiento parking, String name) {
		super(name);
		this.parking = parking;
	}

	@Override
	public void run() {
		System.out.println("Start the job with thread " + Thread.currentThread().getName());
		System.out.println(parking + " object address from thread " + Thread.currentThread().getName());
		System.out.println("End the job with thread " + Thread.currentThread().getName());
	}

}
