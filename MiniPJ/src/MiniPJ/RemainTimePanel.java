package MiniPJ;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemainTimePanel extends JPanel {

	private JLabel remain = new JLabel("30");

	public RemainTimePanel() {

		this.setBounds(30, 30, 50, 20);
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		remain.setSize(50, 20);
		remain.setForeground(Color.YELLOW);
		remain.setLocation(20, 0);
		this.add(remain);

	}

	public void modifyRemain(int i) {

		remain.setText(String.valueOf(i));

	}
}
