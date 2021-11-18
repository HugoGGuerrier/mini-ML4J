package mml4j.main;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    // ----- Macros -----


    public static boolean DEBUG = false;


    // ----- Utils functions ------


    /**
     * Clone a given map into an hashmap
     *
     * @param map The map to clone
     * @param <K> The key type
     * @param <V> The value type
     * @return The newly created map
     */
    public static <K, V> HashMap<K, V> cloneMap(Map<K, V> map) {
        return new HashMap<>(map);
    }

}
