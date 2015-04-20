package entidades;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave extends ObjetoFisico {

	public Canhao canhao;
	private double rotacao;
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
			
	}

	public double getRotacao() {
		return rotacao;
	}	

	public void keyPressed(KeyEvent tecla) {
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_A){
			this.rotacao -= 5;
		}
		
		if(codigo == KeyEvent.VK_D){
			this.rotacao += 5;
		}
		
		if(codigo == KeyEvent.VK_SPACE){
			atira();
		}
		
	}


}
