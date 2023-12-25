package MiniPJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {

	private boolean easyHard;
	private String userName;

	private ImageIcon image1 = new ImageIcon("background1-0.jpg");
	private ImageIcon image2 = new ImageIcon("background2-0.jpg");
	private ImageIcon image3 = new ImageIcon("background3-0.jpg");
	private ImageIcon image4 = new ImageIcon("background4-0.jpg");

	private Image img1 = image1.getImage();
	private Image img2 = image2.getImage();
	private Image img3 = image3.getImage();
	private Image img4 = image4.getImage();

	private GamePanel pjgp;
	private GameGroundPanel gp = null;
	private InputPanel1 ip = null;

	private JTextField input = null;
	private RemainTimePanel remainTime = null;

	private int next = 0;
	private ChangeStage1 change1 = null;
	private ChangeStage2 change2 = null;
	private ChangeStage3 change3 = null;
	private FinalView finalView = null;

	private Timer gameTimer = null;
	private TimerTask timerTask = null;
	private int timeCheck = 30;

	private int stage = 1;
	private int score = 0;

	private Clip clip1;
	private Clip clip2;
	private Clip clip3;
	private Clip clip4;
	private Clip clip5;
	private Clip clip6;
	private Clip clip7;

	private Color colorArray[] = { Color.red, Color.black, Color.white, Color.yellow, Color.green, Color.blue,
			Color.gray, Color.pink };

	public GamePanel(boolean easyHard, String userName) {

		pjgp = this;
		this.easyHard = easyHard;
		this.userName = userName;

		audio(); 
		
		change1 = new ChangeStage1();
		change1.start();
	

		change2 = new ChangeStage2();
		change3 = new ChangeStage3();
		finalView = new FinalView();
	}

	private class Stop extends Thread { // 스테이지1,2,3 실패시 실행되는 스레드
		@Override
		public void run() {

			try {
				sleep(1700);
				System.exit(0);

			} 
			catch (InterruptedException e) {

				return;
			}
		}
	}

	private class ChangeStage1 extends Thread {

		@Override
		public void run() {

			try {
				sleep(1700);
				pjgp.setLayout(new BorderLayout());
				
				clip5.loop(Clip.LOOP_CONTINUOUSLY);
				
				gp = new GameGroundPanel(pjgp, stage, easyHard);
				ip = new InputPanel1();

				add(gp, BorderLayout.CENTER);
				add(ip, BorderLayout.SOUTH);

				pjgp.revalidate();
				pjgp.repaint();
				input.requestFocus();

				while (!isInterrupted()) { // 단어가 바닥에 닿는 경우 고려

					int tmp = gp.modifyScore(0);

					if (tmp < 0) {

						pjgp.removeAll();

						next = 2;

						pjgp.revalidate();
						pjgp.repaint();
					}
				}

			} 
			catch (InterruptedException e) {

				return;
			}

		}
	}

	private class ChangeStage2 extends Thread {

		@Override
		public void run() {

			try {
				sleep(1700);

				// 스테이지1을 통과하고 ChangeStage2 스레드가 실행된경우
				next = 3;
				pjgp.revalidate();
				pjgp.repaint();

				sleep(1700);

				pjgp.setLayout(new BorderLayout());

				stage++;
				clip6.start();
				
				gp = new GameGroundPanel(pjgp, stage, easyHard);
				ip = new InputPanel1();

				add(gp, BorderLayout.CENTER);//
				add(ip, BorderLayout.SOUTH);//

				remainTime = new RemainTimePanel();
				gp.add(remainTime);

				pjgp.revalidate();
				pjgp.repaint();//
				input.requestFocus();

				gameTimer = new Timer();
				timerTask = new GameTimerTask(2);
				gameTimer.schedule(timerTask, 0, 1000);

				while (!isInterrupted()) { // 단어가 바닥에 닿는 경우 고려

					int tmp = gp.modifyScore(0);

					if (tmp < 0) {

						pjgp.removeAll();

						next = 2;

						pjgp.revalidate();
						pjgp.repaint();
					}
				}

			} 
			catch (InterruptedException e) {

				return;
			}
		}
	}

	private class ChangeStage3 extends Thread {
		@Override
		public void run() {

			try {

				sleep(1700);

				// 스테이지2을 통과하고 ChangeStage3 스레드가 실행된경우
				next = 5;
				pjgp.revalidate();
				pjgp.repaint();

				sleep(1900);

				pjgp.setLayout(new BorderLayout());

				stage++;
				clip7.start();
				gp = new GameGroundPanel(pjgp, stage, easyHard);
				ip = new InputPanel1();

				add(gp, BorderLayout.CENTER);//
				add(ip, BorderLayout.SOUTH);//

				remainTime = new RemainTimePanel();
				gp.add(remainTime);

				pjgp.revalidate();
				pjgp.repaint();//
				input.requestFocus();

				timeCheck = 30;
				gameTimer = new Timer();
				timerTask = new GameTimerTask(3);
				gameTimer.schedule(timerTask, 0, 1000);

				while (!isInterrupted()) { // 단어가 바닥에 닿는 경우 고려

					int tmp = gp.modifyScore(0);

					if (tmp < 0) {

						pjgp.removeAll();

						next = 2;

						pjgp.revalidate();
						pjgp.repaint();
					}
				}

			} 
			catch (InterruptedException e) {

				return;
			}
		}

	}

	private class FinalView extends Thread { // 스테이지3 까지 완료 후 실행되는 스레드

		@Override
		public void run() {

			try {

				sleep(1700);
				next = 7;

				saveScore();

				pjgp.revalidate();
				pjgp.repaint();

				sleep(3000);
				System.exit(0);
			} 
			catch (InterruptedException e) {

				return;
			}
		}
	}

	private class GameTimerTask extends TimerTask {

		private int stageNumber;

		public GameTimerTask(int stageNumber) {
			this.stageNumber = stageNumber;
		}

		@Override
		public void run() {

			remainTime.modifyRemain(timeCheck);
			timeCheck--;

			if (next != 2 && timeCheck == -1) {

				pjgp.removeAll();

				if (stageNumber == 2) {
					
					clip2.setFramePosition(0);
					clip2.start();
					
					next = 4;
				} 
				else if (stageNumber == 3) {

					clip2.setFramePosition(0);
					clip2.start();
					
					next = 6;
				}

				score += gp.modifyScore(0); // 점수를 저장해둠

				pjgp.revalidate();
				pjgp.repaint();

				gameTimer.cancel();
			}
		}

	};

	@Override
	public void paintComponent(Graphics g) {

		if (next == 0) {

			super.paintComponent(g);
			g.drawImage(img1, 0, 0, 1000, 600, this);
		} 
		else if (next == 1) { // 스테이지1 통과시 성공화면

			super.paintComponent(g);

			Font f = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 50);
			g.setFont(f);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 600);
			g.setColor(Color.WHITE);
			g.drawString("Good !!!", 430, 280);

			change1.interrupt();
			clip5.stop();
			gp.word.interrupt();
			gp.word2.interrupt();
	
			change2.start();

		} 
		else if (next == 2) { // 스테이지1,2,3 실패시 화면

			super.paintComponent(g);

			Font f = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 50);
			g.setFont(f);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 600);
			g.setColor(Color.WHITE);
			g.drawString("failed..", 430, 260);

			f = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 30);
			g.setFont(f);

			g.drawString("중도탈락으로 점수가 기록되지 않습니다", 280, 310);

			if (stage == 1) {

				change1.interrupt();
				clip5.stop();
			} else if (stage == 2) {

				change2.interrupt();
				clip6.stop();
				
			} else if(stage == 3) {
				change3.interrupt();
				clip7.stop();
			}

			gp.word.interrupt();
			gp.word2.interrupt();

			Stop stop = new Stop();
			stop.start();

		} 
		else if (next == 3) { // 스테이지2 진입시 화면

			super.paintComponent(g);
			g.drawImage(img2, 0, 0, 1000, 600, this);
		} 
		else if (next == 4) { // 스테이지2 성공시 그려지는 화면

			super.paintComponent(g);

			Font f = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 50);
			g.setFont(f);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 600);
			g.setColor(Color.WHITE);
			g.drawString("Good !!!", 430, 280);

			change2.interrupt();
			clip6.stop();
			gp.word.interrupt();
			gp.word2.interrupt();
	
			change3.start();

		} 
		else if (next == 5) { // 스테이지3 진입시 화면

			super.paintComponent(g);
			g.drawImage(img3, 0, 0, 1000, 600, this);
		} 
		else if (next == 6) { // 스테이지3 까지 다 완료한 후의 화면

			Font f = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 50);
			g.setFont(f);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 600);
			g.setColor(Color.WHITE);
			g.drawString("ALL PASS !!!", 380, 280);

			change3.interrupt();
			clip7.stop();
			gp.word.interrupt();
			gp.word2.interrupt();

			finalView.start();
		} 
		else if (next == 7) { // 점수 공개 등을 위한 화면의 배경화면

			super.paintComponent(g);
			g.drawImage(img4, 0, 0, 1000, 600, this); // 원래 이미지

			Font f = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 50);
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("Your score is...", 270, 280);
			g.drawString(Integer.toString(score), 550, 350);
		}
	}

	private void saveScore() {

		FileWriter fout;

		try {

			fout = new FileWriter("./userfile.txt", true);
			fout.write(userName);
			fout.write("\r\n");
			fout.write(Integer.toString(score));
			fout.write("\r\n");
			fout.write(Boolean.toString(easyHard));
			fout.write("\r\n");
			fout.close();
		} 
		catch (IOException e) {

			return;
		}

	}

	private class InputPanel1 extends JPanel {

		public InputPanel1() {

			this.setBackground(Color.LIGHT_GRAY);
			input = new JTextField(40);

			input.addActionListener(new MyActionListener());
			add(input);
		}

		private class MyActionListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				JTextField t = (JTextField) (e.getSource());
				String inWord = t.getText(); // inword는 사용자가 입력한 문자열
				int check = 0;

				input.setText("");

				Component[] components = gp.getComponents();

				for (Component component : components) {

					if (component instanceof JLabel) {

						String content = ((JLabel) component).getText();

						if (stage == 1) {

							if (inWord.equals(content)) {

								gp.remove(component);
								gp.revalidate();
								gp.repaint();
								check = 1;
							}
						} 
						else if (stage == 2) {

							if (inWord.equals("(ㅇㅂㅇ)")) {
								check = 1;
							} 
							else {
								StringBuffer sb = new StringBuffer(inWord).reverse();

								if (sb.toString().equals(content)) {

									gp.remove(component);
									gp.revalidate();
									gp.repaint();
									check = 1;
								}
							}

						} 
						else if (stage == 3) {

							char userFirstChar = inWord.charAt(0);
							char wordFirstChar = content.charAt(0);

							// 사용자가 입력한 글자의 첫번째 수가 숫자라면, 확인하려는 단어의 첫번째 수가 숫자라면
							if (Character.isDigit(userFirstChar) && Character.isDigit(wordFirstChar)) {

								int userNumber = Integer.parseInt(String.valueOf(userFirstChar));
								int wordNumber = Integer.parseInt(String.valueOf(wordFirstChar));

								int colorIs = -1;

								String userColorStr = inWord.substring(1);

								Color textColor = component.getForeground();
								String textColorStr = "";

								for (int i = 0; i < colorArray.length; i++) {

									if (textColor.equals(colorArray[i])) {

										colorIs = i;
										break;
									}
								}

								switch (colorIs) {

								case 0:
									textColorStr = "red";
									break;

								case 1:
									textColorStr = "black";
									break;

								case 2:
									textColorStr = "white";
									break;

								case 3:
									textColorStr = "yellow";
									break;

								case 4:
									textColorStr = "green";
									break;

								case 5:
									textColorStr = "blue";
									break;

								case 6:
									textColorStr = "gray";
									break;

								case 7:
									textColorStr = "pink";
									break;

								}

								if (userNumber == (10 - wordNumber) && userColorStr.equals(textColorStr)) {

									gp.remove(component);
									gp.revalidate();
									gp.repaint();
									check = 1;
								}
							} 
							else {

								if (inWord.equals("하하") && content.equals("하하")) {
									check = 1;
								}
							}
						}
					}
				}
				if (check == 1) { // 단어를 맞췄다면

					int tmp = 0;

					if (stage == 1) { // 스테이지1 의 경우

						if (inWord.equals("(ㅇㅂㅇ)")) {

							tmp = gp.modifyScore((int) (Math.random() * 10) + 1);
						} 
						else {

							tmp = gp.modifyScore(5);
						}

						if (tmp >= 40) { // 스테이지1 통과 점수를 넘으면

							pjgp.removeAll();

							clip2.start();
							
							next = 1;

							// 리페인트하기 전에, 스레드1 죽이기전에
							score += gp.modifyScore(0); // 점수를 저장해둠 나중에 합산할때 필요

							pjgp.revalidate();
							pjgp.repaint();

						}
						else {
							clip4.setFramePosition(0);
							clip4.start();
						}
					} 
					else if (stage == 2) { // 스테이지2,3 의 경우 30초 후 자동으로 넘어가므로 일정점수를 넘는지 확인할 필요 x
						
						clip4.setFramePosition(0);
						clip4.start();
						
						if (inWord.equals("(ㅇㅂㅇ)")) {
							
							tmp = gp.modifyScore((int) (Math.random() * 20) + 1);
						} 
						else {

							tmp = gp.modifyScore(10); // 점수 10점 증가
						}
					} 
					else if (stage == 3) { // 스테이지3 의 경우
						
						clip4.setFramePosition(0);
						clip4.start();
						
						if (inWord.equals("하하")) {

							int index = (int) (Math.random() * 2) + 1;

							Component[] components2 = gp.getComponents();

							for (Component component2 : components2) {

								if (component2 instanceof JLabel) {

									((JLabel) component2).setForeground(colorArray[index]);
								}
							}

						} 
						else {

							tmp = gp.modifyScore(10); // 점수 10점 증가
						}

					}

				} 
				else if (check == 0) { // 단어를 맞추지 못했다면

					int tmp = 0;

					if (stage == 1) { // 스테이지1 은 10점 감점

						tmp = gp.modifyScore(-10);
					} 
					else if (stage == 2) { // 스테이지2 는 20점 감점

						tmp = gp.modifyScore(-20);
					} 
					else if (stage == 3) { // 스테이지3 은 30점 감점

						tmp = gp.modifyScore(-30);
					}
					
					if (tmp < 0) {

						pjgp.removeAll();

						clip1.setFramePosition(0);
						clip1.start();
						
						next = 2;
						pjgp.revalidate();
						pjgp.repaint();
					}
					else {
						
						clip3.setFramePosition(0);
						clip3.start();
					}
				}
			}
		}
	}

	private void audio() {
		try {
			clip1 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip2 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip3 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip4 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			
			clip5 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip6 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip7 = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			
			File audioFile1 = new File("./fail.wav");
			File audioFile2 = new File("./pass.wav");
			File audioFile3 = new File("./miss.wav");
			File audioFile4 = new File("./ok.wav");
			
			File audioFile5 = new File("./stage1.wav");
			File audioFile6 = new File("./stage2.wav");
			File audioFile7 = new File("./stage3.wav");
	
			AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(audioFile1);
			AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
			AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(audioFile3);
			AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(audioFile4);
			
			AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(audioFile5);
			AudioInputStream audioStream6 = AudioSystem.getAudioInputStream(audioFile6);
			AudioInputStream audioStream7 = AudioSystem.getAudioInputStream(audioFile7);
			
			clip1.open(audioStream1);
			clip2.open(audioStream2);
			clip3.open(audioStream3);
			clip4.open(audioStream4);
			
			clip5.open(audioStream5);
			clip6.open(audioStream6);
			clip7.open(audioStream7);
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