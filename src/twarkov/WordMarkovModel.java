package twarkov;

import java.util.*;

public class WordMarkovModel implements MarkovInterface<WordGram> {
	
	protected String[] myWords;
	Map<WordGram,ArrayList<String>> myMap;
	protected Random myRandom;
	protected int myOrder;
	
	public WordMarkovModel(int order){
		myOrder = order;
		myMap = new HashMap<WordGram,ArrayList<String>>();
		myRandom = new Random();
	}

	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+");
		myMap.clear();
		
		int ind = 0;
		WordGram gram = new WordGram(myWords,0,myOrder);
		
		while (ind<myWords.length-myOrder) {
			if (myMap.containsKey(gram)) {
				myMap.get(gram).add(myWords[ind+myOrder]);
			}
			
			if (!myMap.containsKey(gram)) {
				myMap.put(gram, new ArrayList<String>());
				myMap.get(gram).add(myWords[ind+myOrder]);
			}
			
			gram = gram.shiftAdd(myWords[ind+myOrder]);
			ind += 1;
		}
	}

	@Override
	public ArrayList<String> getFollows(WordGram key){
		ArrayList<String> follows = myMap.get(key);
		if (follows == null) {
			throw new NoSuchElementException();
		}
		return follows;
	}
	
	@Override
	public String getRandomText(){
		ArrayList<String> sb = new ArrayList<>();
		int index = myRandom.nextInt(myWords.length - myOrder);
		WordGram current = new WordGram(myWords,index,myOrder);
		
		int charCt = current.toString().length()+1;
		sb.add(current.toString());
		
		while (true) {
			ArrayList<String> follows = getFollows(current);
			if (follows.size() == 0) break;
			index = myRandom.nextInt(follows.size());
			
			String nextItem = follows.get(index);
			
			charCt += nextItem.length() + 1;
			if (charCt>281) break; // exceeds maximum Tweet character count
			
			sb.add(nextItem);
			current = current.shiftAdd(nextItem);
		}
		
		return String.join(" ", sb);
	}
}