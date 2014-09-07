package collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 9/7/14.
 */
public class MultimapTest {

    @Before
    public void setup(){
        Multimap<Integer, String> testMultimap = ArrayListMultimap.create();
        testMultimap.putAll(1, Arrays.asList("AAA_1", "BBB_1", "CCC_1"));
        testMultimap.putAll(2, Arrays.asList("AAA_2", "BBB_2", "CCC_2", "DDD_2"));
        testMultimap.put(3, "AAA_3");
    }

    @Test
    public void testArrayListMultimap(){
        Multimap<Integer, String> multimap = ArrayListMultimap.create();
        multimap.put(1, "AAA_1");
        multimap.put(1, "BBB_1");
        multimap.put(1, "CCC_1");
        multimap.put(2, "AAA_2");
        multimap.put(3, "AAA_3");
        //checks
        assertThat(multimap.size() == 6);
        assertThat(multimap.get(1).size() == 3);
        assertThat(multimap.get(1)).containsOnly("AAA_1", "BBB_1", "CCC_1");
    }

}
