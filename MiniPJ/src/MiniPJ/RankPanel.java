package MiniPJ;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RankPanel extends JPanel {

	private Vector<String> userVector = new Vector<String>();
	private HashMap<Integer, Integer> userScore = new HashMap<Integer, Integer>();

	private int scoreArray[];
	private JLabel ranking[] = new JLabel[10];

	public RankPanel() {

		this.setLayout(null);
		
		int lo = 5;
		
		for (int i = 0; i < ranking.length; i++) {
			
			ranking[i] = new JLabel(Integer.toString(i + 1) + "등 : ");
			ranking[i].setBounds(20, lo, 400, 20);
			ranking[i].setForeground(Color.BLACK);
			
			lo += 20;
		}

		readFile();
		makeRank();

		for (int i = 0; i < ranking.length; i++) {
			
			add(ranking[i]);
		}

	}

	private void readFile() { // words.txt 파일 읽기
		
		try {
			
			Scanner scanner = new Scanner(new File("./userfile.txt"));
			
			while (scanner.hasNext()) {
				
				userVector.add(scanner.nextLine()); // 한 라인에 하나의 단어
			}
			scanner.close();

		} 
		catch (FileNotFoundException e) {
			
			return;
		}

	}

	private void makeRank() {

		scoreArray = new int[userVector.size() / 3];
		
		int k = 0;
		
		for (int i = 1; i < userVector.size(); i += 3) {
			
			int score = Integer.parseInt(userVector.get(i));
			userScore.put(score, i);
			scoreArray[k] = score;
			
			k++;
		}
		
		Arrays.sort(scoreArray); // 일단 배열 오름차순으로 정렬하고

		int length = scoreArray.length;

		for (int i = 0; i < length / 2; i++) { // 내림차순으로 다시 정렬
			
			int tmp = scoreArray[i];
			scoreArray[i] = scoreArray[length - i - 1];
			scoreArray[length - i - 1] = tmp;
		}

		int size;

		if (userVector.size() >= 30) { // 10명 이상이면 TOP 10까지
			
			size = 10;
		} 
		else {
			
			size = userVector.size() / 3; // 아니라면 있는사람들 수 만큼만
		}

		for (int i = 0; i < size; i++) {
			
			String mode = userVector.get(userScore.get(scoreArray[i]) + 1); // true 이면 EASY 모드
			
			if (mode.equals("true")) {
				
				mode = "EASY 모드";
			} 
			else {
				
				mode = "HARD 모드";
			}
			
			String name = userVector.get(userScore.get(scoreArray[i]) - 1); // 이름
			String score = Integer.toString(scoreArray[i]);

			ranking[i].setText(Integer.toString(i + 1) + "등 : " + name + " - " + mode + " - " + score);
		}
	}
}
