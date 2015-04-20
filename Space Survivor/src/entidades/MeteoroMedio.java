package entidades;

import javax.swing.ImageIcon;

public class MeteoroMedio extends Meteoro {

	public MeteoroMedio(int x, int y){
		
		super(x, y);
	
		ImageIcon referencia = new ImageIcon("res\\inimigo_1.gif");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 2;
	
	}
	
	public void explode(){
		
	}
}
