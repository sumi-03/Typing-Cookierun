package MiniPJ;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGroundPanel extends JPanel {

	private GameGroundPanel groundPanel = null;
	private Vector<JLabel> words = new Vector<JLabel>();

	private ImageIcon image1 = new ImageIcon("background1-1.jpg");
	private Image img1 = image1.getImage();

	private ImageIcon image2 = new ImageIcon("background2-1.jpg");
	private Image img2 = image2.getImage();

	private ImageIcon image3 = new ImageIcon("background3-1.jpg");
	private Image img3 = image3.getImage();

	private TextSource textSource = null;
	private ScorePanel scoreObject = null;

	private int index = 0;
	
	// 다른파일에서 종료시키므로 public으로 설정
	public AddWord word = null;
	public ChangeWord word2 = null;

	private SpecialEffects effects = null;

	private int stage = 0;
	private boolean easyHard;

	private JLabel effectWord = new JLabel("(ㅇㅂㅇ)");
	
	private Clip clip1;
	private Clip clip3;


	public GameGroundPanel(JPanel panel, int stage, boolean easyHard) {

		this.easyHard = easyHard;
		this.stage = stage;
		groundPanel = this;
		
		audio();
		
		setLayout(null);

		scoreObject = new ScorePanel();
		add(scoreObject);

		addVecter();

		word = new AddWord();
		word2 = new ChangeWord();
		word.start();
		word2.start();

		effects = new SpecialEffects();
		effects.start();

	}

	public int modifyScore(int n) {
		
		scoreObject.modify(n);
		return scoreObject.getScore();

	}
	
	public void addVecter() { // 단어를 만들어 벡터에 추가

		textSource = new TextSource(stage);
		int size = textSource.size();
		Color colorArray[] = { Color.red, Color.black, Color.white, Color.yellow, Color.green, Color.blue, Color.gray, Color.pink };
		int randomColorNumber;
		for (int i = 0; i < size; i++) {

			String text = textSource.next(index); // 벡터에 넣었던 문자열
			index++;

			JLabel word = new JLabel(text);

			if (stage == 3) {
				randomColorNumber = (int) (Math.random() * 8);
				word.setForeground(colorArray[randomColorNumber]);
			} 
			else {
				word.setForeground(Color.white);
			}

			int x = (int) (Math.random() * 900 + 1);
			int y = -30;

			word.setBounds(x, y, 100, 100);
			words.add(word); // JLabel 벡터에 넣음
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		if (stage == 1) {

			super.paintComponent(g);
			g.drawImage(img1, 0, 0, 1000, 550, this);

		} 
		else if (stage == 2) {

			super.paintComponent(g);
			g.drawImage(img2, 0, 0, 1000, 550, this);
		} 
		else if (stage == 3) {
			
			super.paintComponent(g);
			g.drawImage(img3, 0, 0, 1000, 550, this);
		}
	}

	class AddWord extends Thread { // 벡터에서 몇초마다 단어를 가져와 패널에 붙이는 스레드
		@Override
		public void run() {

			for (int i = 0; i < words.size(); i++) {

				groundPanel.add(words.get(i));
				groundPanel.revalidate();
				groundPanel.repaint();

				try {
					if (stage == 2) {
						sleep(3000);
					} 
					else {
						sleep(1800);
					}
				} 
				catch (InterruptedException e) {
					return;
				}

			}
			return;
		}
	}

	class ChangeWord extends Thread { // 패널에서 제이레이블 컴포넌트만 위치 바꾸는 스레드

		@Override
		public void run() {

			while (true) {

				Component[] components = groundPanel.getComponents();

				for (Component component : components) {

					if (component instanceof JLabel) {

						if (component.getY() >= 470) {
							
							
							if (stage == 1) {
								scoreObject.modify(-10);
							} 
							else if (stage == 2) {
								scoreObject.modify(-20);
							}
							else if(stage == 3){						
								scoreObject.modify(-30);
							}
							
							groundPanel.remove(component);
							
							if(scoreObject.getScore()<0) {
								
								clip1.setFramePosition(0);
								clip1.start();
								
							}
							else {
								clip3.setFramePosition(0);
								clip3.start();
					
							}
						} 
						else {
							component.setLocation(component.getX(), component.getY() + 5);
						} 

					}

				}

				groundPanel.revalidate();
				groundPanel.repaint();

				try { // 단어 아래로 한번 내리고 쉬는 타임

					// 스테이지2 의 경우
					// 멈추는시간을 길게 하여 스테이지1,3보다 단어가 느리게 내려옴

					// easy모드의 경우 hard모드보다 단어가 천천히 내려옴

					if (easyHard) { // easyHard가 true, 즉 easy모드인 경우

						if (stage == 2) { // 스테이지 2의 경우
							
							sleep(300);
						} 
						else { // 스테이지 1,3의 경우
							sleep(250);
						}
					} 
					else { // easyHard가 false, 즉 hard모드인 경우

						if (stage == 2) { // 스테이지 2의 경우
							
							sleep(200);
						} 
						else { // 스테이지 1,3의 경우
							sleep(100);
						}
					}

				} 
				catch (InterruptedException e) {
					
					return;
				}
			}
		}
	}

	class SpecialEffects extends Thread {

		private int time = 0;

		private JLabel findJLabel(String effectText) {

			JLabel label = new JLabel();
			JLabel effectLabel = new JLabel();
			Component[] components = groundPanel.getComponents();

			for (Component component : components) {
				
				if (component instanceof JLabel) {
					
					label = (JLabel) component;
					
					if (label.getText().equals(effectText)) {
						
						effectLabel = label;
						break;
					}
				}
			}

			return effectLabel;

		}

		@Override
		public void run() {

			if (stage == 3) {
				
				effectWord.setText("하하");
			}
			effectWord.setForeground(Color.pink);

			while (true) {
				
				try {
					
					sleep(1000);
					time++;
				} 
				catch (InterruptedException e) {
					
					return;
				}

				if (time % 5 == 0) {

					int x = (int) (Math.random() * 900 + 1);
					int y = (int) (Math.random() * 400 + 1);

					effectWord.setBounds(x, y, 100, 100);

					groundPanel.add(effectWord);
				}
				if (time % 5 == 2) {
					
					if (stage == 3) {
						groundPanel.remove(findJLabel("하하"));
					} 
					else {
						groundPanel.remove(findJLabel("(ㅇㅂㅇ)"));
					}
				}
			}
		}
	}
	
	private void audio() {
		try {
			clip1 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip3 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			
			File audioFile1 = new File("./fail.wav");
			File audioFile3 = new File("./miss.wav");

	
			AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(audioFile1);
			AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(audioFile3);
			
			clip1.open(audioStream1);
			clip3.open(audioStream3);

		} 
		catch (LineUnavailableException e) {
			e.printStackTrace();
		} 
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();  
		}
	}
}
