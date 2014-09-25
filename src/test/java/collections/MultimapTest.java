package collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

/**
 * The MultiMaps are used to associate more than one value with a given key
 */
public class MultimapTest {

    //ArrayListMulitmap is a map that uses ArrayList to store the values for the given key
    @Test
    public void testArrayListMultimap(){
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("key1", "AAA");
        multimap.put("key1", "BBB");
        multimap.put("key1", "AAA");
        multimap.put("key2", "AAA");
        multimap.put("key3", "AAA");
        //checks
        //notice, the call to size() takes into account all values found in each List
        assertEquals(multimap.size(), 5);
        assertEquals(multimap.get("key1").size(), 3);
        assertThat(multimap.get("key1")).isEqualTo(Lists.newArrayList("AAA", "BBB", "AAA"));
        assertEquals(multimap.get("key2").size(), 1);
        assertThat(multimap.get("key2")).containsOnly("AAA");
        //a call to values() returns a collection containing all values in MultiMap
        assertThat(multimap.values()).containsExactly("AAA", "BBB", "AAA", "AAA", "AAA");
        //Also we can move to the traditional Map
        //Notice, the returned map is a live view
        Map<String, Collection<String>> map = multimap.asMap();
        assertThat(map.get("key1")).isEqualTo(Lists.newArrayList("AAA", "BBB", "AAA"));
        map.get("key1").add("AAA");
        assertThat(multimap.get("key1")).isEqualTo(Lists.newArrayList("AAA", "BBB", "AAA", "AAA"));
    }

    //HashMultimap is based on hash tables.
    //Unlike ArrayListMultimap, inserting the same key-value pair multiple times is not supported
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

    //There is LinkedHashMultimap, which returns collections for a given key that have the values in the same order
    // as they were inserted.

    //There is also TreeMultimap that keeps the keys and values sorted by their natural order or the order
    // specified by a comparator.

}
