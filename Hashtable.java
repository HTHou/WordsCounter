import java.util.*;
public class Hashtable<K, V> {
    private static final int INIT_CAPACITY = 1031; //initial table size
    private int m;    // size of table
    private int n;    // number of items in hashtable
    private String[] keys;
    private Integer[] values;
    
    // constructor of hashtable 
    public Hashtable() {
        this(INIT_CAPACITY);
    }
    
    public Hashtable(int capacity) {
        if (capacity < 0) { throw new IllegalArgumentException(); }
        m = capacity;
        n = 0;
        keys = new String[m];
        values = new Integer[m];
    }

    // returns number of keys in this hashtable
    public int size() {
        return n;
    }
    
    // tests if the specified object is a key in this hashtable.
    public boolean containsKey(String key) {
        if (get(key) == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    // Maps the specified key to the specified value in this hashtable.
    public void put(String key, Integer value) {
        if (key == null || value == null) { throw new NullPointerException(); }
        
        if (n >= m/2) {
        	int capacity = m;
            switch (m) {
                case 1031 : capacity = 2063; break;
                case 2063 : capacity = 4127; break;
                case 4127 : capacity = 8263; break;
                case 8263 : capacity = 16529; break;
                case 16529 : capacity = 33071; break;
            }
            resize(capacity);
        }
        
        int b = (key.hashCode() & 0x7fffffff) % m;
        int i;
        int j;
        for (i = 0; i <= m/2; i++) {
            j = (b + i * i) % m;
            if (keys[j] == null) {
            	keys[j] = key;
            	values[j] = value;
            	n++;
            	return;
            }
            else if (keys[j].equals(key)) {
                values[j] = value;
                return;
            }
            
        }
    }
    
    // Returns the value to which the specified key is mapped
    public Integer get(String key) {
        if (key == null) { throw new NullPointerException(); }
        int b = (key.hashCode() & 0x7fffffff) % m;
        int i;
        int j;
        for (i = 0; i <= m/2; i++) {
        	j = (b + i * i) % m;
            if (keys[j] == null) {
            	return null;
            }
            else if (keys[j].equals(key)) {
            	return values[j];
            }
        }
        return null;
    }
    
    // Return a sorted array that contains all keys in this hashtable
    public String[] keys() {
        String[] array = new String[n];
        int j = 0;
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) { 
                array[j] = keys[i];
                j++;
            }
        }
        Arrays.sort(array);
        return array;
    }
    
    // Resize function
    private void resize(int capacity) {
        Hashtable<K, V> temp = new Hashtable<K, V>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        m = temp.m;
        n = temp.n;
    }
}
