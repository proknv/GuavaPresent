package collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

/**
 * Created by proknv on 9/8/14.
 */
public class BiMapTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBiMapValueUniqueness(){
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.put("2", "Tom");
    }

    @Test
    public void testBiMapForcePut() {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","Tom");
        biMap.forcePut("2","Tom");
        assertThat(biMap.containsKey("1")).isFalse();
        assertThat(biMap.containsKey("2")).isTrue();
    }

    @Test
    public void testBiMapInverse() throws Exception {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","Tom");
        biMap.put("2","Harry");
        assertThat(biMap.get("1")).isEqualTo("Tom");
        assertThat(biMap.get("2")).isEqualTo("Harry");
        BiMap<String,String> inverseMap = biMap.inverse();
        assertThat(inverseMap.get("Tom")).isEqualTo("1");
        assertThat(inverseMap.get("Harry")).isEqualTo("2");
    }

}
