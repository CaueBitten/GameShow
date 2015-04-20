package entidades;

import javax.swing.ImageIcon;

public class MeteoroPequeno extends Meteoro {

	public MeteoroPequeno(int x, int y){
		
		super(x, y);
		
		ImageIcon referencia = new ImageIcon("res\\inimigo_1.gif");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 1;
	
	}
	
	public void explode(){
		
	}
	
}
