package twarkov;

import java.util.ArrayList;

/**
 * Note that the order of the Markov model would be typically set via a constructor
 * @param <Type> should be String or WordGram
 */

public interface MarkovInterface<Type> {
	
	/**
	 * Set the training text for subsequent random text generation.
	 * @param text is the training text
	 */
	public void setTraining(String text);
	
	/**
	 * Get randomly generated text based on the training text
	 * and order.
	 * @return randomly generated text 
	 */
	public String getRandomText();
	
	/**
	 * Really a helper method, but must be public to be part of interface. Used
	 * to get all the characters or strings that follow a key. Returns an
	 * ArrayList of the following items. 
	 * @param key is key being searched for in training text
	 * @return a list of items that follow key: single-character strings
	 * if <Type> is String and Strings if <Type> is WordGram
	 */
	public ArrayList<String> getFollows(Type key);
}