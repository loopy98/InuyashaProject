import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.LinkedList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;


class Slot extends JLabel{

	int count1 = -1 ;
	Timer timer1 = new Timer(250, new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
			switch(count1) {
				case -1: setVisible(true); break;
				case 0 : setBackground(new Color(205,92,92)); break;
				case 1 : setBackground(new Color(255,142,142)); break;
				case 2 : setBackground(new Color(205,92,92)); break;
				case 3 : setBackground(new Color(255,142,142)); break;
			}
			count1++;

	        if (count1 == 4) {
	        	count1 = -1;
	        	setVisible(false);
	        	
	        	timer1.stop();
	        }
	    }    
	});

	int count2 = -1 ;
	Timer timer2 = new Timer(250, new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
			switch(count2) {
				case -1: setVisible(true); break;
				case 0 : setBackground(new Color(100,149,237)); break;
				case 1 : setBackground(new Color(150,199,255)); break;
				case 2 : setBackground(new Color(100,149,237)); break;
				case 3 : setBackground(new Color(150,199,255)); break;
			}
			count2++;

	        if (count2 == 4) {
	        	count2 = -1;
	        	setVisible(false);
	        	timer2.stop();
	        }
	    }    
	});

	
	public void twinkle1(){
		if(!timer1.isRunning()) {
			timer1.start();
		}
	}

	public void twinkle2(){
		if(!timer2.isRunning())
			timer2.start();
	}
	
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
	
	public void resetTwinkle() {
		setBackground(Color.white);
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
	
	public boolean move(int dx,int dy) {
		boolean flag = true;
		if(x+dx<0) {x=0;return false;}
		if(x+dx>2) {x=2;return false;}
		if(y+dy<0) {y=0;return false;}
		if(y+dy>3) {y=3;return false;}
		x+=dx;
		y+=dy;
		index = 4*x+y;
		
		return flag;
	}
		
	public boolean isMovable(int dx,int dy) {
		
		if(x+dx<0) return false;
		if(x+dx>2) return false;
		if(y+dy<0) return false;
		if(y+dy>3) return false;
		
		return true;
		
	}
	public boolean isMovable(pos range) {
		
		if(x+range.x<0) return false;
		if(x+range.x>2) return false;
		if(y+range.y<0) return false;
		if(y+range.y>3) return false;
		
		return true;
		
	}
}

class Skill {
	String name;
	pos[] range;
	int[] target = null;
	char code;
	int damage;
	int cost;
	
	public boolean isRangeType() {
		if(target == null) return false;
		return true;
	}
	
	public Skill(String name, int damage, int cost, pos[] range, char code) {
		super();
		this.name = name;
		this.damage = damage;
		this.cost = cost;
		this.range = range;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public pos[] getRange() {
		return range;
	}
	public void setRange(pos[] range) {
		this.range = range;
	}
	public char getCode() {
		return code;
	}
	public void setCode(char code) {
		this.code = code;
	}
	
}

class Character {
	int p;
	String name;
	int healthPoint;
	int manaPoint;
	Skill[] skillSet = new Skill[6];
	String classNumber;
	
	boolean shield = false;
	
	pos location = new pos(0,0);

	public Character(String name,pos location,int player) {
		super();
		this.p = player;
		this.name = name;
		this.location = location;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public int getManaPoint() {
		return manaPoint;
	}

	public void setManaPoint(int manaPoint) {
		this.manaPoint = manaPoint;
	}

	public Skill[] getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(Skill skill,int index) {
		this.skillSet[index] = skill;
	}

	public pos getLocation() {
		return location;
	}

	public void setLocation(pos location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

class subFrame extends JFrame{
	
	JPanel dataPanel = new JPanel();
	JButton confirmBtn = new JButton("레츠고!");
	
	JPanel dataPanel1 = new JPanel();
	JPanel dataPanel2 = new JPanel();
	
	/////////////////////////////////////////////////////
	//statPanel
	
	JPanel statPanel1 = new JPanel();
	JPanel statPanel2 = new JPanel();
	
	JPanel classPanel1 = new JPanel();
	JPanel healthPanel1 = new JPanel();
	JPanel manaPanel1 = new JPanel();
	
	JLabel classLabel1 = new JLabel("반");
	JLabel healthLabel1 = new JLabel("체력");
	JLabel manaLabel1 = new JLabel("행동력");

	JFormattedTextField classTxt1 = new JFormattedTextField(new NumberFormatter());
	JFormattedTextField healthTxt1 = new JFormattedTextField(new NumberFormatter());
	JFormattedTextField manaTxt1 = new JFormattedTextField(new NumberFormatter());
	
	JPanel classPanel2 = new JPanel();
	JPanel healthPanel2 = new JPanel();
	JPanel manaPanel2 = new JPanel();
	
	JLabel classLabel2 = new JLabel("반");
	JLabel healthLabel2 = new JLabel("체력");
	JLabel manaLabel2 = new JLabel("행동력");
	
	JFormattedTextField classTxt2 = new JFormattedTextField(new NumberFormatter());
	JFormattedTextField healthTxt2 = new JFormattedTextField(new NumberFormatter());
	JFormattedTextField manaTxt2 = new JFormattedTextField(new NumberFormatter());
	
	////////////////////////////////////////////////////
	//skillPanel
	JPanel skillPanel1 = new JPanel();
	JPanel skillPanel2 = new JPanel();
	
	JComboBox<String>[] skillComboBox1 = new JComboBox[6];
	JComboBox<String>[] skillComboBox2 = new JComboBox[6];
	
	ImageIcon mainIcon = new ImageIcon(getClass().getResource("nupjookIcon.png"));
	
	subFrame(){
		super("input the data!!");
		setLayout(new BorderLayout());
	
		this.setIconImage(mainIcon.getImage());
		
		confirmBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				MyFrame.nupJook1.setName(classTxt1.getText()+"반 넙죽이");
				MyFrame.nupJook1.setHealthPoint(Integer.parseInt(healthTxt1.getText()));
				MyFrame.nupJook1.setManaPoint(Integer.parseInt(manaTxt1.getText()));
				MyFrame.nupJook2.setName(classTxt2.getText()+"반 넙죽이");
				MyFrame.nupJook2.setHealthPoint(Integer.parseInt(healthTxt2.getText()));
				MyFrame.nupJook2.setManaPoint(Integer.parseInt(manaTxt2.getText()));
				
				MyFrame.nupJook1.classNumber=classTxt1.getText();
				MyFrame.nupJook2.classNumber=classTxt2.getText();
				
				for(int i=0;i<6;i++) {
					String leftSkill = (String) skillComboBox1[i].getSelectedItem();
					String rightSkill = (String) skillComboBox2[i].getSelectedItem();
					
					for(int j=0;j<MyFrame.skillList.length;j++) {
						if(leftSkill == MyFrame.skillList[j].name) MyFrame.nupJook1.setSkillSet(MyFrame.skillList[j],i);
						if(leftSkill == "튀어오르기") MyFrame.nupJook1.setSkillSet(MyFrame.skilllllll, i);
						if(rightSkill == MyFrame.skillList[j].name) MyFrame.nupJook2.setSkillSet(MyFrame.skillList[j],i);
						if(rightSkill == "튀어오르기") MyFrame.nupJook2.setSkillSet(MyFrame.skilllllll, i);
					}
				}
				
				MyFrame f = new MyFrame();
				dispose();
			}
		});
		
		/////////////////////////////////////////////////////////////////////////////
		//dataPanel
		dataPanel1.setBorder(new TitledBorder(new EtchedBorder(),"왼쪽 넙죽이"));
		dataPanel2.setBorder(new TitledBorder(new EtchedBorder(),"오른쪽 넙죽이"));
		
		dataPanel1.setLayout(new GridLayout(2,1,5,5));
		dataPanel2.setLayout(new GridLayout(2,1,5,5));
		
		dataPanel1.add(statPanel1);
		dataPanel1.add(skillPanel1);
		dataPanel2.add(statPanel2);
		dataPanel2.add(skillPanel2);
		
		dataPanel.setLayout(new GridLayout(1,2,5,5));
		dataPanel.add(dataPanel1);
		dataPanel.add(dataPanel2);
		
		///////////////////////////////////////////////////////////////////////////////
		//statPanel
		statPanel1.setLayout(new GridLayout(3,1,5,5));
		statPanel2.setLayout(new GridLayout(3,1,5,5));
		
		statPanel1.add(classPanel1);
		statPanel1.add(healthPanel1);
		statPanel1.add(manaPanel1);
		
		classPanel1.add(classLabel1);
		classPanel1.add(classTxt1);
		classTxt1.setColumns(20);
		healthPanel1.add(healthLabel1);
		healthPanel1.add(healthTxt1);
		healthTxt1.setColumns(20);
		manaPanel1.add(manaLabel1);
		manaPanel1.add(manaTxt1);
		manaTxt1.setColumns(20);
		
		statPanel2.add(classPanel2);
		statPanel2.add(healthPanel2);
		statPanel2.add(manaPanel2);
		
		classPanel2.add(classLabel2);
		classPanel2.add(classTxt2);
		classTxt2.setColumns(20);
		healthPanel2.add(healthLabel2);
		healthPanel2.add(healthTxt2);
		healthTxt2.setColumns(20);
		manaPanel2.add(manaLabel2);
		manaPanel2.add(manaTxt2);
		manaTxt2.setColumns(20);
		
		///////////////////////////////////////////////////////////////////////////////
		//skillPanel
		skillPanel1.setLayout(new GridLayout(3,2,20,10));
		skillPanel2.setLayout(new GridLayout(3,2,20,10));
		
		skillPanel1.setBorder(new TitledBorder(new EtchedBorder(),"스킬셋"));
		skillPanel2.setBorder(new TitledBorder(new EtchedBorder(),"스킬셋"));
		
		for(int i=0;i<6;i++) {
			skillComboBox1[i] = new JComboBox<String>();
			skillComboBox2[i] = new JComboBox<String>();
			
			skillComboBox1[i].addItem("튀어오르기");
			skillComboBox2[i].addItem("튀어오르기");
			
			for(int j=0;j<MyFrame.skillList.length;j++) {
				skillComboBox1[i].addItem(MyFrame.skillList[j].name);
				skillComboBox2[i].addItem(MyFrame.skillList[j].name);
			}
			
			skillPanel1.add(skillComboBox1[i]);
			skillPanel2.add(skillComboBox2[i]);
		}
		
		///////////////////////////////////////////////////////////////////////////////
		//arrangement
		this.add(dataPanel);
		this.add(confirmBtn,BorderLayout.SOUTH);
		
		setSize(647,400);
		setLocation(50,50);
		setVisible(true);
	}
	
}

class MyFrame extends JFrame implements KeyListener,MouseListener{
	

	JPanel background;
	
	static Character nupJook1 = new Character("넙죽이A",new pos(1,0),1);
	static Character nupJook2 = new Character("넙죽이B",new pos(1,3),2);
	
	JPanel mainPanel = new JPanel();
	JPanel textPanel = new JPanel();

	JPanel playerClassPanel1 = new JPanel();
	JPanel playerClassPanel2 = new JPanel();
	
	JPanel classPanel1 = new JPanel();
	JPanel classPanel2 = new JPanel();
	
	JPanel statusPanel=new JPanel();
	
	JPanel boardPanel=new JPanel();
	JPanel boardPanel2=new JPanel();
	
	JPanel playerPanel1 = new JPanel();
	JPanel playerPanel2 = new JPanel();
	
	
	JPanel boxPanel1 = new JPanel();
	JPanel boxPanel2 = new JPanel();
	
	JPanel statusPanel1 = new JPanel();
	JPanel statusPanel2 = new JPanel();
	
	JPanel statusImage1 = new JPanel();
	JPanel statusImage2 = new JPanel();
	
	ImageIcon heart = new ImageIcon(getClass().getResource("heart.png"));
	ImageIcon diamond = new ImageIcon(getClass().getResource("diamond.png"));
	ImageIcon shield = new ImageIcon(getClass().getResource("shield.png"));
	ImageIcon scaledHeart = new ImageIcon(heart.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
	ImageIcon scaledDiamond = new ImageIcon(diamond.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
	ImageIcon scaledShield = new ImageIcon(shield.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
	
	JLabel health1 = new JLabel("체력",JLabel.CENTER);
	JLabel health2 = new JLabel("체력",JLabel.CENTER);
	JLabel mana1 = new JLabel("행동력",JLabel.CENTER);
	JLabel mana2 = new JLabel("행동력",JLabel.CENTER);

	JLabel class1 = new JLabel(nupJook1.classNumber, JLabel.CENTER);
	JLabel class2 = new JLabel(nupJook2.classNumber, JLabel.CENTER);
	

	JLabel playerClass1 = new JLabel(nupJook1.classNumber, JLabel.CENTER);
	JLabel playerClass2 = new JLabel(nupJook2.classNumber, JLabel.CENTER);
	
	JLabel heart1 = new JLabel();
	JLabel heart2 = new JLabel();
	JLabel diamond1 = new JLabel();
	JLabel diamond2 = new JLabel();
	
	JLabel round = new JLabel("Round \n 1",JLabel.CENTER);
	
	ImageIcon nupjookImage = new ImageIcon(getClass().getResource("nudenupjook2.png"));
	ImageIcon scaledNupjookImage = new ImageIcon(nupjookImage.getImage().getScaledInstance(135,135,Image.SCALE_SMOOTH));

	ImageIcon scaledNupjookImage2 = new ImageIcon(nupjookImage.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
	
	JLabel imageLabel1 = new JLabel(scaledNupjookImage);
	JLabel imageLabel2 = new JLabel(scaledNupjookImage);

	JLabel playLabel1 = new JLabel(scaledNupjookImage2,JLabel.CENTER);
	JLabel playLabel2 = new JLabel(scaledNupjookImage2,JLabel.CENTER);
	
	JLabel shieldLabel1 = new JLabel(scaledShield);
	JLabel shieldLabel2 = new JLabel(scaledShield);
	
	ImageIcon mainIcon = new ImageIcon(getClass().getResource("nupjookIcon.png"));
	
	JTextArea txt = new JTextArea();
	
	JButton okayBtn = new JButton("레츠고!");
	
	static Slot[] slot = new Slot[12];
	static Slot[] slot2 = new Slot[12];
	
	static Skill skillz = new Skill("404 Not Found",1,1,new pos[]{new pos(-1,0),new pos(1,0),new pos(0,-1),new pos(0,1)},'z');
	static Skill skillx = new Skill("스토크스 빔",1,1,new pos[]{new pos(0,-3),new pos(0,-2),new pos(0,-1),new pos(0,0),new pos(0,1),new pos(0,2),new pos(0,3)},'x');
	static Skill skillc = new Skill("세미콜론 삭제",3,1,new pos[]{new pos(-1,0)},'c');
	static Skill skillv = new Skill("핵분열",2,7,new pos[] {new pos(-2,-3),new pos(-2,-2),new pos(-2,-1),new pos(-2,0),new pos(-2,1),new pos(-2,2),new pos(-2,3),new pos(-1,3),new pos(-1,2),new pos(-1,1),new pos(-1,0),new pos(-1,-1),new pos(-1,-2),new pos(-1,-3),new pos(0,-3),new pos(0,-2),new pos(0,-1),new pos(0,0),new pos(0,1),new pos(0,2),new pos(0,3),new pos(1,-3),new pos(1,-2),new pos(1,-1),new pos(1,0),new pos(1,1),new pos(1,2),new pos(1,3),new pos(2,-3),new pos(2,-2),new pos(2,-1),new pos(2,0),new pos(2,1),new pos(2,2),new pos(2,3)},'v');
	static Skill skillb = new Skill("암세포 분열",2,2,new pos[] {new pos(0,-1),new pos(-1,-1),new pos(-1,1),new pos(1,1),new pos(0,1),new pos(1,-1)},'b');
	static Skill skilln = new Skill("삼각자 던지기",3,2,new pos[] {new pos(-1,1),new pos(-1,-1),new pos(1,1),new pos(1,-1)},'n');
	static Skill skillm = new Skill("산화반응",2,1,new pos[] {new pos(2,0),new pos(1,0),new pos(0,0),new pos(-1,0),new pos(-2,0)},'m');
	static Skill skillj = new Skill("완전연소",2,2,new pos[] {new pos(1,0),new pos(-1,0),new pos(0,1),new pos(0,-1)},'j');
	static Skill skillk = new Skill("유전자 비분리",1,2,new pos[] {new pos(-1,0),new pos(-1,-1),new pos(-1,1),new pos(1,1),new pos(1,0),new pos(1,-1),new pos(0,1),new pos(0,-1)},'k');
	static Skill skilll = new Skill("이중슬릿",1,1,new pos[]{new pos(0,-1),new pos(-1,-1),new pos(-1,1),new pos(1,1),new pos(0,1),new pos(1,-1)},'l');
	static Skill skilllllll = new Skill("튀어오르기",0,0,new pos[] {},'?');
	static Skill skillq = new Skill("무기능력:넙죽칼",3,3,new pos[] {new pos(0,1),new pos(0,-1)},'q');
	static Skill skillw = new Skill("무기능력:넙죽활",4,3,new pos[] {new pos(0,1),new pos(0,-1)},'w');
	static Skill skille = new Skill("무기능력:딱총나무 지팡이",4,3,new pos[] {new pos(1,0),new pos(-1,0)},'e');
	static Skill skilla = new Skill("무기능력:철퇴",5,4,new pos[] {new pos(0,1),new pos(0,-1)},'a');
	static Skill skills = new Skill("무기능력:훈민정음 해례본",5,5,new pos[] {new pos(0,1),new pos(0,-1),new pos(1,0),new pos(-1,0)},'s');
	static Skill skilld = new Skill("무기능력:사랑의 요술봉",3,5,new pos[] {new pos(0,1),new pos(0,-1),new pos(1,0),new pos(-1,0),new pos(1,1),new pos(1,-1),new pos(-1,-1),new pos(-1,1)},'d');
	static Skill skillo = new Skill("실리콘 도핑",0,-3,new pos[] {},'o');
	//static Skill skillg = new Skill("이단이동1",0,0,new pos[] {},'g');
	//static Skill skillh = new Skill("이단이동2",0,0,new pos[] {},'h');
	
	static Skill[] skillList = {skillz,skillx,skillc,skillv,skillb,skilln,skillm,skillj,skillk,skilll,skillq,skillw,skille,skilla,skills,skilld};
	
	static String[] action = new String[6];
	
	JButton actionBtn = new JButton("행동 입력");
	
	Timer timer;
	int count;
	int typingCount = 0;
	
	MyFrame(){
		super("Let's Go! NUPJOOK!");
		setSize(900,720);
		setLocation(80,15);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		
		this.setIconImage(mainIcon.getImage());
		
		playLoopSound("backgroundMusic.wav");
		
		//background
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				ImageIcon bgImage = new ImageIcon(getClass().getResource("backgroundImage_1.png"));
				ImageIcon scaledbgImage = new ImageIcon(bgImage.getImage().getScaledInstance(900,675,Image.SCALE_SMOOTH));
				g.drawImage(bgImage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		

		
		//playerPanel
		playerPanel1.setLayout(new BorderLayout());
		playerPanel1.setBackground(new Color(0,0,0,0));
		playerPanel1.add(playLabel1,0);
		playerPanel2.setLayout(new BorderLayout());
		playerPanel2.setBackground(new Color(0,0,0,0));
		playerPanel2.add(playLabel2,0);
		
		
		//boxPanel
		
		boxPanel1.setLayout(new BorderLayout());
		boxPanel1.setBackground(new Color(250,238,214));
		boxPanel1.setBorder(new LineBorder(new Color(150,138,114),5,false));	

		boxPanel2.setLayout(new BorderLayout());
		boxPanel2.setBackground(new Color(250,238,214));
		boxPanel2.setBorder(new LineBorder(new Color(150,138,114),5,false));	
		
		//classPanel

		classPanel1.setLayout(new BorderLayout());
		classPanel1.setBackground(new Color(0,0,0,0));
		classPanel1.add(class1,0);
		class1.setFont(new Font("나눔스퀘어라운드",Font.BOLD,30));
		class1.setOpaque(false);
		

		classPanel2.setLayout(new BorderLayout());
		classPanel2.setBackground(new Color(0,0,0,0));
		classPanel2.add(class2,0);
		class2.setFont(new Font("나눔스퀘어라운드",Font.BOLD,30));
		class2.setOpaque(false);
		//statusPanel
		

		//playerClassPanel
		playerClassPanel1.setLayout(new BorderLayout());
		playerClassPanel1.setBackground(new Color(0,0,0,0));
		playerClassPanel1.add(playerClass1,0);
		playerClass1.setFont(new Font("나눔스퀘어라운드",Font.BOLD,23));
		playerClass1.setOpaque(false);
		
		playerClassPanel2.setLayout(new BorderLayout());
		playerClassPanel2.setBackground(new Color(0,0,0,0));
		playerClassPanel2.add(playerClass2,0);
		playerClass2.setFont(new Font("나눔스퀘어라운드",Font.BOLD,23));
		playerClass2.setOpaque(false);
		
		statusPanel.setBounds(4,4,893,135);
		statusPanel.setBackground(new Color(0,0,0,0));
		
		statusPanel1.setLayout(new BorderLayout());
		statusPanel1.setBackground(new Color(0,0,0,0));
		statusPanel1.add(health1,BorderLayout.NORTH);
		statusPanel1.add(mana1,BorderLayout.SOUTH);
		
		health1.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
		health1.setOpaque(false);
		health1.setBackground(new Color(201,201,201));
		mana1.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
		mana1.setOpaque(false);
		mana1.setBackground(new Color(201,201,201));
		
		heart1.setText("x"+nupJook1.healthPoint);
		heart1.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
		heart1.setIcon(scaledHeart);
		diamond1.setText("x"+nupJook1.manaPoint);
		diamond1.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
		diamond1.setIcon(scaledDiamond);
		
		statusPanel2.setLayout(new BorderLayout());
		statusPanel2.setBackground(new Color(0,0,0,0));
		statusPanel2.add(health2,BorderLayout.NORTH);
		statusPanel2.add(mana2,BorderLayout.SOUTH);
		
		health2.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
		health2.setOpaque(false);
		health2.setBackground(new Color(201,201,201));
		mana2.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
		mana2.setOpaque(false);
		mana2.setBackground(new Color(201,201,201));
		
		heart2.setText("x"+nupJook2.healthPoint);
		heart2.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
//		heart2.setHorizontalAlignment(SwingConstants.RIGHT);
		heart2.setIcon(scaledHeart);
		diamond2.setText("x"+nupJook2.manaPoint);
		diamond2.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
//		diamond2.setHorizontalAlignment(SwingConstants.RIGHT);
		diamond2.setIcon(scaledDiamond);
		
		boxPanel1.setBounds(155,10,193,130);
		boxPanel2.setBounds(542,10,193,130);
		classPanel1.setBounds(4,4,135,135);
		imageLabel1.setBounds(4,4,135,135);
		classPanel2.setBounds(750,4,135,135);
		imageLabel2.setBounds(750,4,135,135);
		statusPanel1.setBounds(165,20,90,107);
		statusPanel2.setBounds(552,20,90,107);
		shieldLabel1.setBounds(4,150,38,38);
		shieldLabel2.setBounds(848,150,38,38);
		
		heart1.setBounds(258,20,90,45);
		diamond1.setBounds(258,85,90,45);
		heart2.setBounds(645,20,90,45);
		diamond2.setBounds(645,85,90,45);

		round.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,20));
		round.setBounds(550,5,100,120);
		
		//boardPanel
		boardPanel.setLayout(new GridLayout(3,4,5,5));
		boardPanel.setBounds(4, 270,862, 270);
		boardPanel.setOpaque(false);	
		boardPanel.addMouseListener(this);

		boardPanel2.setLayout(new GridLayout(3,4,5,5));
		boardPanel2.setBounds(4, 270,862, 270);
		boardPanel2.setOpaque(false);	
		boardPanel2.addMouseListener(this);
		
		for(int i=0;i<12;i++) {
			slot[i] = new Slot();
			slot[i].setOpaque(true);
			slot[i].setBorder(new LineBorder(Color.BLACK,5,false));
			slot[i].setVisible(false);
			boardPanel.add(slot[i]);
		}
		
		for(int i=0;i<12;i++) {
			slot2[i] = new Slot();
			slot2[i].setOpaque(true);
			slot2[i].setBackground(new Color(245,222,178));
			slot2[i].setBorder(new LineBorder(Color.BLACK,5,false));
			boardPanel2.add(slot2[i]);
		}
		/*
		for(int i=0;i<12;i++) {
			boardPanel.setComponentZOrder(slot[i],1);
			boardPanel.setComponentZOrder(slot2[i],1);
		}
		*/

		recolor();
		
		//textField
		txt.setBounds(4,548,862,80);
		txt.setFont(new Font("나눔스퀘어라운드",Font.PLAIN,30));
		txt.setFocusable(false);
		
		actionBtn.setBounds(775,630,90,40);
		actionBtn.setFocusable(false);
		actionBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				InputFrame iFrame = new InputFrame();
				
			}
			
		});
		
		okayBtn.setBounds(5,630,90,40);
		okayBtn.setFocusable(false);
		okayBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("start");
				continueTurn();
				
			}
			
		});
		
		//arrangement
		background.setLayout(null);

		
		
		background.add(classPanel1);
		background.add(imageLabel1);
		background.add(classPanel2);
		background.add(imageLabel2);
		background.add(statusPanel1);
		background.add(statusPanel2);
		
		background.add(heart1);
		background.add(heart2);
		background.add(diamond1);
		background.add(diamond2);
		background.add(shieldLabel1);
		background.add(shieldLabel2);

		background.add(boxPanel1);
		background.add(boxPanel2);
		
		checkShield();
		
		//background.add(round);
		
		background.add(statusPanel);
		background.add(boardPanel,-1);
		background.add(boardPanel2,-1);
		background.add(txt);
		background.add(actionBtn);
		background.add(okayBtn);
		
		setContentPane(background);
		
		txt.setText("다음 행동을 입력하세요!"+"\n"+">");
	}
	/*
	public void twinkle(Slot slot) {
		
		Timer timer1 = new Timer(250, new ActionListener() {
			int count1 = -1 ;
		    public void actionPerformed(ActionEvent evt) {
				switch(count1) {
					case -1: setVisible(true); break;
					case 0 : setBackground(new Color(205,92,92)); break;
					case 1 : setBackground(new Color(255,142,142)); break;
					case 2 : setBackground(new Color(205,92,92)); break;
					case 3 : setBackground(new Color(255,142,142)); break;
				}
				count1++;

		        if (count1 == 4) {
		        	count1 = -1;
		        	setVisible(false);
		        	
		        	timer1.stop();
		        }
		    }    
		});

		int count2 = -1 ;
		Timer timer2 = new Timer(250, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
				switch(count2) {
					case -1: setVisible(true); break;
					case 0 : setBackground(new Color(100,149,237)); break;
					case 1 : setBackground(new Color(150,199,255)); break;
					case 2 : setBackground(new Color(100,149,237)); break;
					case 3 : setBackground(new Color(150,199,255)); break;
				}
				count2++;

		        if (count2 == 4) {
		        	count2 = -1;
		        	setVisible(false);
		        	timer2.stop();
		        }
		    }    
		});

	}
*/	
	public void playSound(String str) {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(str));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void playLoopSound(String str) {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(str));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        clip.loop(Clip.LOOP_CONTINUOUSLY);	        
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void checkShield() {
		if(nupJook1.shield) shieldLabel1.setVisible(true);
		else shieldLabel1.setVisible(false);
		
		if(nupJook2.shield) shieldLabel2.setVisible(true);
		else shieldLabel2.setVisible(false);
	}
	
	public void recolor() {
		for(int i=0;i<12;i++) {

			slot[i].setVisible(false);
			slot2[i].setBackground(new Color(245,222,178));
			slot2[i].setBorder(new LineBorder(Color.BLACK,5,false));
		}
		
		int a1 = 20+216*(nupJook1.location.index%4);
		int a2 = 100+216*(nupJook2.location.index%4);
		int b1 = 250+91*(nupJook1.location.index/4);
		int b2 = 250+91*(nupJook2.location.index/4);
		int c = 100;
		int d = 100;
		
		/*slot[nupJook1.location.index].setBackground(new Color(255,200,200));
		slot[nupJook1.location.index].setBorder(new LineBorder(Color.RED,5,false));
		slot[nupJook2.location.index].setBackground(new Color(200,200,255));
		slot[nupJook2.location.index].setBorder(new LineBorder(Color.BLUE,5,false));
		if(nupJook1.location.index == nupJook2.location.index ) {
			slot[nupJook1.location.index].setBorder(new LineBorder(new Color(75,0,130),5,false));
			slot[nupJook1.location.index].setBackground(new Color(216,191,216));
		}*/
		
		slot2[nupJook1.location.index].setBackground(new Color(255,200,200));
		slot2[nupJook1.location.index].setBorder(new LineBorder(Color.RED,5,false));
		slot2[nupJook2.location.index].setBackground(new Color(200,200,255));
		slot2[nupJook2.location.index].setBorder(new LineBorder(Color.BLUE,5,false));
		if(nupJook1.location.index == nupJook2.location.index ) {
			slot2[nupJook1.location.index].setBorder(new LineBorder(new Color(75,0,130),5,false));
			slot2[nupJook1.location.index].setBackground(new Color(216,191,216));
		}
		
		//background.setComponentZOrder(boardPanel,1);
		//background.setComponentZOrder(boardPanel2,1);
		
		playerPanel1.setBounds(a1,b1,c,d);
		playerPanel2.setBounds(a2,b2,c,d);
		playerClassPanel1.setBounds(a1,b1,c,d);
		playerClassPanel2.setBounds(a2,b2,c,d);
		
		
		background.add(playerClassPanel1,0);
		background.add(playerClassPanel2,0);
		background.add(playerPanel1,0);
		background.add(playerPanel2,0);
	}
	
	public void checkData() {
		heart1.setText("x"+nupJook1.healthPoint);
		diamond1.setText("x"+nupJook1.manaPoint);
		heart2.setText("x"+nupJook2.healthPoint);
		diamond2.setText("x"+nupJook2.manaPoint);
	}

	public LinkedList<Integer> skillHitRange(Character nupjook,char code){
		LinkedList<Integer> output = new LinkedList<>();
		pos[] outputArray = null;
		
		pos center = nupjook.getLocation();
		
		switch(code) {
			case 'z': outputArray = skillz.range; break; //2468
			case 'x': outputArray = skillx.range; break; //all garo line 
			case 'c': outputArray = skillc.range; break; //2
			case 'v': outputArray = skillv.range; break;
			case 'b': outputArray = skillb.range; break;
			case 'n': outputArray = skilln.range; break;
			case 'm': outputArray = skillm.range; break;
			case 'j': outputArray = skillj.range; break;
			case 'k': outputArray = skillk.range; break;
			case 'l': outputArray = skilll.range; break;
			case '?': outputArray = skilllllll.range; break;
			case 'q': outputArray = skillq.range; break;
			case 'w': outputArray = skillw.range; break;
			case 'e': outputArray = skille.range; break;
			case 'a': outputArray = skilla.range; break;
			case 's': outputArray = skills.range; break;
			case 'd': outputArray = skilld.range; break;
			case 'o': outputArray = skillo.range; break;
		}
		
		for(int i=0;i<outputArray.length;i++) {
			pos range = outputArray[i];
			if(center.isMovable(range)) {
				pos target = new pos(center.x,center.y);
				target.move(range.x, range.y);
				output.addFirst(target.index);
			}
		}
		
		System.out.println(output.toString());
		return output;
	}
	String str = "";
	String line = "";
	Timer typer1 = new Timer(50, new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			
			txt.setText(str.substring(0,++typingCount));
			
			if(typingCount == str.length()) { 
				typingCount = 0;
				typer1.stop();
			}
			
	    }    
	});

	Timer typer2 = new Timer(500, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			txt.setText(line+str.substring(0,++typingCount));
				
			if(typingCount == str.length()) {
				typingCount = 0;
				typer2.stop();
			}
		};
	});
	
	public void typing(String stri) {
		str = stri;
		if(!typer1.isRunning())
			typer1.start();
		//txt.setText(stri);
	}
	
	public void typing(String lin,String stri) {
		str = stri;
		line = lin;
		if(!typer2.isRunning())
			typer2.start();
		//txt.setText(line+str);
	}
	public int skillNametoType(String name) {
		
		switch(name){
			case "왼쪽으로!":
			case "오른쪽으로!":
			case "아래로!":
			case "위로!": return 1;
				
			case "도핑!":
			case "방어!": return 0;
		}
		
		return 2;
	}
	
	public Skill skillNametoSkill(String name) {
		for(int i=0;i<skillList.length;i++) {
			if(name==skillList[i].name) return skillList[i];
		}
		return skilllllll;
	}
	
	public void continueTurn() {
		String[] left = {action[0],action[1],action[2]};
		String[] right = {action[3],action[4],action[5]};
		
		String[] toDoAction = new String[6];
		Character[] whoseAction = new Character[6];
		
		for(int i=0;i<3;i++) {
			if(skillNametoType(left[i])==2) {
				toDoAction[2*i] = right[i];
				toDoAction[2*i+1] = left[i];
				whoseAction[2*i] = nupJook2;
				whoseAction[2*i+1] = nupJook1;
			} else {
				toDoAction[2*i] = left[i];
				toDoAction[2*i+1] = right[i];
				whoseAction[2*i]= nupJook1;
				whoseAction[2*i+1]= nupJook2;
			}
		}
		setTimer(toDoAction,whoseAction);
	}
	
	public void useSth(Character nupjook,String str) {
		switch(skillNametoType(str)) {
			case 0: useDopOrShield(nupjook,str); break;
			case 1: useMove(nupjook,str); break;
			case 2: useSkill(nupjook,skillNametoSkill(str)); break;
		}
	}
	
	public void setTimer(String[] act,Character[] who) {
		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(count%2==0) {
					nupJook1.shield = false;
					nupJook2.shield = false;
					checkShield();
				}
				
				if(count<6) {	
					
				playSound("Beep.wav");
				//System.out.println("beep");
				System.out.println(count);
				System.out.println(count+": "+who[count].name+"의 "+act[count]+"!");
				useSth(who[count],act[count]);
				
				}
				
				count++;
				
				if (count == 8) {
					count = 0;
					txt.setText("다음 행동을 입력하세요!"+"\n"+">");
					timer.stop();
				}
			}
		};
		
		timer = new Timer(3000,taskPerformer);
		//timer.setRepeats(true);
		count=0;
		timer.start();
	}
	
	public void useDopOrShield(Character nupjook,String str) {
		if(str == "도핑!") useSkill(nupjook,skillo);
		else {
			nupjook.shield=true;
			typing(nupjook.name+"는 방어 태세에 들어갔다!");
			checkShield();
		}
	}
	
	public void useMove(Character nupjook, String str) {
		switch(str) {
			case "위로!": if(nupjook.location.x > 0) {nupjook.location.move(-1,0); typing(nupjook.name+"는 위로 한 칸 이동했다!");}break;
			case "아래로!": if(nupjook.location.x < 2) {nupjook.location.move(1,0); typing(nupjook.name+"는 아래로 한 칸 이동했다!");} break;
			case "왼쪽으로!": if(nupjook.location.y > 0) {nupjook.location.move(0, -1); typing(nupjook.name+"는 왼쪽으로 한 칸 이동했다!");} break;
			case "오른쪽으로!": if(nupjook.location.y < 3) {nupjook.location.move(0, 1); typing(nupjook.name+"는 오른쪽으로 한 칸 이동했다!");} break;
		}
		recolor(); 
	}

	public void useSkill(Character nupjook,Skill skill) {
		
		String firstLine = nupjook.name+"의 "+skill.getName()+"!";
		
		LinkedList<Integer> inputList = skillHitRange(nupjook,skill.code);
		boolean isHit = false;
		

		
		if(nupjook.manaPoint<skill.cost) {
			typing(firstLine+"\n행동력이 부족해 공격은 실패했다!");
			checkData();
			return;
		} else nupjook.manaPoint-=skill.cost;
		
		
		while(!inputList.isEmpty()) {
			int head = inputList.removeFirst();
			
			if(nupjook.p == 1)
				slot[head].twinkle1();
			if(nupjook.p == 2)
				slot[head].twinkle2();
			
			if(nupjook == nupJook2 && MyFrame.nupJook1.location.index == head) {
				isHit = true;
				if(nupJook1.shield && skill.damage>2) {
					nupJook1.healthPoint-=(skill.damage-2);
					typing(firstLine+"\n"+nupJook1.name+"는 방어로 받는 데미지가 줄어든다!");
					checkData();
					return;
				}
				else if(nupJook1.shield && skill.damage<=2) {
					typing(firstLine+"\n"+nupJook1.name+"는 방어했다!");
					checkData();
					return;
				}
				else nupJook1.healthPoint-=skill.damage;
			}
			if(nupjook == nupJook1 && MyFrame.nupJook2.location.index == head) { 
				isHit = true;
				if(nupJook2.shield && skill.damage>2) {
					nupJook2.healthPoint-=(skill.damage-3);
					typing(firstLine+"\n"+nupJook2.name+"는 방어로 받는 데미지가 줄어든다!");
					checkData();
					return;
				}
				else if(nupJook2.shield && skill.damage<=2) {
					typing(firstLine+"\n"+nupJook2.name+"는 방어했다!");
					checkData();
					return;
				}
				else nupJook2.healthPoint-=skill.damage;
			}
		}
		checkData();
		
		if(isHit) typing(firstLine+"\n효과는 굉장했다!");
		else if(skill==skilllllll) typing(firstLine+"\n넙죽이는 튀어올랐다!");
		else if(skill==skillo) typing(firstLine+"\n"+nupjook.name+"의 행동력이 3 회복되었다!");
		else typing(firstLine+"\n공격은 빗나갔다!");
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		char c = arg0.getKeyChar();
//		txt.setText(""+c);
		System.out.println(""+c);
		/*
		switch(arg0.getKeyChar()) {
			case 'w': if(nupJook1.location.x > 0)useMove(nupJook1,"위로!"); break;
			case 's': if(nupJook1.location.x < 2)useMove(nupJook1,"아래로!"); break;
			case 'a': if(nupJook1.location.y > 0)useMove(nupJook1,"왼쪽으로!"); break;
			case 'd': if(nupJook1.location.y < 3)useMove(nupJook1,"오른쪽으로!"); break;
			
			case 'r': useSkill(nupJook1,nupJook1.skillSet[0]); break;
			case 't': useSkill(nupJook1,nupJook1.skillSet[1]); break;
			case 'f': useSkill(nupJook1,nupJook1.skillSet[2]); break;
			case 'g': useSkill(nupJook1,nupJook1.skillSet[3]); break;
			case 'v': useSkill(nupJook1,nupJook1.skillSet[4]); break;
			case 'b': useSkill(nupJook1,nupJook1.skillSet[5]); break;
			
			case 'y': useSkill(nupJook2,nupJook2.skillSet[0]); break;
			case 'u': useSkill(nupJook2,nupJook2.skillSet[1]); break;
			case 'h': useSkill(nupJook2,nupJook2.skillSet[2]); break;
			case 'j': useSkill(nupJook2,nupJook2.skillSet[3]); break;
			case 'n': useSkill(nupJook2,nupJook2.skillSet[4]); break;
			case 'm': useSkill(nupJook2,nupJook2.skillSet[5]); break;
			
			case 'x': useSkill(nupJook1,skillo); break;
			case 'o': useSkill(nupJook2,skillo); break;
			case 'c': nupJook1.shield=true; typing(nupJook1.name+"는 방어 태세에 들어갔다!"); checkShield(); break;
			case 'i': nupJook2.shield=true; typing(nupJook2.name+"는 방어 태세에 들어갔다!"); checkShield(); break;
			case 'p': nupJook1.shield=false; nupJook2.shield=false; checkShield(); break;
			
			case 'Y': nupJook1.healthPoint++; checkData(); break;
			case 'H': nupJook1.healthPoint--; checkData(); break;
			case 'U': nupJook1.manaPoint++; checkData(); break;
			case 'J': nupJook1.manaPoint--; checkData(); break;
			case 'I': nupJook2.healthPoint++; checkData(); break;
			case 'K': nupJook2.healthPoint--; checkData(); break;
			case 'O': nupJook2.manaPoint++; checkData(); break;
			case 'L': nupJook2.manaPoint--; checkData(); break;
		}*/
		switch(arg0.getKeyCode()) {
		/*
			case KeyEvent.VK_UP: if(nupJook2.location.x > 0)useMove(nupJook2,"위로!"); break;
			case KeyEvent.VK_DOWN: if(nupJook2.location.x < 2)useMove(nupJook2,"아래로!"); break;
			case KeyEvent.VK_LEFT: if(nupJook2.location.y > 0)useMove(nupJook2,"왼쪽으로!"); break;
			case KeyEvent.VK_RIGHT: if(nupJook2.location.y < 3)useMove(nupJook2,"오른쪽으로!"); break;
		*/
			case KeyEvent.VK_SPACE: try {
				System.out.println("start");	
				continueTurn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

class InputFrame extends JFrame{
	
	JPanel mainPanel = new JPanel();
	JPanel btnPanel = new JPanel();
	
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	
	JPanel[] leftActionPanel = new JPanel[3];
	JPanel[] rightActionPanel = new JPanel[3];
	
	JButton confirmBtn = new JButton("입력 완료!");
	
	JLabel[] leftLabel = new JLabel[3];
	JLabel[] rightLabel = new JLabel[3];
	
	JComboBox[] leftComboBox = new JComboBox[3];
	JComboBox[] rightComboBox = new JComboBox[3];
	
	InputFrame(){
		super("Input data!!");
		setLayout(new BorderLayout());
		
		add(mainPanel);
		add(btnPanel,BorderLayout.SOUTH);
		
		btnPanel.add(confirmBtn);
		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				setVisible(false);
				
				String action1 = (String) leftComboBox[0].getSelectedItem();
				String action2 = (String) leftComboBox[1].getSelectedItem();
				String action3 = (String) leftComboBox[2].getSelectedItem();
				String action4 = (String) rightComboBox[0].getSelectedItem();
				String action5 = (String) rightComboBox[1].getSelectedItem();
				String action6 = (String) rightComboBox[2].getSelectedItem();
				
				String[] actionList = {action1,action2,action3,action4,action5,action6};
				
				MyFrame.action= actionList;
				
				dispose();
				
			}
			
		});
		
		mainPanel.setLayout(new GridLayout(1,2,5,5));
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new GridLayout(3,1,5,5));
		leftPanel.setBorder(new TitledBorder(new EtchedBorder(),"왼쪽 넙죽이"));
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new GridLayout(3,1,5,5));
		rightPanel.setBorder(new TitledBorder(new EtchedBorder(),"오른쪽 넙죽이"));
		
		for(int i=0;i<3;i++) {
			leftActionPanel[i] = new JPanel();
			rightActionPanel[i] = new JPanel();
			
			leftPanel.add(leftActionPanel[i]);
			rightPanel.add(rightActionPanel[i]);
			
			leftLabel[i] = new JLabel("행동 "+i);
			rightLabel[i] = new JLabel("행동"+i);
			
			leftActionPanel[i].add(leftLabel[i]);
			rightActionPanel[i].add(rightLabel[i]);
			
			leftComboBox[i] = new JComboBox();
			rightComboBox[i] = new JComboBox();
			
			leftActionPanel[i].add(leftComboBox[i]);
			rightActionPanel[i].add(rightComboBox[i]);
			
			leftComboBox[i].addItem("왼쪽으로!");
			leftComboBox[i].addItem("오른쪽으로!");
			leftComboBox[i].addItem("아래로!");
			leftComboBox[i].addItem("위로!");
			leftComboBox[i].addItem("도핑!");
			leftComboBox[i].addItem("방어!");
			
			rightComboBox[i].addItem("왼쪽으로!");
			rightComboBox[i].addItem("오른쪽으로!");
			rightComboBox[i].addItem("아래로!");
			rightComboBox[i].addItem("위로!");
			rightComboBox[i].addItem("도핑!");
			rightComboBox[i].addItem("방어!");
			
			for(int j=0;j<6;j++) {
				Skill getSkill1 = MyFrame.nupJook1.skillSet[j];
				if(getSkill1.name!="튀어오르기") leftComboBox[i].addItem(getSkill1.name);
				
				Skill getSkill2 = MyFrame.nupJook2.skillSet[j];
				if(getSkill2.name!="튀어오르기") rightComboBox[i].addItem(getSkill2.name);
			}
			
		}
		
		setSize(450,300);
		setLocation(400,120);
		setVisible(true);
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
		
		//real mode
		//
		
		
//		MyFrame f = new MyFrame();
		subFrame f = new subFrame();
		//InputFrame f =new InputFrame();
		
		//debugging Mode
		///*
		
		//*/
	}

}
