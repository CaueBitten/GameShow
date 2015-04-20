package entidades;

public class Canhao {
	
	protected Posicao posicao;
	private double angulo;
	
	public Canhao(int x, int y){
		this.posicao = new Posicao(x, y);
		this.angulo = 0;
	}
	
	public double getAngulo() {
		return angulo;
	}
	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}	
	
	
}
