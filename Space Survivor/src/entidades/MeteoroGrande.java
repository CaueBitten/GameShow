package entidades;

import javax.swing.ImageIcon;

public class MeteoroGrande extends Meteoro {

	public MeteoroGrande(int x, int y){

		super(x, y);
	
		ImageIcon referencia = new ImageIcon("res\\inimigo_1.gif");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 3;
	
	}
	
	public void explode(){
		
	}
}
