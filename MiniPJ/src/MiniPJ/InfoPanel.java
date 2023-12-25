package MiniPJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	private int number = -1; 

	private ImageIcon image0_1 = new ImageIcon("info0-1.jpg");
	private ImageIcon image0_2 = new ImageIcon("info0-2.jpg");
	private ImageIcon image1 = new ImageIcon("info1.png");
	private ImageIcon image2 = new ImageIcon("info2.png");
	private ImageIcon image3 = new ImageIcon("info3.png");
	private ImageIcon image4 = new ImageIcon("info-item.png");

	private Image img0_1 = image0_1.getImage();
	private Image img0_2 = image0_2.getImage();
	private Image img1 = image1.getImage();
	private Image img2 = image2.getImage();
	private Image img3 = image3.getImage();
	private Image img4 = image4.getImage();

	public InfoPanel(int number) {

		this.number = number;
		this.setLayout(null);
		
		JLabel text1 = new JLabel();
		JLabel text2 = new JLabel();
		JLabel text3 = new JLabel();
		JLabel text4 = new JLabel();
		JLabel text5 = new JLabel();
		JLabel text6 = new JLabel();
		JLabel text7 = new JLabel();
		JLabel text8 = new JLabel();
		JLabel text9 = new JLabel();
		JLabel text10 = new JLabel();

		Font font = new Font("맑은 고딕", Font.BOLD, 10);

		text1.setForeground(Color.WHITE);
		text2.setForeground(Color.WHITE);
		text3.setForeground(Color.WHITE);
		text4.setForeground(Color.WHITE);
		text5.setForeground(Color.WHITE);
		text6.setForeground(Color.WHITE);
		text7.setForeground(Color.BLACK);
		text8.setForeground(Color.BLUE);
		text9.setForeground(Color.BLUE);
		text10.setForeground(Color.BLUE);

		text1.setFont(font);
		text2.setFont(font);
		text3.setFont(font);
		text4.setFont(font);
		text5.setFont(font);
		text6.setFont(font);
		text7.setFont(font);
		text8.setFont(font);
		text9.setFont(font);
		text10.setFont(font);

		if (number == -1) {

			text1.setText("타이핑쿠키런에 오신것을 환영합니다!!");
			text2.setText("마녀의 오븐을 탈출하려는 계획을 점검하던 당신은");
			text3.setText("펫인 초코방울이 방울감옥에 갇혀버린 사실을 알게되었어요");
			text4.setText("방울감옥의 세 몬스터방울에 맞서 초코방울을 구해주세요!!");
			text5.setText("여러분은 할수 있어요! 용감한쿠키니까요 ^^");

			text1.setBounds(170, 13, 500, 100);
			text2.setBounds(170, 33, 500, 100);
			text3.setBounds(170, 53, 500, 100);
			text4.setBounds(170, 73, 500, 100);
			text5.setBounds(170, 93, 500, 100);

			add(text1);
			add(text2);
			add(text3);
			add(text4);
			add(text5);
		} 
		else if (number == 0) {

			text1.setForeground(Color.BLACK);
			text2.setForeground(Color.BLACK);
			text3.setForeground(Color.BLACK);
			text4.setForeground(Color.BLACK);
			text5.setForeground(Color.BLACK);
			text6.setForeground(Color.BLACK);

			text1.setText("게임 시작에 앞서 몇가지를 알려드릴게요");
			text2.setText("EASY, HARD를 선택하여 게임의 난이도를 조절할 수 있어요");
			text3.setText("HARD 모드일 경우 모든 스테이지에서 ");
			text4.setText("EASY 모드보다 단어가 떨어져 내려오는 속도가 더 빨라요");
			text5.setText("단어 추가를 통해 원하는 단어를 목록에 추가할 수 있고요");
			text6.setText("이름을 입력하고 엔터키를 치면 게임을 시작할수 있어요 ^^");

			text1.setBounds(170, 13, 500, 100);
			text2.setBounds(170, 33, 500, 100);
			text3.setBounds(170, 53, 500, 100);
			text4.setBounds(170, 73, 500, 100);
			text5.setBounds(170, 93, 500, 100);
			text6.setBounds(170, 113, 500, 100);

			add(text1);
			add(text2);
			add(text3);
			add(text4);
			add(text5);
			add(text6);
		} 
		else if (number == 1) {

			text3.setForeground(Color.CYAN);
			text4.setForeground(Color.CYAN);
			text5.setForeground(Color.CYAN);
			text6.setForeground(Color.CYAN);

			text1.setText("STAGE 1 에서는 제대로 단어를 타이핑 할 경우 점수가 5점 증가하고");
			text2.setText("단어가 바닥에 닿거나 잘못 타이핑한 경우 10점이 감점되요");
			text3.setText("단어는 영어일 수도 있고 한국어일 수도 있는데 ");
			text4.setText("바닥으로 떨어지는 단어를 '그대로' 작성해야 해요");
			text5.setText("40점을 넘으면 STAGE 2로 넘어갈 수 있어요 ^^");
			text6.setText("만약 점수가 0점 미만이면 중도탈락이에요 ㅠㅠ");

			text1.setBounds(60, 13, 500, 100);
			text2.setBounds(60, 33, 500, 100);
			text3.setBounds(60, 53, 500, 100);
			text4.setBounds(60, 73, 500, 100);
			text5.setBounds(60, 93, 500, 100);
			text6.setBounds(60, 113, 500, 100);

			add(text1);
			add(text2);
			add(text3);
			add(text4);
			add(text5);
			add(text6);
		} 
		else if (number == 2) {

			text3.setForeground(Color.CYAN);
			text4.setForeground(Color.CYAN);
			text5.setForeground(Color.CYAN);
			text6.setForeground(Color.CYAN);

			text1.setText("STAGE 2 에서는 제대로 단어를 타이핑 할 경우 점수가 10점 증가하고");
			text2.setText("단어가 바닥에 닿거나 잘못 타이핑한 경우 20점이 감점되요");
			text3.setText("단어는 영어일 수도 있고 한국어일 수도 있는데 ");
			text4.setText("바닥으로 떨어지는 단어를 '거꾸로' 작성해야 해요");
			text5.setText("30초 후 STAGE 3으로 넘어갈 수 있어요 ^^");
			text6.setText("스테이지마다 점수는 새로 계산되고 0점 미만이면 중도탈락이에요 ㅠㅠ");

			text1.setBounds(60, 13, 500, 100);
			text2.setBounds(60, 33, 500, 100);
			text3.setBounds(60, 53, 500, 100);
			text4.setBounds(60, 73, 500, 100);
			text5.setBounds(60, 93, 500, 100);
			text6.setBounds(60, 113, 500, 100);

			add(text1);
			add(text2);
			add(text3);
			add(text4);
			add(text5);
			add(text6);
		} 
		else if (number == 3) {

			text3.setForeground(Color.CYAN);
			text4.setForeground(Color.CYAN);
			text5.setForeground(Color.CYAN);
			text6.setForeground(Color.CYAN);

			text1.setText("STAGE 3 에서는 제대로 단어를 타이핑 할 경우 점수가 10점 증가하고");
			text2.setText("단어가 바닥에 닿거나 잘못 타이핑한 경우 30점이 감점되요");
			text3.setText("단어의 내용은 숫자 + 문자(열)에요");
			text4.setText("'10에서 숫자를 뺀 숫자'와 '단어의 색상'을 작성하여야해요");
			text5.setText("점수가 0점 미만이면 중도탈락이에요 ㅠㅠ");
			text6.setText("30초 후 게임이 끝나고 합산된 점수를 확인할 수 있어요");

			text1.setBounds(60, 5, 500, 100);
			text2.setBounds(60, 25, 500, 100);
			text3.setBounds(60, 90, 500, 100);
			text4.setBounds(60, 110, 500, 100);
			text5.setBounds(60, 130, 500, 100);
			text6.setBounds(60, 150, 500, 100);

			add(text1);
			add(text2);
			add(text3);
			add(text4);
			add(text5);
			add(text6);
		} 
		else if (number == 4) {

			text1.setForeground(Color.PINK);
			text2.setForeground(Color.BLACK);
			text3.setForeground(Color.BLACK);
			text4.setForeground(Color.BLUE);
			text5.setForeground(Color.PINK);
			text6.setForeground(Color.BLACK);

			text1.setText("(ㅇㅂㅇ)");
			text2.setText("STAGE 1에서의 효과 : 점수 1~10점 랜덤 증가");
			text3.setText("STAGE 2에서의 효과 : 점수 1~20점 랜덤 증가");
			text4.setText("나타나있는 동안 여러번 타이핑해도 되요");
			text5.setText("하하");
			text6.setText("STAGE 3에서의 효과 :");
			text7.setText("타이핑 시점의 모든 단어의 색을 black 혹은 white 로 변경");
			text8.setText("나타나있는 동안 여러번 타이핑해도 되요");
			text9.setText("STAGE 3의 배경색이 어두우니");
			text10.setText("black으로 글자색이 바뀐다면 게임이 더 어려워지겠죠?");

			text1.setBounds(60, -23, 500, 100);
			text2.setBounds(60, -3, 500, 100);
			text3.setBounds(60, 17, 500, 100);
			text4.setBounds(60, 37, 500, 100);
			text5.setBounds(60, 65, 500, 100);
			text6.setBounds(60, 85, 500, 100);
			text7.setBounds(60, 105, 500, 100);
			text8.setBounds(60, 125, 500, 100);
			text9.setBounds(60, 145, 500, 100);
			text10.setBounds(60, 165, 500, 100);

			add(text1);
			add(text2);
			add(text3);
			add(text4);
			add(text5);
			add(text6);
			add(text7);
			add(text8);
			add(text9);
			add(text10);
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		if (number == -1) {
			
			super.paintComponent(g);
			g.drawImage(img0_1, 0, 0, 500, 300, this);
		} 
		else if (number == 0) {
			
			super.paintComponent(g);
			g.drawImage(img0_2, 0, 0, 500, 300, this);
		} 
		else if (number == 1) {
			
			super.paintComponent(g);
			g.drawImage(img1, 0, 0, 500, 300, this);
		} 
		else if (number == 2) {
			
			super.paintComponent(g);
			g.drawImage(img2, 0, 0, 500, 300, this);
		} 
		else if (number == 3) {
			
			super.paintComponent(g);
			g.drawImage(img3, 0, 0, 500, 300, this);
		} 
		else if (number == 4) {
			super.paintComponent(g);
			g.drawImage(img4, 0, 0, 500, 300, this);
		}
	}
}
