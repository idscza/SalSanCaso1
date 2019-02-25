package Caso1;

public class Mensaje {
	
	private String contenido;	
	private long tCreado;
	private long tAtendido;
	
	public Mensaje(String ms) {
		contenido = ms;
		tCreado = System.currentTimeMillis();
	}
	
	public void actualizar(int n) {
		tAtendido = System.currentTimeMillis();
		contenido+="     |    Atendido por: "+n+"     |      Espero en total: "+((tAtendido-tCreado))+"ms";
		System.out.println(contenido);
	}

	public synchronized void duerme() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void despertar() {
		notify();
	}

	public String darcontenido() {
		return contenido;
	}
}
