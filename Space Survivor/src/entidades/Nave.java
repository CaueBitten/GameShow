package entidades;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave extends ObjetoFisico {

	private double rotacao;
	private double inclinacao;
	private List<Missel> misseis;
	
	
	public Nave(){
		ImageIcon referencia = new ImageIcon("res\\nave.gif");
		
		setImagem(referencia.getImage());
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		this.posicao.setX(LIMITE_X/2 - getLargura()/2);
		this.posicao.setY(LIMITE_Y/2 - getAltura()/2);
		
		misseis = new ArrayList<Missel>();
		
	}

	public List<Missel> getMisseis() {
		return misseis;
	}
	
	public void atira(){
		this.misseis.add(new Missel(this.posicao.getX(), this.posicao.getY(), this.rotacao));
	}
	
	@Override
	public void deslocar() {
		
		posicao.setX(posicao.getX() + posicao.getDx()); 
		posicao.setY(posicao.getY() + posicao.getDy());; 
		
		if(this.posicao.getX() < 1){
			posicao.setX(1);
		}
		
		if(this.posicao.getX() > (LIMITE_X - getLargura() - 5)){
			posicao.setX(LIMITE_X - getLargura() - 5);
		}
		
		if(this.posicao.getY() < 1){
			posicao.setY(1);
		}

		if(this.posicao.getY() > LIMITE_Y - 2*getAltura()){
			posicao.setY(LIMITE_Y - 2*getAltura());
		}
		
		rotacao += inclinacao;
		
	}

	public double getRotacao() {
		return rotacao;
	}	

	public void keyPressed(KeyEvent tecla) {
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			posicao.setDy(-1);
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			posicao.setDy(1);
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			posicao.setDx(-1);
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			posicao.setDx(1);
		}
		
		if(codigo == KeyEvent.VK_A){
			inclinacao = -1;
		}
		
		if(codigo == KeyEvent.VK_D){
			inclinacao = 1;
		}
		
		if(codigo == KeyEvent.VK_SPACE){
			atira();
		}
		
	}

	public void keyReleased(KeyEvent tecla) {
	
	int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			posicao.setDy(0);
		}
 
		if(codigo == KeyEvent.VK_DOWN){
			posicao.setDy(0);
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			posicao.setDx(0);
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			posicao.setDx(0);
		}
		
		if(codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_D){
			inclinacao = 0;
		}
		
	}


}
