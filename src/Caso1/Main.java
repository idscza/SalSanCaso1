package Caso1;

public class Main {

	public static void main(String[] args) {
		
		
		int tamanioBuffer = 5;
		int msPorCliente = 50;
		
		int cantidadClientes =50;
		int cantidadServidores = 50;
		
		Buffer buf = new Buffer(tamanioBuffer, cantidadClientes);
		
		for (int i = 1; i <= cantidadClientes; i++) 
			(new Cliente(msPorCliente, buf)).start();
		
		for (int i = 1; i <= cantidadServidores; i++) {
			(new Servidor(buf, i)).start();
		}
		
	}

}
