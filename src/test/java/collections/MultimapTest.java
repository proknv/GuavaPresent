package collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Before;
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
        Multimap<Integer, String> multimap = ArrayListMultimap.create();
        multimap.put(1, "AAA");
        multimap.put(1, "BBB");
        multimap.put(1, "AAA");
        multimap.put(2, "AAA");
        multimap.put(3, "AAA");
        //checks
        assertEquals(multimap.size(), 5);
        assertEquals(multimap.get(1).size(), 3);
        assertThat(multimap.get(1)).containsOnly("AAA", "BBB");
        assertEquals(multimap.get(2).size(), 1);
        assertThat(multimap.get(2)).containsOnly("AAA");
        assertThat(multimap.values()).containsOnly("AAA", "BBB");
    }

    @Test
    public void testHashMultimap(){
        Multimap<Integer, String> multimap = HashMultimap.create();
        multimap.put(1, "AAA");
        multimap.put(1, "BBB");
        multimap.put(1, "AAA");
        multimap.put(2, "AAA");
        multimap.put(3, "AAA");
        //checks
        assertEquals(multimap.size(), 4);
        assertEquals(multimap.get(1).size(), 2);
        assertThat(multimap.get(1)).containsOnly("AAA", "BBB");
        assertEquals(multimap.get(2).size(), 1);
        assertThat(multimap.get(2)).containsOnly("AAA");
        assertThat(multimap.values()).containsOnly("AAA", "BBB");
    }

}
