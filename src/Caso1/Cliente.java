package Caso1;

public class Cliente extends Thread{
	private int porEnviar;
	private Buffer buffer;
	
	public Cliente (int n, Buffer b) {
		porEnviar=n;
		buffer=b;
	}
	
	@Override
	public void run() {
		
		while( porEnviar > 0){
			buffer.pC();
			buffer.almacenarMs(new Mensaje("     Cliente: "+this.getName()+"   |    PorEnviar:"+porEnviar--));
		}
			
		
		buffer.registrarSalida(this.getName());
	}
}
