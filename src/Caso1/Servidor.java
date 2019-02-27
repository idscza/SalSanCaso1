package Caso1;

public class Servidor extends Thread {
	private Buffer buffer;
	private int id;
	private Mensaje ms;
	
	public Servidor(Buffer b, int pid) {
		buffer=b;
		id = pid;
	}
	
	@Override
	public void run() {
		
		while(hayClientes()) {
			ms = buffer.retirar();	// retira el objeto en la posicion [primero] del buffer.
			if(ms == null)
				yield();			// si no hay mensaje, sede el procesador.
			else
				responderMS(ms);	// si hay mensaje, lo responde (y despierta a su cliente).
		}
		
		System.out.println("  sale servidor "+id);
	}
	
	/**
	 * 
	 * @param ms no puede ser null.
	 */
	public void responderMS(Mensaje ms) {

		ms.actualizar(id);
		try {
			sleep((long)0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ms.despertar();
	}
	
	public boolean hayClientes() {
		return buffer.darTotalClientes() > 0;
	}
}
