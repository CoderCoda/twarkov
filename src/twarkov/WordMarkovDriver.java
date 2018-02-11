package twarkov;

import twitter4j.*;

public class WordMarkovDriver {
	public static void main(String[] args) throws TwitterException {
		String test = "neiltyson";
		
		TwitterAPI trainer = new TwitterAPI(test);
		String text = trainer.getTimeline();
		
		MarkovInterface<WordGram> markov = new WordMarkovModel(2);
		markov.setTraining(text);
		String random = markov.getRandomText(50);
		printNicely(random,60);
	}

	private static void printNicely(String random, int screenWidth) {
		String[] words = random.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > screenWidth) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
}