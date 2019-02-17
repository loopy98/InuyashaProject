import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

class Slot extends JLabel{

	public Slot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Slot(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public Slot(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	public Slot(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public Slot(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public Slot(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}


	
	
}

class pos {
	int x;
	int y;
	
	int index;
	
	pos(int x, int y){
		this.x = x;
		this.y = y;
		
		this.index = 4*x+y;
	}
	
	public void move(int dx,int dy) {
		x+=dx;
		y+=dy;
		if(x<0) x=0;
		if(x>2) x=2;
		if(y<0) y=0;
		if(y>3) y=3;
		index = 4*x+y;
	}
}

class MyFrame extends JFrame implements KeyListener,MouseListener{
	
	static pos position1 = new pos(1,0);
	static pos position2 = new pos(1,3);
	
	JPanel mainPanel = new JPanel();
	JPanel textPanel = new JPanel();
	
	JPanel statusPanel=new JPanel();
	JPanel boardPanel=new JPanel();
	
	JPanel statusPanel1 = new JPanel();
	JPanel statusPanel2 = new JPanel();
	
	JPanel statusImage1 = new JPanel();
	JPanel statusImage2 = new JPanel();
	
	ImageIcon heart = new ImageIcon(getClass().getResource("heart.png"));
	ImageIcon diamond = new ImageIcon(getClass().getResource("diamond.png"));
	
	JLabel health1 = new JLabel("Ã¼·Â",JLabel.CENTER);
	JLabel health2 = new JLabel("Ã¼·Â",JLabel.CENTER);
	JLabel mana1 = new JLabel("Çàµ¿·Â",JLabel.CENTER);
	JLabel mana2 = new JLabel("Çàµ¿·Â",JLabel.CENTER);
	
	JPanel heart1 = new JPanel();
	JPanel heart2 = new JPanel();
	JPanel diamond1 = new JPanel();
	JPanel diamond2 = new JPanel();
	
	JLabel round = new JLabel("Round \n 1",JLabel.CENTER);
	
	BufferedImage image1 = new BufferedImage(180,180,BufferedImage.TYPE_3BYTE_BGR);
	BufferedImage image2 = new BufferedImage(180,180,BufferedImage.TYPE_3BYTE_BGR);
	
	JLabel imageLabel1 = new JLabel(new ImageIcon(image1));
	JLabel imageLabel2 = new JLabel(new ImageIcon(image2));
	
	JTextArea txt = new JTextArea();
	
	static Slot[] slot = new Slot[12];
	
	MyFrame(){
		super("Let's Go! NUPJOOK!");
		setSize(1200,960);
		setLocation(50,50);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		
		//background
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				ImageIcon bgImage = new ImageIcon(getClass().getResource("backgroundImage_1.png"));
				g.drawImage(bgImage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		//statusPanel
		
		statusPanel.setBounds(5,5,1190,180);
		statusPanel.setBackground(new Color(0,0,0,0));
		
		statusPanel1.setLayout(new BorderLayout());
		statusPanel1.setBackground(new Color(0,0,0,0));
		statusPanel1.add(health1,BorderLayout.NORTH);
		statusPanel1.add(mana1,BorderLayout.SOUTH);
		
		health1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå",Font.PLAIN,30));
		health1.setOpaque(true);
		health1.setBackground(new Color(201,201,201));
		mana1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå",Font.PLAIN,30));
		mana1.setOpaque(true);
		mana1.setBackground(new Color(201,201,201));
		
		statusPanel2.setLayout(new BorderLayout());
		statusPanel2.setBackground(new Color(0,0,0,0));
		statusPanel2.add(health2,BorderLayout.NORTH);
		statusPanel2.add(mana2,BorderLayout.SOUTH);
		
		health2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå",Font.PLAIN,30));
		health2.setOpaque(true);
		health2.setBackground(new Color(201,201,201));
		mana2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå",Font.PLAIN,30));
		mana2.setOpaque(true);
		mana2.setBackground(new Color(201,201,201));
		
		imageLabel1.setBounds(5,5,180,180);
		imageLabel2.setBounds(1000,5,180,180);
		statusPanel1.setBounds(193,5,120,120);
		statusPanel2.setBounds(1200-325,5,120,120);

		round.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå",Font.PLAIN,20));
		round.setBounds(550,5,100,120);
		
		//boardPanel
		boardPanel.setLayout(new GridLayout(3,4,5,5));
		boardPanel.setBounds(5, 360,1150, 360);
		boardPanel.setOpaque(false);
		boardPanel.addMouseListener(this);
		
		for(int i=0;i<12;i++) {
			slot[i] = new Slot(""+i,JLabel.CENTER);
			slot[i].setOpaque(true);
			slot[i].setBorder(new LineBorder(Color.BLACK,5,false));
			slot[i].setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå",Font.PLAIN,30));
			boardPanel.add(slot[i]);
		}

		recolor();
		
		//textField
		txt.setBounds(5,730,1150,200);
		txt.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå",Font.PLAIN,30));
		txt.setText("asdf");
		txt.setFocusable(false);
		
		//arrangement
		background.setLayout(null);
		background.add(imageLabel1);
		background.add(imageLabel2);
		background.add(statusPanel1);
		background.add(statusPanel2);
		background.add(round);
		
		background.add(statusPanel);
		background.add(boardPanel);
		background.add(txt);
		
		setContentPane(background);

	}
	
	public void timeDelay() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void twinkle(int[] numList) {
		
		for(int i=0;i<numList.length;i++){
			slot[numList[i]].setBackground(Color.ORANGE);
		}
		
		timeDelay();
		
		for(int i=0;i<numList.length;i++) {
			slot[numList[i]].setBackground(Color.WHITE);
		}
	}
	
	public void recolor() {
		for(int i=0;i<12;i++) {
			slot[i].setBackground(Color.WHITE);
			slot[i].setBorder(new LineBorder(Color.BLACK,5,false));
		}
		
		slot[position1.index].setBorder(new LineBorder(Color.RED,5,false));
		slot[position2.index].setBorder(new LineBorder(Color.BLUE,5,false));
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		char c = arg0.getKeyChar();
		txt.setText(""+c);
		int[] putNum = {0,1,2,3};
		switch(arg0.getKeyChar()) {
			case '1': twinkle(putNum); break;
			case 'w': position1.move(-1,0); recolor(); break;
			case 's': position1.move(1,0); recolor(); break;
			case 'a': position1.move(0, -1); recolor(); break;
			case 'd': position1.move(0, 1); recolor(); break;
			case 'i': position2.move(-1,0); recolor(); break;
			case 'k': position2.move(1,0); recolor(); break;
			case 'j': position2.move(0, -1); recolor(); break;
			case 'l': position2.move(0, 1); recolor(); break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		((JComponent)arg0.getSource()).requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

public class InuyashaProjectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			/*
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		MyFrame f = new MyFrame();
	}

}
