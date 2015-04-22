package entidades;

public abstract class Meteoro extends ObjetoFisico{

	protected int tamanho;
	private static final int VELOCIDADE = 1;
	
	public Meteoro(int x, int y){
		
		int dx, dy;
		
		dx = x - LIMITE_X/2;
		dy = y - LIMITE_Y/2;
		
		if(dx > 0){
			dx = -1;
		}
		else if(dx < 0){
			dx = 1;
		}
		else{
			dx = 0;
		}
		
		if(dy > 0){
			dy = -1;
		}
		else if(dy < 0){
			dy = 1;
		}
		else{
			dy = 0;
		}
		
		this.posicao.setX(x);
		this.posicao.setY(y);
		
		
		this.posicao.setDx(VELOCIDADE*dx);
		this.posicao.setDy(VELOCIDADE*dy);
		setDestruido(false);
		setVisivel(true);
	}
	
public Meteoro(int x, int y, int dx, int dy){
		
		this.posicao.setX(x);
		this.posicao.setY(y);
		
		
		this.posicao.setDx(VELOCIDADE*dx);
		this.posicao.setDy(VELOCIDADE*dy);
		setDestruido(false);
		setVisivel(true);
	}
	
	public void deslocar(){
		
		this.posicao.setX(this.posicao.getX() + (int)(VELOCIDADE*this.posicao.getDx()));
		this.posicao.setY(this.posicao.getY() + (int)(VELOCIDADE*this.posicao.getDy()));	
		
		
	}
	
	public abstract void explode();
	
	public abstract void divide();
	
	public void setDestruido(){
		explode();
		setDestruido(true);
	}
	
	public int getTamanho(){
		return tamanho;
	}
	
	
}
