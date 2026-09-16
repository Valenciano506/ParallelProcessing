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
	public EntranceJob(aparcamiento parking) {
		this.parking = parking;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.out.println(parking);
	}

}
