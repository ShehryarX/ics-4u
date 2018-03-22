package Logic;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;



public class Dictionary {

	private HashMap<Integer, List<String>> wordsMap = new HashMap<>();

    public Dictionary() throws FileNotFoundException{
    	Scanner scan = new Scanner(new File("words\\words_en.txt"));
    	int size = scan.nextInt();

        for(int i = 0; i < size; i++) {
            String word = scan.next();
            if(word.length() > 1) {
                if (this.wordsMap.get(word.length()) != null) {
                    this.wordsMap.get(word.length()).add(word);
                } else {
                    ArrayList<String> newList = new ArrayList<>();
                    newList.add(word);
                    this.wordsMap.put(word.length(), newList);
                }
            }
        }
    }
	public Dictionary(String FileUrl) throws FileNotFoundException{
        Scanner scan = new Scanner(new File(FileUrl));
        int size = scan.nextInt();

        for(int i = 0; i < size; i++) {
            String word = scan.next();
            if(word.length() > 1) {
                if (this.wordsMap.get(word.length()) != null) {
                    this.wordsMap.get(word.length()).add(word);
                } else {
                    ArrayList<String> newList = new ArrayList<>();
                    newList.add(word);
                    this.wordsMap.put(word.length(), newList);
                }
            }
        }
    }
	public boolean isValidWord(String word){
        for(String dicoWord : this.wordsMap.get(word.length())){
            if(word.equalsIgnoreCase(dicoWord)){
                return true;
            }
        }
        return false;
    }
}
