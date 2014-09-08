package collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

/**
 * Created by proknv on 9/7/14.
 */
public class MultimapTest {

    @Test
    public void testArrayListMultimap(){
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("key1", "AAA");
        multimap.put("key1", "BBB");
        multimap.put("key1", "AAA");
        multimap.put("key2", "AAA");
        multimap.put("key3", "AAA");
        //checks
        assertEquals(multimap.size(), 5);
        assertEquals(multimap.get("key1").size(), 3);
        assertThat(multimap.get("key1")).containsOnly("AAA", "BBB");
        assertEquals(multimap.get("key2").size(), 1);
        assertThat(multimap.get("key2")).containsOnly("AAA");
        assertThat(multimap.values()).containsOnly("AAA", "BBB");
    }

    @Test
    public void testHashMultimap(){
        Multimap<String, String> multimap = HashMultimap.create();
        multimap.put("key1", "AAA");
        multimap.put("key1", "BBB");
        multimap.put("key1", "AAA");
        multimap.put("key2", "AAA");
        multimap.put("key3", "AAA");
        //checks
        assertEquals(multimap.size(), 4);
        assertEquals(multimap.get("key1").size(), 2);
        assertThat(multimap.get("key1")).containsOnly("AAA", "BBB");
        assertEquals(multimap.get("key2").size(), 1);
        assertThat(multimap.get("key2")).containsOnly("AAA");
        assertThat(multimap.values()).containsOnly("AAA", "BBB");
    }

}
