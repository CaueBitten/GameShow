package entidades;

import javax.swing.ImageIcon;

public class MeteoroMedio extends Meteoro {

	public MeteoroMedio(int x, int y){
		
		super(x, y);
	
		ImageIcon referencia = new ImageIcon("res\\asterioide_medio.png");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 2;
	
	}
	
public MeteoroMedio(int x, int y, int dx, int dy){
		
		super(x, y, dx, dy);
	
		ImageIcon referencia = new ImageIcon("res\\asterioide_medio.png");
		setImagem(referencia.getImage());
		
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		
		tamanho = 2;
	
	}
	
	public void explode(){
		ImageIcon referencia = new ImageIcon("res\\explosao_2.gif");
		setImagem(referencia.getImage());
		
		setAltura(0);
		setLargura(0);
		
	}
	
	public void divide(){
		tamanho--;
	}
	
}
