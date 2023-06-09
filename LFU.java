package test;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class LFU implements CacheReplacementPolicy {
    public LinkedHashMap<String,Integer> set;
    public LFU() {
        this.set = new LinkedHashMap<String,Integer>();
    }
    public void add(String word) {
        if(set.containsKey(word)){
            int oldVal = set.get(word);
            set.replace(word, oldVal, oldVal+1);
        }
        else {
            set.put(word, 1);
        }
    }
    public String remove() {
        if(set.isEmpty()) {
            return null;
        }
        else {
            int[] min = new int[1];
            min[0] = Integer.MAX_VALUE;
            String[] res = new String[1];
            res[0] = null;
            set.forEach((key,value)-> {
                if( value < min[0]){
                    min[0] = value;
                    res[0] = key;
                }
            } );
            set.remove(res[0]);
            return res[0];
        }
    }
}
