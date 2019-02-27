package Caso1;

public class Main {

	public static void main(String[] args) {

//		int tamanioBuffer = 10;
//		int msPorCliente = 10;
//		int segundosXservidor = 1;
//		int cantidadClientes =500;
//		int cantidadServidores = 1000;
		int tamanioBuffer = 3;
		int msPorCliente = 50;
		int segundosXservidor = 0;
		int cantidadClientes =50;
		int cantidadServidores = 50;

		Buffer buf = new Buffer(tamanioBuffer, cantidadClientes);

		for (int i = 1; i <= cantidadClientes; i++) 
			(new Cliente(msPorCliente, buf)).start();

		for (int i = 1; i <= cantidadServidores; i++) {
			(new Servidor(buf, i, segundosXservidor)).start();
		}

	}

}
