package entidades;

import java.util.List;

import javax.swing.ImageIcon;

public class MeteoroGrande extends Meteoro {

	public MeteoroGrande(int x, int y){

		super(x, y);
	
		ImageIcon referencia = new ImageIcon("res\\asteroide_grande.png");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 3;
	
	}
	
	
	public void explode(){
		ImageIcon referencia = new ImageIcon("res\\explosao_1.png");
		setImagem(referencia.getImage());
		
//		setAltura(0);
//		setLargura(0);
	}
	
	
	@Override
	public int getTamanho() {
		return tamanho;
	}
	
}
