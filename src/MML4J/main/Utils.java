package MML4J.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Utils {

    /**
     * Clone a given map into an hashmap
     *
     * @param map The map to clone
     * @param <K> The key type
     * @param <V> The value type
     * @return The newly created map
     */
    public static <K, V> HashMap<K, V> cloneMap(Map<K, V> map) {
        HashMap<K, V> res = new HashMap<>();
        for(K key : map.keySet()) {
            res.put(key, map.get(key));
        }
        return res;
    }

}
