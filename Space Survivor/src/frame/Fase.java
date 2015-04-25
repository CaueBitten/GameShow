package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import entidades.Meteoro;
import entidades.MeteoroGrande;
import entidades.MeteoroMedio;
import entidades.MeteoroPequeno;
import entidades.Missel;
import entidades.Nave;

public class Fase extends JPanel implements ActionListener{

	private Image fundo;
	private Nave nave;
	private List<Meteoro> meteoros;
	private Timer timer;
	private boolean gameOver;
	private int[][] coordenadas = 
		{ 	{360, 1200}, {360, -800}, {360, 1500}, {360, -2600}, {360, -2200}, {360, 3000}, {360, 3350},
			{1350, 360}, {1900, 360}, {1700, 360}, {-2400, 360}, {-2800, 360}, {3200, 360}, {-3500, 360},
			{2380, 1000}, {1279, 4353}, {1022, 3247}, {3648, 1332}, {1321, 3123}, {2131, 1324}, {1323, 1323},
			{-2121, -3300}, {-2131, 3212}, {1002, -1292}, {1002, -2234}, {-1213, 2001}, {2001, -2001}
//			{}, {}, {}, {}, {},
//			{}, {}, {}, {}, {}
		};

	
	
	public Fase(){
		
		gameOver = false;
		
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());
		
		
		ImageIcon referencia = 	new ImageIcon("res\\fundo.png");
		fundo = referencia.getImage();
		
		nave = new Nave();
		
		inicializaMeteoros();
		
		timer = new Timer(5, this);
		timer.start();
		
	}
	
	public void inicializaMeteoros(){
		
		meteoros = new ArrayList<Meteoro>();
		
		for(int i = 0; i < coordenadas.length; i++){
			if(i%3 == 0){
				meteoros.add(new MeteoroPequeno(coordenadas[i][0], coordenadas[i][1]));
			}
			else if(i%3 == 1){
				meteoros.add(new MeteoroMedio(coordenadas[i][0], coordenadas[i][1]));
			}
			else{
				meteoros.add(new MeteoroGrande(coordenadas[i][0], coordenadas[i][1]));
			}
		}
		
	}
	
	public void paint(Graphics g){
		
		Graphics2D graficos = (Graphics2D) g;
	
		graficos.drawImage(fundo, 0, 0, null);
		
		if(!gameOver){
			
			Graphics2D g2 = (Graphics2D) graficos.create();
			g2.translate(nave.posicao.getX() + nave.getLargura()/2, nave.posicao.getY() + nave.getAltura()/2);
			g2.rotate(nave.getRotacao()*(Math.PI/180));
			g2.translate(-(nave.posicao.getX() + nave.getLargura()/2), -(nave.posicao.getY() + nave.getAltura()/2));
			g2.drawImage(nave.getImagem(), nave.posicao.getX(), nave.posicao.getY(), this);
			
			g2.dispose();
						
			List<Missel> misseis = nave.getMisseis();
			
			for(int i = 0; i < misseis.size(); i++){
				
				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.posicao.getX(), m.posicao.getY(), this);
				
			}
			
			System.out.println("NUMERO DE METEOROS: " + meteoros.size());
			for(int i = 0; i < meteoros.size(); i++){

				Meteoro m = (Meteoro) meteoros.get(i);
				graficos.drawImage(m.getImagem(), m.posicao.getX(), m.posicao.getY(), this);
				
			}
			
			graficos.setColor(Color.WHITE);
			
		}
		
		else{
			
			ImageIcon fimJogo = new ImageIcon("res\\game_over.jpg");
			
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			
		}
	
		
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		List<Missel> misseis = nave.getMisseis();
		
		for(int i = 0; i < misseis.size(); i++){
			
			Missel m = (Missel) misseis.get(i);
			
			if(m.isVisivel()){
				m.deslocar();
			}
			else{	
				misseis.remove(i);
			}
			
		}
		
		for(int i = 0; i < meteoros.size(); i++){
			
			Meteoro m = (Meteoro) meteoros.get(i);
			
			if(!m.isDestruido()){
				m.deslocar();
			}
			else{
				meteoros.remove(i);
			}
			
		}
		
		nave.deslocar();
		repaint();
		checarColisoes();
	}

	public void checarColisoes(){
		
		Rectangle formaNave = nave.getBounds();
		Rectangle formaMeteoro;
		Rectangle formaMissel;
		
		for(int i = 0; i < meteoros.size(); i++){
			
			Meteoro tempMeteoro = (Meteoro) meteoros.get(i);
			formaMeteoro = tempMeteoro.getBounds();
			
			if(formaNave.intersects(formaMeteoro)){
				
				nave.setDestruido(true);
				nave.setVisivel(false);
				tempMeteoro.setVisivel(false);
				
				gameOver = true;
				
			}
			
		}
		
		List<Missel> misseis = nave.getMisseis();
		                     
		for(int i = 0; i < misseis.size(); i++){
			
			Missel tempMissel = (Missel) misseis.get(i);
			formaMissel = tempMissel.getBounds();
			
			for(int j = 0; j < meteoros.size(); j++){
				
				Meteoro tempMeteoro = (Meteoro) meteoros.get(j);
				formaMeteoro = tempMeteoro.getBounds();
				
				if(formaMissel.intersects(formaMeteoro)){
					System.out.println("ACERTOU! PIU! PIU! UHU!");
					System.out.println("LISTA DE METEOROS ANTES:" + meteoros.size());
					tempMeteoro.divide(meteoros);
					tempMeteoro.setDestruido();
					System.out.println("LISTA DE METEOROS DEPOIS:" + meteoros.size());
					tempMissel.setVisivel(false);
					
//					if(tempMeteoro.getTamanho() == 3){
//						System.out.println("CRIOU OS METEOROS");
//						meteoros.add(new MeteoroMedio(tempMeteoro.posicao.getX() + tempMeteoro.getLargura(), tempMeteoro.posicao.getY() + tempMeteoro.getAltura(), tempMeteoro.posicao.getDx(), tempMeteoro.posicao.getDy()));
//						meteoros.add(new MeteoroMedio(tempMeteoro.posicao.getX() - tempMeteoro.getLargura(), tempMeteoro.posicao.getY() - tempMeteoro.getAltura(), tempMeteoro.posicao.getDx(), - tempMeteoro.posicao.getDy()));
//					}
//					if(tempMeteoro.getTamanho() == 2){
//						meteoros.add(new MeteoroPequeno(tempMeteoro.posicao.getX() + tempMeteoro.getLargura(), tempMeteoro.posicao.getY() + tempMeteoro.getAltura(), tempMeteoro.posicao.getDx(), tempMeteoro.posicao.getDy()));
//						meteoros.add(new MeteoroPequeno(tempMeteoro.posicao.getX() - tempMeteoro.getLargura(), tempMeteoro.posicao.getY() - tempMeteoro.getAltura(), tempMeteoro.posicao.getDx(), - tempMeteoro.posicao.getDy()));
//					}
					
					
				}
				
			}
			
		}
		
//		for(int i = 0; i < meteoros.size(); i++){
//			Meteoro m = (Meteoro) meteoros.get(i);
//			if(m.posicao.getX() < -10 || m.posicao.getX() > 1010){
//				meteoros.remove(i);
//			}
//			if(m.posicao.getY() < -10 || m.posicao.getY() > 730){
//				meteoros.remove(i);
//			}
//		}
		
	}
	
	private class TecladoAdapter extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER && gameOver == true){
				gameOver = false;
				nave = new Nave();
				inicializaMeteoros();
			}
			
			
			nave.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e){
			
			nave.keyReleased(e);
			
		}
	}
	
	
}
