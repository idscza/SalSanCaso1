package Caso1;

public class Buffer {

	private Mensaje[] buffer;
	private int totalClientes;	// cantidad Clientes que se estan ejecutando.
	private int cupo; 	// se comportara como semaforo para clientes.
	private int top = 0;		// clientes siempre agregan al indice top.
	private int primero = 0;	// servidores siempre atienden al indice primero.

	public Buffer (int tamanio, int numClientes) {
		buffer = new Mensaje[tamanio];		
		cupo = tamanio;			// el semaforo permite entrar tantos clientes como "canales" tenga el buffer.
		totalClientes = numClientes;
	}
	/**
	 * 
	 * @param ms : mensaje que sera agregado al tope del buffer.
	 */
	public void almacenarMs (Mensaje ms) {

		if(buffer[top]==null){
			buffer[top]=ms;					// agrega el mensaje en el tope del buffer.
			top=(top+1)%buffer.length;		// actualiza el tope a la siguiente posicion.
			ms.duerme();					// se duerme hasta que sea atendido.	
		}
		else
			System.out.println("=================indice top sobre casilla ocupada================= MAAAL!! ==================================");
	}


	/**
	 * Es Synchronized para evitar inconsistencias al acceder y actualizar las variables compartidas (buffer y primero).
	 * @return mensaje que sigue en la fila. null si no hay mensajes.
	 */
	public synchronized Mensaje retirar() {

		Mensaje ms = buffer[primero];

		if(ms != null) {
			buffer[primero]=null;
			primero=(primero+1)%buffer.length;
			vC();							// termina la consulta. Norifica si hay clientes en cola.
		}
		
		return ms;
	}
	public synchronized void pC() {
		cupo--;
		if(cupo < 0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public synchronized void vC() {
		cupo++;
		if(cupo <= 0)
			notify();
	}

	/**
	 * Es synchronized ya que manipula variable compartida (totalClientes).
	 */
	public synchronized void registrarSalida(String sCliente) {
		totalClientes--;
		System.out.println("Sale "+ sCliente + " quedan "+totalClientes+" clientes.		top: "+ top+ "primero: "+primero+imprimir());

	}

	private String imprimir() {
		String imp = "\n";
		for (int i = 0; i < buffer.length; i++) {
			imp+="| "+(buffer[i]==null?0:1)+" |";
		}
		return imp;
	}
	/**
	 * 
	 * @return cantidad de clientes en ejecucion.
	 */
	public int darTotalClientes () {
		return totalClientes;
	}

	public synchronized void reajusteIndice(){
		System.out.println("ajustar indice necesario");
		for (int i = 0; i < buffer.length+1; i++) {
			if(buffer[primero]==null)
				primero=(primero+1)%buffer.length;
		}
	}

}
