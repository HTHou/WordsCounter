import java.util.*;
import java.io.*;
public class FrequencyCounter{
    public static void main(String[] args) {
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        Scanner file;
        String word;
        try {
        file = new Scanner(new FileReader(args[0]));
        }
        catch (FileNotFoundException err) {
        	System.err.println(err);
        	return;
        }
        while (file.hasNext()) {
            word = file.next();
            word = word.replaceAll("[\\p{Punct}\\p{Space}]+", "")
            		.toLowerCase();
            if (ht.containsKey(word)) {
                ht.put(word, ht.get(word) + 1);
            }
            else {
                ht.put(word, 1);
            }
        }
        file.close();
        
        int freq;
        int maxfreq = 99999;
        int counter = 0;
        while (counter < ht.size()) {
            freq = 0;
            for (String freqword: ht.keys()) {
                if (ht.get(freqword) >= freq 
                		&& ht.get(freqword) < maxfreq) {
                    freq = ht.get(freqword);
                }
            }
            for (String freqword: ht.keys()) {
                if (ht.get(freqword) == freq) {
                    System.out.println(freqword + "\t" + freq);
                    counter++;
                }
                maxfreq = freq;
            }
        }
    }
    
}
