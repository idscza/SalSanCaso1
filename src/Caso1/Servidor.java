package Caso1;

public class Servidor extends Thread {
	private Buffer buffer;
	private int id;
	private Mensaje ms;
	private int t;

	public Servidor(Buffer b, int pid, int seg) {
		buffer=b;
		id = pid;
		t=seg;
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
	}

	/**
	 * 
	 * @param ms no puede ser null.
	 */
	public void responderMS(Mensaje ms) {

		trabajoFalso();
		ms.actualizar(id);
		ms.despertar();
	}

	private void trabajoFalso() {
		try {
			sleep((long)t*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	public boolean hayClientes() {
		return buffer.darTotalClientes() > 0;
	}
}
