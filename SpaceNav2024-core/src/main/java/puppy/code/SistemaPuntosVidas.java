package puppy.code;

//Patron de diseño singleton para vidas, puntaje, etc.

public class SistemaPuntosVidas {
	private static SistemaPuntosVidas instanciaPuntos;
	
	private int vidas;
	private int puntos;
	private int ronda;
	private int record;
	
	private SistemaPuntosVidas() {
		reiniciarJuego();
	}
	
	public static SistemaPuntosVidas getInstance() {
		if (instanciaPuntos == null) {
			instanciaPuntos = new SistemaPuntosVidas();
		}
		return instanciaPuntos;
	}
	
	public void agregarPuntos(int puntosObt) {
		puntos += puntosObt;
		if (puntos > record) {
			record = puntos;
		}
	}
	
	public void restarVida() {
		if (vidas > 0) {
			vidas--;
		}
	}
	
	public boolean juegoTerminado() {
		return vidas <= 0;
	}
	
	public void avanzarRonda() {
		ronda++;;
		vidas++;
	}
	
	public void reiniciarJuego() {
        vidas = 3;
        puntos = 0;
        ronda = 1;
    }
	
	public int getVidas() {
		return vidas;
	}
	
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public int getRonda() {
		return ronda;
	}
	
	public void setRonda(int ronda) {
        this.ronda = ronda;
    }
	
    public int getRecord() {
        return record;
    }
    
    public void setRecord(int record) {
        this.record = record;
    }
}
