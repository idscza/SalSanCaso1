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
		boolean entro;
		while( porEnviar > 0){
			buffer.pC();
			Mensaje ms = new Mensaje("     Cliente: "+this.getName()+"   |    PorEnviar:"+porEnviar--);
			entro = buffer.almacenarMs(ms);
			while(!entro){
				buffer.vC();
				buffer.pC();
				entro=buffer.almacenarMs(ms);
			}
		}
			
		
		buffer.registrarSalida(this.getName());
	}
}
