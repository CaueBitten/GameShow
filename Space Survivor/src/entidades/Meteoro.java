package entidades;

public abstract class Meteoro extends ObjetoFisico{

	protected int tamanho;
	private static final double VELOCIDADE = 0.5;
	
	public Meteoro(int x, int y){
		
		this.posicao.setDx((int)(x/100));
		this.posicao.setDy((int)(y/100));
		setDestruido(false);
		setVisivel(true);
	}
	
	public void deslocar(){
		
		this.posicao.setX(this.posicao.getX() + (int)(VELOCIDADE*this.posicao.getDx()));
		this.posicao.setY(this.posicao.getY() + (int)(VELOCIDADE*this.posicao.getDy()));	
		
		
	}
	
	public abstract void explode();
	
	public int getTamanho(){
		return tamanho;
	}
	
	
}
