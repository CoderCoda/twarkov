package twarkov;

import java.util.*;
import twitter4j.*;

public class WordMarkovDriver {
	
	public static void main(String[] args) throws TwitterException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a Twitter username: ");
		String input = sc.nextLine().trim();
		
		TwitterAPI trainer = new TwitterAPI(input);
		String text = trainer.getTimeline();
		
		MarkovInterface<WordGram> markov = new WordMarkovModel(2);
		markov.setTraining(text);
		String random = markov.getRandomText();
		System.out.println('@' + input + " would tweet:\n");
		printNicely(random,60);
		sc.close();
	}

	private static void printNicely(String random, int screenWidth) {
		String[] words = random.split("\\s+");
		int psize = 0;
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > screenWidth) {
				System.out.println();
				psize = 0;
			}
		}
	}
}