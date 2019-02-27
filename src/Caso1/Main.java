package Caso1;

public class Main {

	public static void main(String[] args) {
		
		
		int tamanioBuffer = 20;
		int msPorCliente = 5;
		
		int cantidadClientes =10;
		int cantidadServidores = 5;
		
		Buffer buf = new Buffer(tamanioBuffer, cantidadClientes);
		
		for (int i = 1; i <= cantidadClientes; i++) 
			(new Cliente(msPorCliente, buf)).start();
		
		for (int i = 1; i <= cantidadServidores; i++) {
			(new Servidor(buf, i)).start();
		}
		
	}

}
