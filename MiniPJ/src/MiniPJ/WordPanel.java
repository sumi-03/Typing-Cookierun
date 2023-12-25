package MiniPJ;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordPanel extends JPanel {

	private int number = 0;
	private JTextField input;
	private String inWord;

	public WordPanel(int number) {

		this.number = number;
		this.setLayout(null);

		input = new JTextField();
		
		if (number == 3) {
			
			input.setText("숫자(1~9)+문자(열) 로 입력 요망");
		}
		
		Font font = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 9);
		input.setFont(font);
		
		JButton addIt = new JButton("OK");
		font = new Font("한컴 말랑말랑 Bold", Font.PLAIN, 10);
		addIt.setFont(font);
		
		input.setBounds(10, 50, 150, 20);
		addIt.setBounds(170, 45, 50, 40);
		
		addIt.addActionListener(new AddActionListener());

		add(input);
		add(addIt);
	}

	private class AddActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {

			inWord = input.getText();
			
			if (number == 1) {
				
				try {
					
					if(!inWord.equals("")) {
						
						FileWriter fout = new FileWriter("./words1.txt", true);
						fout.write(inWord);
						fout.write("\r\n");
						fout.close();
						input.setText("저장되었습니다");
					}
					
				} 
				catch (IOException e1) {
					
					return;
				}
			} 
			else if (number == 2) {
				
				try {
					
					if(!inWord.equals("")) {
						
						FileWriter fout = new FileWriter("./words2.txt", true);
						fout.write(inWord);
						fout.write("\r\n");
						fout.close();
						input.setText("저장되었습니다");
					}
				} 
				catch (IOException e1) {
					
					return;
				}
			} 
			else if (number == 3) {

				char firstChar = inWord.charAt(0);
				char secondChar = '0';

				if (inWord.length() > 1) {
					secondChar = inWord.charAt(1);
				}
				int firstInt = inWord.charAt(0) - 48;

				if (inWord.length() <= 1) {

					input.setText("2자리 이상, 숫자(1~9)+문자(열)");
				} 
				else {

					if (Character.isDigit(firstChar) && firstInt != 0 && !Character.isDigit(secondChar)) {

						try {
							
							FileWriter fout = new FileWriter("./words3.txt", true);
							fout.write(inWord);
							fout.write("\r\n");
							fout.close();
							input.setText("저장되었습니다");
						} 
						catch (IOException e1) {
							
							return;
						}
					} 
					else {

						input.setText("숫자(1~9)+문자(열)입력 요망");
					}
				}
			}
		}
	}
}