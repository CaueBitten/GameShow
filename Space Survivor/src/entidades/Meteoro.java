package entidades;

import java.util.List;

public abstract class Meteoro extends ObjetoFisico{

	protected int tamanho;
	protected static final int VELOCIDADE = 1;
	
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
		if(posicao.getDx() > 0){
			if(0 >= posicao.getX() || posicao.getX() >= LIMITE_X){
				posicao.setX(0);
			}
		}
		
		else if(posicao.getDx() < 0){
			if(0 >= posicao.getX() || posicao.getX() >= LIMITE_X){
				posicao.setX(LIMITE_X);
			}
		}
		
		else{
			if(0 > posicao.getY() || posicao.getX() > LIMITE_Y){
				posicao.setY(LIMITE_Y/2);
			}
		}
		
		if(posicao.getDy() > 0){
			if(0 >= posicao.getY() || posicao.getX() >= LIMITE_Y){
				posicao.setY(0);
			}			
		}
		
		else if(posicao.getDy() < 0){
			if(0 >= posicao.getY() || posicao.getX() >= LIMITE_Y){
				posicao.setY(LIMITE_Y);
			}
		}
		
		else{
			if(0 > posicao.getX() || posicao.getX() > LIMITE_X){
				posicao.setX(LIMITE_X/2);
			}
		}
		
		this.posicao.setX(this.posicao.getX() + (int)(VELOCIDADE*this.posicao.getDx()));
		this.posicao.setY(this.posicao.getY() + (int)(VELOCIDADE*this.posicao.getDy()));	
		
		
	}
	
	public abstract void explode();
		
	public void setDestruido(){
		explode();
		setDestruido(true);
	}
	
	public abstract int getTamanho();
	
	
}
