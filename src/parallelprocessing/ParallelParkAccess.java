package parallelprocessing;

import modelos.aparcamiento;
import modelos.empresa;

/*
 * @author Valenciano
 * 11 sept 2026
 */
public class ParallelParkAccess {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		aparcamiento parking1 = new aparcamiento("madrid", "Aparcamiento 1", 100);
		aparcamiento parking2 = new aparcamiento("barcelona", "Aparcamiento 2", 200);
		empresa empresaValen = new empresa("Valen");
		empresaValen.registrarAparcamiento(parking1);
		empresaValen.registrarAparcamiento(parking2);
		
	}

}
