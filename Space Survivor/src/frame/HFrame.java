package frame;

import javax.swing.JFrame;

public class HFrame extends JFrame{
	
	public HFrame(){
		
		add(new Fase());
		setTitle("Space Survivor");
		setSize(1000,720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new HFrame();
	}

}
