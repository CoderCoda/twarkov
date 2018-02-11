package twarkov;


public class WordGram implements Comparable<WordGram>{
	
	private String[] myWords;
	private int myHash;
	
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size];
		int ind = 0;
		for (int i=index; i<index+size; i++) {
			myWords[ind] = words[i];
			ind += 1;
		}
		hashCode();
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (int i=0; i<myWords.length; i++) {
			hash += myWords[i].hashCode();
			hash = hash*10;
		}
		myHash = hash;
		return myHash;
	}
	
	@Override
	public String toString() {
		return String.join(" ", myWords);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram) other;
		for (int i=0; i<wg.myWords.length; i++) {
			if (!wg.myWords[i].equals(this.myWords[i])) return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(WordGram wg) {
		int ret = 0;
		if (this.myWords.length>wg.myWords.length) return 1;
		if (this.myWords.length<wg.myWords.length) return -1;
		for (int i=0; i<wg.myWords.length; i++) {
			if (!wg.myWords[i].equals(this.myWords[i])) ret = this.myWords[i].compareTo(wg.myWords[i]);
		}
		return ret;
	}
	
	public int length() {
		return myWords.length;
	}
	
	public WordGram shiftAdd(String last) {
		String[] myWords2 = new String[myWords.length];
		for (int i=1; i<myWords.length; i++) {
			myWords2[i-1] = myWords[i];
		}
		myWords2[myWords.length-1] =  last;
		return new WordGram(myWords2,0,myWords.length);
	}
}