package MiniPJ;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

	private JPanel scorePanel = null;
	private int score = 0;
	private JLabel textLabel = new JLabel("점수");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));

	public ScorePanel() {

		scorePanel = this;

		this.setBounds(820, 480, 120, 20);
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		textLabel.setSize(50, 20);
		textLabel.setForeground(Color.RED);
		textLabel.setLocation(10, 0);
		this.add(textLabel);

		scoreLabel.setSize(50, 20);
		scoreLabel.setLocation(70, 0);
		scoreLabel.setForeground(Color.RED);
		this.add(scoreLabel);
 
	}

	public void modify(int n) {

		score += n;
		scoreLabel.setText(Integer.toString(score));

		scorePanel.repaint();
		scorePanel.revalidate();

	}

	public int getScore() {

		return Integer.valueOf(scoreLabel.getText());
	}
}
