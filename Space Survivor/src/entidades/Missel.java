package entidades;

import javax.swing.ImageIcon;

public class Missel extends ObjetoFisico {

	private static final int VELOCIDADE = 4;
	private double anguloLancamento;
	
	public Missel(int x, int y, double angle){
		
		this.posicao.setX(x);
		this.posicao.setY(y);
		
		this.anguloLancamento = angle;
		this.anguloLancamento = Math.toRadians(this.anguloLancamento);
		
		ImageIcon referencia = new ImageIcon("res\\missel.png"); 
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		setDestruido(false);
		setVisivel(true);
		
	}

	@Override
	public void deslocar() {
		
		this.posicao.setDx((int) (VELOCIDADE*Math.sin(anguloLancamento)));
		this.posicao.setDy((int) -(VELOCIDADE*Math.cos(anguloLancamento)));
		
		this.posicao.setX(this.posicao.getX() + this.posicao.getDx());
		this.posicao.setY(this.posicao.getY() + this.posicao.getDy());
		
		if(this.posicao.getX() < 0 || this.posicao.getX() > LIMITE_X || this.posicao.getY() < 0 || this.posicao.getY() > LIMITE_Y ) {
			setVisivel(false);
		}
		
	}
	
	
}
