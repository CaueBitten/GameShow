package entidades;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class ObjetoFisico {

	public Posicao posicao;
	private int altura;
	private int largura;
	private boolean isDestruido; 
	private boolean isVisivel;

	private Image imagem;
	
	public ObjetoFisico(){
		posicao = new Posicao();	
	}
	
	//Posso fazer isso? Declarar constantes aqui? E a velocidade fica aqui ou nos filhos?
	protected static final int LIMITE_X = 1000;
	protected static final int LIMITE_Y = 720;
	//protected static final int VELOCIDADE;
	
	
	abstract public void deslocar();

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public boolean isDestruido() {
		return isDestruido;
	}

	public void setDestruido(boolean isDestruido) {
		this.isDestruido = isDestruido;
		setVisivel(!isDestruido);
	}

	public boolean isVisivel() {
		return isVisivel;
	}
	
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(posicao.getX(), posicao.getY(), getLargura(), getAltura());
	}
	
}
