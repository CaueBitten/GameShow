package frame;

import java.awt.Color;
import java.awt.Font;
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
	private int pontuacao;
	private Nave nave;
	private List<Meteoro> meteoros;
	private Timer timer;
	private boolean gameOver;
	private int[][] coordenadas = 
		{ 	{500, 720}, {250, 0}, {750, 0}, {750, 0}, {1000, 720}, {0, 0},
			{1350, 0}, {1900, 180}, {1700, 360}, {-2400, 360}, {-2800, 360}, {3200, 540}, {-3500, 720},
//			{}, {}, {}, {}, {},
//			{}, {}, {}, {}, {}
		};

	
	
	public Fase(){
		
		gameOver = false;
		
		pontuacao = 0;
		
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
	
	public void removeMeteoros(){
		
		while(meteoros.size() != 0){
			meteoros.remove(0);
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
			
			for(int i = 0; i < meteoros.size(); i++){

				Meteoro m = (Meteoro) meteoros.get(i);
				graficos.drawImage(m.getImagem(), m.posicao.getX(), m.posicao.getY(), this);
				
			}
			
			graficos.setColor(Color.WHITE);
			g.drawString("Pontuacao : " + pontuacao, 10, 10);
			
		}
		
		else{
			
			ImageIcon fimJogo = new ImageIcon("res\\game_over.jpg");
			
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);

			//graficos.setFont(new Font("TimesRoman", Font.PLAIN, 32)); 
			graficos.setColor(Color.WHITE);
			g.drawString("Pontuacao : " + pontuacao, 450, 350);

		}
		if(meteoros.size() == 0){			
			ImageIcon fimJogo = new ImageIcon("res\\deathstar2.gif");

			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			
			//graficos.setFont(new Font("TimesRoman", Font.PLAIN, 32)); 
			graficos.setColor(Color.WHITE);
			g.drawString("Pontuacao : " + pontuacao, 450, 350);
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
				if(m.getTamanho() == 3){
					espalhaMeteoroGrande(m);
				}
				else if(m.getTamanho() == 2){
					espalhaMeteoroMedio(m);
				}
			}
			
		}
		
		nave.deslocar();
		checarColisoes();
		repaint();
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
					tempMeteoro.setDestruido();
					tempMissel.setVisivel(false);
					
					if(!gameOver){
						pontuacao += 50*tempMeteoro.getTamanho();
					}
					
				}
				
			}
			
		}
		
		for(int i = 0; i < meteoros.size(); i++){
			Meteoro m = (Meteoro) meteoros.get(i);
			if(m.posicao.getX() < 0 || m.posicao.getX() > 1000){
				meteoros.remove(i);
			}
			if(m.posicao.getY() < 0 || m.posicao.getY() > 720){
				meteoros.remove(i);
			}
		}
		
	}
	
	private void espalhaMeteoroGrande(Meteoro m) {
		
		int x = m.posicao.getX();
		int y = m.posicao.getY();
		int dx = m.posicao.getDx();
		int dy = m.posicao.getDy();
		
		int dx1, dx2, dy1, dy2;
		
		if(dx == 0){
			dx1 = -1;
			dx2 = 1;
			dy1 = dy;
			dy2 = dy;
		}
		
		else if(dy == 0){
			dx1 = dx;
			dx2 = dx;
			dy1 = -1;
			dy2 = 1;
		}
		
		else if(dx > 0 && dy > 0){
			dx1 = 1;
			dx2 = 0;
			dy1 = 0;
			dy2 = 1;
		}
		
		else if(dx > 0 && dy < 0){
			dx1 = 1;
			dx2 = 0;
			dy1 = 0;
			dy2 = -1;
		}
		
		else if(dx < 0 && dy > 0){
			dx1 = -1;
			dx2 = 0;
			dy1 = 0;
			dy2 = 1;
		}
		
		else{
			dx1 = -1;
			dx2 = 0;
			dy1 = 0;
			dy2 = -1;
		}
		
		meteoros.add(new MeteoroMedio(x, y, dx1, dy1));
		meteoros.add(new MeteoroMedio(x, y, dx2, dy2));

	}

	private void espalhaMeteoroMedio(Meteoro m) {
  
 		int x = m.posicao.getX();
		int y = m.posicao.getY();
		int dx = m.posicao.getDx();
		int dy = m.posicao.getDy();
		
		int dx1, dx2, dy1, dy2;
		
		if(dx == 0){
			dx1 = -1;
			dx2 = 1;
			dy1 = dy;
			dy2 = dy;
		}
		
		else if(dy == 0){
			dx1 = dx;
			dx2 = dx;
			dy1 = -1;
			dy2 = 1;
		}
		
		else if(dx > 0 && dy > 0){
			dx1 = 1;
			dx2 = 0;
			dy1 = 0;
			dy2 = 1;
		}
		
		else if(dx > 0 && dy < 0){
			dx1 = 1;
			dx2 = 0;
			dy1 = 0;
			dy2 = -1;
		}
		
		else if(dx < 0 && dy > 0){
			dx1 = -1;
			dx2 = 0;
			dy1 = 0;
			dy2 = 1;
		}
		
		else{
			dx1 = -1;
			dx2 = 0;
			dy1 = 0;
			dy2 = -1;
		}
		
		meteoros.add(new MeteoroPequeno(x, y, dx1, dy1));
		meteoros.add(new MeteoroPequeno(x, y, dx2, dy2));
	}

	private class TecladoAdapter extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER && (gameOver == true || meteoros.size() == 0)){
				gameOver = false;
				
				if(meteoros.size() != 0){
					pontuacao = 0;				
				}
				
				nave = new Nave();
				removeMeteoros();
				inicializaMeteoros();
			}
			
			
			nave.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e){
			
			nave.keyReleased(e);
			
		}
	}
	
	
}
