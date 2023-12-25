package MiniPJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class GameFrame extends JFrame {

	private boolean easyHard = true;

	private JTextField name = new JTextField(7);
	private String userName;

	private ImageIcon image = new ImageIcon("background0.jpg");
	private ImageIcon easy = new ImageIcon("easybt.png");
	private ImageIcon hard = new ImageIcon("hardbt.png");
	private ImageIcon addWord = new ImageIcon("addWord.png");
	private ImageIcon ranking = new ImageIcon("rank.png");
	private ImageIcon explain = new ImageIcon("info.png");

	private Image img = image.getImage();

	private InfoDialog infoDia = null;
	private WordDialog wordDia = null;
	private RankDialog rankDia = null;
	
	private Clip clip = null;

	public GameFrame() {
		
		setSize(1000, 600);

		setContentPane(new StartPanel()); // StartPanel 의 객체를 생성하여 컨텐트팬으로 설정
		
		// 다이얼로그 생성
		infoDia = new InfoDialog(this, "게임 안내"); 
		wordDia = new WordDialog(this, "단어 추가");
		rankDia = new RankDialog(this, "TOP 10");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 오디오를 재생하기 위한 준비, 게임 시작에 들어가기 전까지 배경음악 무한 반복
		audio();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		setResizable(false);
		setVisible(true);

	}
 
	private class StartPanel extends JPanel {

		public StartPanel() {
			
			this.setLayout(null);

			JButton mode = new JButton(easy); // 이지/하드모드를 나타내는 버튼 생성
			mode.setBorderPainted(false); // 테두리를 그리지 않음
			mode.setFocusPainted(false); // 포커스를 얻었을 때 외형을 그리지 않음
			mode.setBounds(670, 420, 100, 55);
			mode.addActionListener(new ModeActionListener()); // 액션리스너 달기
			add(mode); // 패널에 추가

			JButton info = new JButton(explain); // 게임안내 창을 띄우는 버튼 생성
			info.setBorderPainted(false); // 테두리를 그리지 않음
			info.setFocusPainted(false); // 포커스를 얻었을 때 외형을 그리지 않음
			info.setBounds(720, 475, 80, 80);
			info.addActionListener(new InfoActionListener()); // 액션리스너 달기
			add(info); // 패널에 추가

			JButton word = new JButton(addWord);
			word.setBorderPainted(false); //
			word.setFocusPainted(false);//
			word.setBounds(770, 430, 70, 40);
			word.addActionListener(new WordActionListener());
			add(word);

			JButton rank = new JButton(ranking);
			rank.setBorderPainted(false); //
			rank.setFocusPainted(false);//
			rank.setBounds(675, 470, 50, 30);
			rank.addActionListener(new RankActionListener());
			add(rank);

			name.setBounds(670, 400, 170, 20);
			add(name);
			
			name.addActionListener(new NameActionListener());

		}

		private class InfoActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				
				infoDia.setResizable(false);
				infoDia.setVisible(true);
			}
		}

		private class NameActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				
				JTextField t = (JTextField) (e.getSource());
				userName = t.getText();
				
				if(!userName.equals("")) {
					
					// 메인음악멈추기
					clip.stop();
					setContentPane(new GamePanel(easyHard, userName));
					getContentPane().revalidate();
					getContentPane().repaint();
				}
				

			}
		}

		private class ModeActionListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				
				JButton b = (JButton) (e.getSource());
				
				if (easyHard) {
					
					b.setIcon(hard);
					easyHard = false;
				} 
				else {
					
					b.setIcon(easy);
					easyHard = true;
				}
			}
		}

		private class RankActionListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
				rankDia.setResizable(false);
				rankDia.setVisible(true);
			}
		}

		private class WordActionListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
				wordDia.setResizable(false);
				wordDia.setVisible(true);
			}
		}

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); 
		}

	}

	private class InfoDialog extends JDialog {

		private JTabbedPane pane = createTabbedPane();

		public InfoDialog(JFrame frame, String title) {

			super(frame, title, false); // 모달리스 타입으로 지정
			setLayout(new BorderLayout());
			setSize(500, 300);
			add(pane, BorderLayout.CENTER);

		}

		JTabbedPane createTabbedPane() {
			
			JTabbedPane pane = new JTabbedPane();
			
			pane.addTab("하고싶은 말", new InfoPanel(-1));
			pane.addTab("게임 시작 전", new InfoPanel(0));
			pane.addTab("STAGE 1", new InfoPanel(1));
			pane.addTab("STAGE 2", new InfoPanel(2));
			pane.addTab("STAGE 3", new InfoPanel(3));
			pane.addTab("아이템", new InfoPanel(4));

			return pane;
		}
	}

	private class WordDialog extends JDialog {

		private JTabbedPane pane = createTabbedPane();

		public WordDialog(JFrame frame, String title) {

			super(frame, title, false);
			setLayout(new BorderLayout());
			setSize(250, 200);
			add(pane, BorderLayout.CENTER);

		}

		JTabbedPane createTabbedPane() {
			
			JTabbedPane pane = new JTabbedPane();

			pane.addTab("STAGE 1", new WordPanel(1));
			pane.addTab("STAGE 2", new WordPanel(2));
			pane.addTab("STAGE 3", new WordPanel(3));

			return pane;
		}
	}

	private class RankDialog extends JDialog {

		public RankDialog(JFrame frame, String title) {

			super(frame, title, false);
			setLayout(new BorderLayout());
			setSize(420, 250);
			add(new RankPanel(), BorderLayout.CENTER);

		}
	}
	private void audio() {
		try {
			clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			
			// 오디오 데이터를 읽을 오디오 스트림 객체 생성
			File audioFile = new File("./start.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	
			// 오디오 클립과 오디오 스트림 연결
			clip.open(audioStream);

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
