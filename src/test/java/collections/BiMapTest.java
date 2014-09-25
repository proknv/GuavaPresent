package collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

/**
 * BiMap allows us to navigate from a value to a key in a map.
 * BiMap keeps the values unique in the map as well as the keys
 */
public class BiMapTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBiMapValueUniqueness(){
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.put("2", "Tom");
    }

    //The BiMap.forcePut() call will quietly remove the map entry with the same value before
    // placing the key-value pair in the map
    @Test
    public void testBiMapForcePut() {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","Tom");
        biMap.forcePut("2","Tom");
        assertThat(biMap.containsKey("1")).isFalse();
        assertThat(biMap.containsKey("2")).isTrue();
    }

    //The BiMap inverse() method swaps the keys with the values
    @Test
    public void testBiMapInverse() throws Exception {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","Tom");
        biMap.put("2","Harry");
        assertThat(biMap.get("1")).isEqualTo("Tom");
        assertThat(biMap.get("2")).isEqualTo("Harry");
        BiMap<String,String> inverseBiMap = biMap.inverse();
        assertThat(inverseBiMap.get("Tom")).isEqualTo("1");
        assertThat(inverseBiMap.get("Harry")).isEqualTo("2");
    }

    //There are also other implementations of BiMap in Guava

}
