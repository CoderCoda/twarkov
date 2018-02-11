package twarkov;

import java.util.*;

public class WordMarkovModel implements MarkovInterface<WordGram> {
	
	protected String[] myWords;
	Map<WordGram,ArrayList<String>> myMap;
	protected Random myRandom;
	protected int myOrder;
	protected static String PSEUDO_EOS = "";
	protected static long RANDOM_SEED = 1234;
	
	public WordMarkovModel(int order){
		myOrder = order;
		myMap = new HashMap<WordGram,ArrayList<String>>();
		myRandom = new Random(RANDOM_SEED);
	}

	
	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+");
		//myMap.clear();
		
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
		
		if (myMap.containsKey(gram)) {
			myMap.get(gram).add(PSEUDO_EOS);
		}
		
		if (!myMap.containsKey(gram)) {
			myMap.put(gram, new ArrayList<String>());
			myMap.get(gram).add(PSEUDO_EOS);
		}
	}

	
	public ArrayList<String> getFollows(WordGram key){
		ArrayList<String> follows = myMap.get(key);
		if (follows == null) {
			throw new NoSuchElementException();
		}
		return follows;
	}
	

	public String getRandomText(int length){
		ArrayList<String> sb = new ArrayList<>();
		int index = myRandom.nextInt(myWords.length - myOrder);
		WordGram current = new WordGram(myWords,index,myOrder);
		
		sb.add(current.toString());
		for(int k=0; k < length-myOrder; k++){
			ArrayList<String> follows = getFollows(current);
			if (follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			
			String nextItem = follows.get(index);
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
			}
			sb.add(nextItem);
			current = current.shiftAdd(nextItem); //current.substring(1)+ nextItem;
		}
		return String.join(" ", sb);
	}
	
	
	@Override
	public int getOrder() {
		return myOrder;
	}
	
	@Override
	public void setSeed(long seed) {
		myRandom.setSeed(seed);
	}
}