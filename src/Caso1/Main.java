package Caso1;

public class Main {

	public static void main(String[] args) {

		int tamanioBuffer = 0;
		int msPorCliente = 0;
		int segundosXservidor = 0;
		int cantidadClientes =0;
		int cantidadServidores = 0;		
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("500.txt")));
			tamanioBuffer = Integer.parseInt(in.readLine().split(":")[1]);
			cantidadServidores = Integer.parseInt(in.readLine().split(":")[1]);
			segundosXservidor = Integer.parseInt(in.readLine().split(":")[1]);
			cantidadClientes = Integer.parseInt(in.readLine().split(":")[1]);
			msPorCliente = Integer.parseInt(in.readLine().split(":")[1]);
			System.out.println("|"+tamanioBuffer+"|"+cantidadServidores+"|"+segundosXservidor+"|"+cantidadClientes+"|"+msPorCliente);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Buffer buf = new Buffer(tamanioBuffer, cantidadClientes);

		for (int i = 1; i <= cantidadClientes; i++) 
			(new Cliente(msPorCliente, buf)).start();

		for (int i = 1; i <= cantidadServidores; i++) {
			(new Servidor(buf, i, segundosXservidor)).start();
		}

	}

}
