package entidades;

import java.util.List;

import javax.swing.ImageIcon;

public class MeteoroPequeno extends Meteoro {

	public MeteoroPequeno(int x, int y){
		
		super(x, y);
		
		ImageIcon referencia = new ImageIcon("res\\asteroide_pequeno.png");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 1;
	
	}
	
	public MeteoroPequeno(int x, int y, int dx, int dy){
		
		super(x, y, dx, dy);
		
		ImageIcon referencia = new ImageIcon("res\\asteroide_pequeno.png");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 1;
	
	}
	
	public void explode(){
		ImageIcon referencia = new ImageIcon("res\\explosao_2.gif");
		setImagem(referencia.getImage());
	
//		setAltura(0);
//		setLargura(0);
		
	}

	@Override
	public int getTamanho() {
		return tamanho;
	}
	
	
}
