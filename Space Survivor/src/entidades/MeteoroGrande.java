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
		
		setAltura(0);
		setLargura(0);
	}
	
	public void divide(List<Meteoro> meteoros){
		meteoros.add(new MeteoroMedio(posicao.getX(), posicao.getY()));
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getX());
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getY());
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getDx());
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getDy());
		meteoros.add(new MeteoroMedio(posicao.getX(), posicao.getY()));
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getX());
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getY());
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getDx());
//		System.out.println(meteoros.get(meteoros.size() - 1).posicao.getDy());
	}
	
	@Override
	public int getTamanho() {
		return tamanho;
	}
	
}
