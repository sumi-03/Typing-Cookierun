package MiniPJ;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class TextSource {

	private Vector<String> wordVector = new Vector<String>(30000);

	public TextSource(int stage) {
		
		try {
			
			if (stage == 1) {
				
				Scanner scanner = new Scanner(new FileReader("words1.txt"));
				
				while (scanner.hasNext()) {
					
					String word = scanner.nextLine();
					wordVector.add(word);
				}
				
				scanner.close();
			} 
			else if (stage == 2) {
				
				Scanner scanner = new Scanner(new FileReader("words2.txt"));
				
				while (scanner.hasNext()) {
					
					String word = scanner.nextLine();
					wordVector.add(word);
				}
				
				scanner.close();
			}

			else if (stage == 3) {
				
				Scanner scanner = new Scanner(new FileReader("words3.txt"));
				
				while (scanner.hasNext()) {
					
					String word = scanner.nextLine();
					wordVector.add(word);
				}
				
				scanner.close();
			}

		} 
		catch (FileNotFoundException e) {

			System.out.println("파일 없어요");

			System.exit(0);
		}
	}

	public String next(int index) {

		return wordVector.get(index);
	}

	public int size() {
		
		return wordVector.size();
	}
}
