package entidades;

public class Posicao {

	private int x;
	private int y;
	private int dx;
	private int dy;

	public Posicao(){
		this.x = 0;
		this.y = 0;
	}
	
	public Posicao(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	

	public int getY() {
		return y;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
}
