import java.util.*;

public class Quiz2Redux{
    /*Returns an ArrayList<String> that contains all subsets of the
     *characters of String s. Assume s has no duplicate characters.
     *the characters should appear in the same order that they occur
     *in the original string.
     */

    public static ArrayList<String> combinations(String s){
        ArrayList<String>words = new ArrayList<String>();
        words.add("");
        help(words , s, 0, "");
        Collections.sort(words);
        return words;
    }

    private static void help(ArrayList<String> words, String word, int index, String sub) {
        for (int i = index; i < word.length(); i++) {
            String s = sub + word.substring(i,i+1);
            words.add(s);
            help(words, word, i+1, s);
        }
    }
}
